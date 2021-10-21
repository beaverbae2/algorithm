package A2021_10_21;

import java.util.*;
import java.io.*;

/**
 * 41MIN
 * Simulation
 * @author beaverbae
 *
 */

public class BOJ_20056_마법사_상어와_파이어볼 {
	static int N, M, K;
	static List<Node>[][] board;
	static final int[][] dirs = {{-1, 0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		board = new List[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				board[r][c] = new ArrayList<>();
			}
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
		
			board[r][c].add(new Node(m, s, d));
		}
		
		while (K-- > 0) {
			move();
			checkDupPos();
		}
		
		System.out.println(getAns());
	}
	
	private static void move() {
		List<Node>[][] nextBoard = new List[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				nextBoard[r][c] = new ArrayList<>();
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c].isEmpty()) continue;
				
				for (int i = 0; i < board[r][c].size(); i++) {
					Node ball = board[r][c].get(i);
					int m = ball.m;
					int s = ball.s;
					int d = ball.d;
					
					// 1행과 N행, 1열과 N열이 연결
					int real_s = s % N;
					int nr = r;
					int nc = c;
					for (int j = 0; j < real_s; j++) {
						nr += dirs[d][0];
						nc += dirs[d][1];
						
						if (nr < 0) nr = N-1;
						else if (nr >= N) nr = 0;
						
						if (nc < 0) nc = N-1;
						else if (nc >= N) nc = 0;
					}
					
					nextBoard[nr][nc].add(new Node(m, s, d));
				}
			}
		}
		
		board = nextBoard;
	}

	private static void checkDupPos() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c].size() < 2) continue;
				
				boolean isEven = false;
				boolean isOdd = false;
				int sum_m = 0;
				int sum_s = 0;
				int cnt = board[r][c].size();
				
				for (int i = 0; i < board[r][c].size(); i++) {
					Node ball = board[r][c].get(i);
					int m = ball.m;
					int s = ball.s;
					int d = ball.d;
					
					sum_m += m;
					sum_s += s;
					
					if (d % 2 == 0) isEven = true;
					else isOdd = true;
				}
				
				
				board[r][c].clear();
				int next_m = sum_m / 5;
				int next_s = sum_s / cnt;
				
				if (next_m == 0) continue;
				
				if (isEven && isOdd) {
					for (int d = 1; d < dirs.length; d += 2) {
						board[r][c].add(new Node(next_m, next_s, d));
					}
				} else {
					for (int d = 0; d < dirs.length; d += 2) {
						board[r][c].add(new Node(next_m, next_s, d));
					}
				}
			}
		}
	}
	
	private static int getAns() {
		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < board[r][c].size(); i++) {
					result += board[r][c].get(i).m;
				}
			}
		}
		
		return result;
	}
	
	private static void print() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(board[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static class Node {
		int m, s, d;

		public Node(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}
}
