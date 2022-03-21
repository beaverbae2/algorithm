package A2022_03_22;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 47MIN
 * @author beaverbae
 * - 중복 검사 : set vs map
 * - 재귀 종료 조건 : 답 찾으면 실행 완료 후 return처리
 * 
 */


public class BOJ_2239_스도쿠 {
	static int[][] board;
	static boolean flag = false;
	static Set<Integer>[] row, col, square;
	static List<int[]> list = new ArrayList<>();
	static int N = 9;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			char[] arr = br.readLine().toCharArray();
			for (int c = 0; c < N; c++) {
				int n = arr[c] - '0';
				int i = getSquareIdx(r, c);

				board[r][c] = n;
				row[r].add(n);
				col[c].add(n);
				square[i].add(n);
				
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
		return row[r].contains(n) || col[c].contains(n) || square[i].contains(n);
	}
	
	private static void add(int r, int c, int i, int n) {
		row[r].add(n);
		col[c].add(n);
		square[i].add(n);
		board[r][c] = n;
	}
	
	private static void remove(int r, int c, int i, int n) {
		row[r].remove(n);
		col[c].remove(n);
		square[i].remove(n);
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
	
	private static void init() {
		row = new HashSet[N];
		col = new HashSet[N];
		square = new HashSet[N];
		
		for (int i = 0; i < N; i++) {
			row[i] = new HashSet<>();
			col[i] = new HashSet<>();
			square[i] = new HashSet<>();
		}
	}
	
	private static void print() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
}
