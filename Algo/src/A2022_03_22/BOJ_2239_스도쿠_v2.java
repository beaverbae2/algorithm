package A2022_03_22;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * - 행, 열, 3x3안에 있는 원소 확인을 기존 set에서 boolean배열로 표현하여 최적화
 * - 배열 쓸수 있으면 무조건 배열 쓰자 
 */

public class BOJ_2239_스도쿠_v2 {
	static int[][] board;
	static boolean flag = false;
	static boolean[][] row, col, square;
	static List<int[]> list = new ArrayList<>();
	static int N = 9;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		row = new boolean[N][N+1];
		col = new boolean[N][N+1];
		square = new boolean[N][N+1];
		board = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			char[] arr = br.readLine().toCharArray();
			for (int c = 0; c < N; c++) {
				int n = arr[c] - '0';
				int i = getSquareIdx(r, c);

				board[r][c] = n;
				row[r][n] = true;
				col[c][n] = true;
				square[i][n] = true;
				
				if (n == 0) list.add(new int[] {r, c});
			}
		}
		
		dfs(0);
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(board[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int i) {
		if (i == list.size()) {
			flag = true;
			return;
		}
		
		int r = list.get(i)[0];
		int c = list.get(i)[1];
		
		for (int n = 1; n <= N; n++) {
			int idx = getSquareIdx(r, c);
			if (isIn(r, c, idx, n)) continue;
			
			add(r, c, idx, n);
			dfs(i+1);
			if (flag) return;
			remove(r, c, idx, n);
		}
	}
	
	private static boolean isIn(int r, int c, int i, int n) {
		return row[r][n] || col[c][n] || square[i][n];
	}
	
	private static void add(int r, int c, int i, int n) {
		row[r][n] = true;
		col[c][n] = true;
		square[i][n] = true;
		board[r][c] = n;
	}
	
	private static void remove(int r, int c, int i, int n) {
		row[r][n] = false;
		col[c][n] = false;
		square[i][n] = false;
	}

	private static int getSquareIdx(int r, int c) {
		if (r < 3) {
			if (c < 3) return 0;
			if (c < 6) return 1;
			return 2;
		} else if (r < 6) {
			if (c < 3) return 3;
			if (c < 6) return 4;
			return 5;
		} else {
			if (c < 3) return 6;
			if (c < 6) return 7;
			return 8;
		}
	}	
}
