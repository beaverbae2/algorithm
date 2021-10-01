package A2021_10_02;

import java.util.*;
import java.io.*;

/**
 * Backtracking
 * 63MIN
 * @author beaverbae
 * 유효성 검사할 때 효율적으로 해야함
 */

public class BOJ_2239_스도쿠 {
	static int[][] board;
	static HashSet<Integer>[] rowSet, colSet;
	static HashSet<Integer>[][] areaSet;
	static final int N = 9;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][N];
		rowSet = new HashSet[N];
		colSet = new HashSet[N];
		areaSet = new HashSet[N][N];
		
		for (int r = 0; r < N; r++) {
			String s = br.readLine();
			for (int c = 0; c < N; c++) {
				board[r][c] = s.charAt(c) - '0';
				if ((r == 1 || r == 4 || r == 7) && (c == 1 || c == 4 || c == 7)) {
					areaSet[r][c] = new HashSet<>();
				}
			}
			
			rowSet[r] = new HashSet<>();
			colSet[r] = new HashSet<>();
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] == 0) continue;
				rowSet[r].add(board[r][c]);
				colSet[c].add(board[r][c]);
			}
		}
		
		for (int r = 1; r < N; r+=3) {
			for (int c = 1; c < N; c+=3) {
				for (int i = r-1; i <= r+1; i++) {
					for (int j = c-1; j <= c+1; j++) {
						if (board[i][j] == 0) continue;
						areaSet[r][c].add(board[i][j]);
					}
				}
			}
		}
		
		dfs(0);
	}
	
	private static void dfs(int i) {
		if (i == N * N) {
			printBoard();
			System.exit(0);
		}
		
		int r = i / N;
		int c = i % N;
		if (board[r][c] != 0) {
			dfs(i+1);
			return;
		}
		
		for (int n = 1; n < 10; n++) {
			if (isValid(r, c, n)) {
				board[r][c] = n;
				dfs(i+1);
				board[r][c] = 0;
				remove(r, c, n);
			}
		}
	}
	
	private static void remove(int r, int c, int n) {
		rowSet[r].remove(n);
		colSet[c].remove(n);
		r = getCenterPos(r);
		c = getCenterPos(c);
		areaSet[r][c].remove(n);
	}

	private static boolean isValid(int r, int c, int n) {
		// 가로, 세로, 3x3 영역
		if (!checkRow(r, c, n) || !checkCol(r, c, n) || !checkArea(r, c, n)) return false;
		rowSet[r].add(n);
		colSet[c].add(n);
		r = getCenterPos(r);
		c = getCenterPos(c);
		areaSet[r][c].add(n);
		
		return true;
	}
	
	
	private static boolean checkArea(int r, int c, int n) {
		r = getCenterPos(r);
		c = getCenterPos(c);
		
		return !areaSet[r][c].contains(n);
	}

	private static boolean checkCol(int r, int c, int n) {
		return !colSet[c].contains(n);
	}

	private static boolean checkRow(int r, int c, int n) {
		return !rowSet[r].contains(n);
	}

	private static int getCenterPos(int n) {
		if (n < 3) return 1;
		if (n < 6) return 4;
		return 7;
	}

	private static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(board[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
