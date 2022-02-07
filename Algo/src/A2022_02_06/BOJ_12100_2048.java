package A2022_02_06;

import java.util.*;
import java.io.*;

public class BOJ_12100_2048 {
	static int N, ans;
	static final int MAX_CNT = 5;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(board, 0);
		System.out.println(ans);
	}
	
	private static void dfs(int[][] board, int cnt) {
		if (cnt == MAX_CNT) {
			calcMaxBlock(board);
			return;
		}
		
		for (int dir = 0; dir < 4; dir++) {
			int[][] next_board = move(board, dir);
			checkCrash(next_board, dir);
			next_board = move(next_board, dir);
			dfs(next_board, cnt+1);
		}
	}

	private static void checkCrash(int[][] next_board, int dir) {
		if (dir == 0) {// 동
			for (int r = 0; r < N; r++) {
				for (int c = N-1; c > 0; c--) {
					if (next_board[r][c] == 0) continue;
					
					if (next_board[r][c] == next_board[r][c-1]) {
						next_board[r][c] *= 2;
						next_board[r][c-1] = 0;
					}
				}
			}
		} else if (dir == 1) {// 서
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N-1; c++) {
					if (next_board[r][c] == 0) continue;
					
					if (next_board[r][c] == next_board[r][c+1]) {
						next_board[r][c] *= 2;
						next_board[r][c+1] = 0;
					}
				}
			}
		} else if (dir == 2) {// 남
			for (int c = 0; c < N; c++) {
				for (int r = N-1; r > 0; r--) {
					if (next_board[r][c] == 0) continue;
					
					if (next_board[r][c] == next_board[r-1][c]) {
						next_board[r][c] *= 2;
						next_board[r-1][c] = 0;
					}
				}
			}
		} else {// 북
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N-1; r++) {
					if (next_board[r][c] == 0) continue;
					
					if (next_board[r][c] == next_board[r+1][c]) {
						next_board[r][c] *= 2;
						next_board[r+1][c] = 0;
					}
				}
			}
		}
		
//		printBoard(next_board);
	}

	private static int[][] move(int[][] board, int dir) {
		int[][] next_board = new int[N][N];
		
		if (dir == 0) {// 동
			for (int r = 0; r < N; r++) {
				Queue<Integer> q = new LinkedList<>();
				int zeroCnt = 0;
				for (int c = 0; c < N; c++) {
					if (board[r][c] == 0) zeroCnt++;
					else q.offer(board[r][c]);
				}
				
				for (int c = 0; c < N; c++) {
					if (zeroCnt-- > 0) next_board[r][c] = 0;
					else next_board[r][c] = q.poll();
				}
			}
		} else if (dir == 1) {// 서
			for (int r = 0; r < N; r++) {
				Queue<Integer> q = new LinkedList<>();
				int zeroCnt = 0;
				for (int c = N-1; c >= 0; c--) {
					if (board[r][c] == 0) zeroCnt++;
					else q.offer(board[r][c]);
				}
				
				for (int c = N-1; c >= 0; c--) {
					if (zeroCnt-- > 0) next_board[r][c] = 0;
					else next_board[r][c] = q.poll();
				}
			}
		} else if (dir == 2) {// 남
			for (int c = 0; c < N; c++) {
				Queue<Integer> q = new LinkedList<>();
				int zeroCnt = 0;
				for (int r = 0; r < N; r++) {
					if (board[r][c] == 0) zeroCnt++;
					else q.offer(board[r][c]);
				}
				
				for (int r = 0; r < N; r++) {
					if (zeroCnt-- > 0) next_board[r][c] = 0;
					else next_board[r][c] = q.poll();
				}
			}
		} else {// 북
			for (int c = 0; c < N; c++) {
				Queue<Integer> q = new LinkedList<>();
				int zeroCnt = 0;
				for (int r = N-1; r >= 0; r--) {
					if (board[r][c] == 0) zeroCnt++;
					else q.offer(board[r][c]);
				}
				
				for (int r = N-1; r >= 0; r--) {
					if (zeroCnt-- > 0) next_board[r][c] = 0;
					else next_board[r][c] = q.poll();
				}
			}
		}
		
//		printBoard(next_board);
		return next_board;
	}

	private static void calcMaxBlock(int[][] board) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ans = Math.max(ans, board[r][c]);
			}
		}
	}
	
	private static void printBoard(int[][] board) {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
}
