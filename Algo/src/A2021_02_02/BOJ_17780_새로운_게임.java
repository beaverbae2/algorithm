package A2021_02_02;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */
public class BOJ_17780_새로운_게임 {
	static int[][] map;// 격자의 색 표시
	static List<Node>[][] board;// 격자에 있는 말 표시
	static int N, K;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };// 동 서 북 남
	static Pair[] position;// position[i] : i번째 말의 현재 위치 (r, c)
	static boolean overSize;// 한 칸에 말이 4개 이상 확인

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		board = new List[N][N];
		position = new Pair[K + 1];
		overSize = false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;

			board[r][c].add(new Node(i + 1, dir));
			position[i + 1] = new Pair(r, c);
		}

		int answer = 0;
		while (true) {
			if (answer > 1000) {
				answer = -1;
				break;
			}

			if (overSize)
				break;

			game();// 게임 진행
			answer++;
		}

		System.out.println(answer);
	}

	private static void game() {
		for (int n = 1; n <= K; n++) {// 전체 말에 대해서 진행
			Pair p = position[n];
			int r = p.r;
			int c = p.c;

			Node node = board[r][c].get(0);
			int num = node.num;
			if (num != n)
				continue; // 바닥에 말이 없는 경우 pass

			int dir = node.dir;// 방향
			int nr = r + dirs[dir][0];// 이동하려는 행 좌표
			int nc = c + dirs[dir][1];// 이동하려는 열 좌표

			if (isTurn(nr, nc)) {// 방향 바꾸는 경우(파란색 or 벙위 밖)
				dir = turn(dir); // 방향 변환
				nr = r + dirs[dir][0];// 바뀐 방향으로 이동하려는 행 변경
				nc = c + dirs[dir][1];// 바뀐 방향으로 이동하려는 열 변경
				node.dir = dir;// 실제 board에 있는 말의 방향 변경
				
				
				if (isTurn(nr, nc)) {// 방향 바꿨는데도 또 걸리는 경우
					continue;// 그 자리에서 가만히 있는다
				} else {// 이동하고, 해당 칸의 말의 개수 확인
					move(r, c, nr, nc, dir);
					if (checkSize(nr, nc)) {
						overSize = true;
						break;
					}
				}
			} else {// 이동하고, 해당 칸의 말의 개수 확인
				move(r, c, nr, nc, dir);
				if (checkSize(nr, nc)) {
					overSize = true;
					break;
				}
			}

		}
	}
	
	// (r, c)에서의 말의 개수가 4개 이상인지 확인
	public static boolean checkSize(int r, int c) {
		return board[r][c].size() >= 4;
	}
	
	// 말의 이동
	public static void move(int r, int c, int nr, int nc, int dir) {
		ArrayList<Node> moveNodes = new ArrayList<>();

		if (map[nr][nc] == 0) {// 흰
			for (int i = 0; i < board[r][c].size(); i++) {// 순서대로
				Node node = board[r][c].get(i);
				moveNodes.add(new Node(node.num, node.dir));
				position[node.num] = new Pair(nr, nc);
			}
			board[nr][nc].addAll(moveNodes);

		} else if (map[nr][nc] == 1) {// 빨
			for (int i = board[r][c].size() - 1; i >= 0; i--) {// 역순으로
				Node node = board[r][c].get(i);
				moveNodes.add(new Node(node.num, node.dir));
				position[node.num] = new Pair(nr, nc);
			}
			board[nr][nc].addAll(moveNodes);
		}

		// 정리
		board[r][c] = new ArrayList<>();// 이동하기 전 좌표는 빈칸이 된다

	}

	// 방향 전환 : 맵 밖 or 파란색
	public static boolean isTurn(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 2) {
			return true;
		}
		return false;
	}

	// 방향 180도 전환
	public static int turn(int dir) {
		if (dir == 0) {
			dir = 1;
		} else if (dir == 1) {
			dir = 0;
		} else if (dir == 2) {
			dir = 3;
		} else if (dir == 3) {
			dir = 2;
		}
		return dir;
	}

	static class Node {
		int num, dir;// 번호, 방향

		public Node(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", dir=" + dir + "]";
		}
	}

	static class Pair {
		int r, c;// 행, 열

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
}
