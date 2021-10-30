package A2021_10_30;

import java.util.*;
import java.io.*;

/**
 * Simulation
 *
 */

public class BOJ_23291_어항_정리_refactoring {
	static int N, K;
	static int ans, max, min;
	static final int MAX = 0, MIN = 10001;
	static int[][] board, nextBoard;
	static int R, C;
	static List<Node> arrangeList;// 첫 번째 공중 부양 작업 규칙들이 들어 있는 리스트
	static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		makeArrangeList();
		R = maxRow();
		board = new int[R][N];
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			board[R-1][j] = Integer.parseInt(st.nextToken());
		}
		
		while (true) {
			calcMaxAndMin();// 최대 최소 구하기
			if (max - min <= K) break;
			
			addOneFishAtMin();
			lift1();// 첫 번째 공중 부양
			lift2();// 두 번째 공중 부양
			
			ans++;
		}
		
		System.out.println(ans);
	}
	
	private static void print(int[][] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.println(Arrays.toString(b[i]));
		}
		System.out.println();
	}

	// arrangeList 구하기
	private static void makeArrangeList() {
		arrangeList = new ArrayList<>();
		int w = 1;
		int h = 1;
		int idx = 0;
		
		while (true) {
			arrangeList.add(new Node(w, h));
			if (idx % 2 == 0) w++;
			else h++;
			
			// N - w * h : 바닥에 있는 어항들의 개수
			if (N - w * h < w) break;
			
			idx++;
		}
		
	}

	// 행의 최대 크기 구하기
	private static int maxRow() {
		int r1 = arrangeList.get(arrangeList.size() - 1).h + 1;
		int r2 = 4;
		
		return Math.max(r1, r2);
	}
	
	// 어항의 최댓값, 최솟값 구하기
	private static void calcMaxAndMin() {
		max = MAX;
		min = MIN;
		
		for (int j = 0; j < N; j++) {
			max = Math.max(max, board[R-1][j]);
			min = Math.min(min, board[R-1][j]);
		}
	}

	// 물고기가 가장 적은 어항이 물고기 한 마리 추가
	private static void addOneFishAtMin() {
		for (int j = 0; j < N; j++) {
			if (board[R-1][j] == min) board[R-1][j]++; 
		}
		
	}

	// 첫 번째 공중 부양
	private static void lift1() {
		for (int i = 0; i < arrangeList.size(); i++) {
			Node node = arrangeList.get(i);
			rotateAndPut1(node);
		}
		
		replaceFishes();
		arrange();
	}

	// 위로 이동할 어항들은 시계 방향 회전 후, 위에 두기
	private static void rotateAndPut1(Node node) {
		Queue<Integer> q = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == 0) continue;

				q.offer(board[r][c]);
			}
		}
		
		int w = node.w;
		int h = node.h;
		int C = N - w * h;
		board = new int[R][C];// 열 크기가 줄어든다
		
		// 위에 있는 어항들 -> 시계 방향으로 회전
		for (int c = w-1; c >= 0; c--) {
			for (int r = R-1-h; r < R-1; r++) {
				board[r][c] = q.poll();
			}
		}
		
		// 바닥에 있는 어항들
		for (int c = 0; c < board[0].length; c++) {
			board[R-1][c] = q.poll();
		}
	}

	// 물고기 숫자 재배치
	private static void replaceFishes() {
		int C = board[R-1].length;
		
		int[][] replaceBoard = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] == 0) continue;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isIn(nr, nc, R, C) || board[nr][nc] == 0) continue;
					
					int sub = board[r][c] - board[nr][nc];
					int denom = sub / 5;// 몫
					
					if (denom > 0) {
						replaceBoard[r][c]-=denom;
						replaceBoard[nr][nc]+=denom;
					}
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				board[r][c] += replaceBoard[r][c];
			}
		}
		
	}

	private static boolean isIn(int nr, int nc, int R, int C) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	// 두 번째 공중 부양
	private static void lift2() {
		int w = N/2;
		int h = 2;
		
		while (w >= N/4) {
			rotateAndPut2(w, h);
			w /= 2;
			h *= 2;
		}
		
		replaceFishes();
		arrange();
	}

	
	// 위로 올라갈 어항들을 180도 회전 후, 올리기 
	private static void rotateAndPut2(int w, int h) {
		Queue<Integer> q = new LinkedList<>();
		for (int r = R-1; r > R-1 - h/2; r--) {
			for (int c = w-1; c>= 0; c--) {
				q.offer(board[r][c]);
			}
		}
		
		for (int r = R - h/2; r < R; r++) {
			for (int c = w; c < board[r].length; c++) {
				q.offer(board[r][c]);
			}
		}
		board = new int[R][w];
		for (int r = R - h; r < R; r++) {
			for (int c = 0; c < w; c++) {
				board[r][c] = q.poll();
			}
		}
		
	}
	
	// 어항 재배치 -> 가로로
	private static void arrange() {
		nextBoard = new int[R][N];
		int j = 0;
		
		for (int c = 0; c < board[R-1].length; c++) {
			for (int r = R-1; r >= 0; r--) {
				if (board[r][c] == 0) continue;
				
				nextBoard[R-1][j++] = board[r][c];
				if (j == N) break;
			}
		}
		
		board = nextBoard;
	}
	
	static class Node {
		/** 
		 * 올려진 어항들은 직사각형 모양을 가진다  
		 * w : 올려진 어항들의 가로 길이
		 * h : 올려진 어항들의 세로 길이
		 */
		int w, h;

		public Node(int w, int h) {
			this.w = w;
			this.h = h;
		}

		@Override
		public String toString() {
			return "Node [w=" + w + ", h=" + h + "]";
		}
	}
}
