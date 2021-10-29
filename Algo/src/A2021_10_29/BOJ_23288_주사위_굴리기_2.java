package A2021_10_29;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 1H43MIN
 * @author beaverbae
 * 
 * 방향별로 이동시 주사위의 면이 바뀌는 규칙이 있음 -> 2차원 배열로 표현
 * 하나의 메소드 작성 후 반드시 체크 하기
 * 이차원은 좌표는 헷갈리므로 다 필기하고 하자(머리로 하면 실수 무조건 함)
 * 
 * 실수한 부분
 * - 테두리 밖으로 이동할 때 방향도 바뀌는 것
 * - sc의 최대범위는 N이 아닌 M이다
 * - 이외 다수
 */

public class BOJ_23288_주사위_굴리기_2 {
	static int[][] board;
	static int[][] points;
	static final int[][] dirs = {{0,1}, {0,-1}, {-1,0}, {1,0}};// 우좌상하
	static final int[][] nextDiceMoves = {{5, 4, 2, 3, 0, 1}, 
									  {4, 5, 2, 3, 1, 0}, 
									  {3, 2, 0, 1, 4, 5}, 
									  {2, 3, 1, 0, 4, 5}};
	static int N, M, K;
	static int ans, sr, sc , dir;// 주사위의 좌표
	static int[] dice = {1, 6, 5, 2, 4, 3};// 위 아래 앞 뒤 왼 오
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0; sr = 0; sc = 0; dir = 0;
		
		board = new int[N][M];
		points = new int[N][M];
		visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (visited[r][c]) continue;
				
				calcPoints(r, c);
			}
		}
		
		while (K-- > 0) {
			// 주사위 이동
			move();
			// 주사위 위치 변경
			changeDicePos();
			// 점수 획득
			ans += points[sr][sc];
			// 방향 설정
			changeDirection();
		}
		
		System.out.println(ans);
	}


	private static void calcPoints(int sr, int sc) {
		Queue<int[]> q1 = new LinkedList<>();
		Queue<int[]> q2 = new LinkedList<>();
		
		q1.offer(new int[] {sr, sc});
		q2.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		int point = board[sr][sc];
		int cnt = 1;
		int total_point = 0;
		
		while (!q1.isEmpty()) {
			int[] arr = q1.poll();
			int r = arr[0];
			int c = arr[1];
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
				
				if (board[nr][nc] == point) {
					q1.offer(new int[] {nr, nc});
					q2.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					cnt++;
				}
			}
		}
		
		total_point = point * cnt;
		while (!q2.isEmpty()) {
			int[] arr = q2.poll();
			int r = arr[0];
			int c = arr[1];
			points[r][c] = total_point;
		}
	}


	private static void changeDirection() {
		// 0: 오, 1: 왼, 2: 상, 3: 하
		
		// 주사위 바닥 > 보드 위
		if (dice[1] > board[sr][sc]) {
			// 시계방향 (오흔쪽 회전)
			rightRotate();
		}
		// 주사위 바닥 < 보드 위
		else if (dice[1] < board[sr][sc]) {
			// 반시계 방향 (왼쪽 회전)
			leftRotate();
		}
	}

	private static void rightRotate() {
		if (dir == 0) dir = 3;
		else if (dir == 1) dir = 2;
		else if (dir == 2) dir = 0;
		else dir = 1;
	}

	private static void leftRotate() {
		if (dir == 0) dir = 2;
		else if (dir == 1) dir = 3;
		else if (dir == 2) dir = 1;
		else dir = 0;
	}

	private static void changeDicePos() {
		int[] nextDice = new int[dice.length];
		
		for (int i = 0; i < nextDiceMoves[dir].length; i++) {
			int j = nextDiceMoves[dir][i];
			nextDice[j] = dice[i]; 
		}
		
		dice = nextDice;
	}

	private static void move() {
		// 이동방향으로 한 칸 이동
		sr += dirs[dir][0];
		sc += dirs[dir][1];
		// 범위 체크 
		checkPos();
	}
	
	
	// 이동한 좌표가 범위를 벗어난 경우 확인
	private static void checkPos() {
		if (sr >= N) {
			sr-=2;
			dir = 2;
		} else if (sr < 0) {
			sr+=2;
			dir = 3;
		} else if (sc >= M) {
			sc-=2;
			dir = 1;
		} else if (sc < 0) {
			sc+=2;
			dir = 0;
		}
	}

	private static void print(int[][] b) {
		for (int r = 0; r < b.length; r++) {
			System.out.println(Arrays.toString(b[r]));
		}
		System.out.println();
	}
}
