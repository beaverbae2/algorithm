package A2022_03_23;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 78MIN
 * @author beaverbae
 * 해멘 부분
 * - 먼지 확산 : 먼저 nextBoard에 board를 deepCopy() 해야함
 * - 청정기 작동
 *     - 경로 잘못 찾음 : 경로의 범위 설정 -> isIn() override로 해결
 *     - 마지막에 -1 이 이동함 -> 하드코딩으로 해결  
 */

public class BOJ_17144_미세먼지안녕 {
	static int[][] board;
	static int R, C, T;
	static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static List<Pair> top, down;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		board = new int[R][C];
		top = new ArrayList<>();
		down = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				
				if (board[r][c] == -1) {
					if (top.isEmpty()) top.add(new Pair(r, c));
					else down.add(new Pair(r, c));
				}
			}
		}
		
		makeClearPath(top, 0, 0, 0, top.get(0).r, C-1, false);
		makeClearPath(down, 2, down.get(0).r, down.get(0).c, R-1, C-1,  true);
		System.out.println(exec());
	}
	
	private static int exec() {
		int time = 0;
		
		while (time < T) {
			spread();
			clear();
			time++;
		}
		
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] == -1) continue;
				sum += board[r][c];
			}
		}
		
		return sum;
	}
	
	private static void spread() {
		int[][] nextBoard = new int[R][C];
		deepCopy(board, nextBoard);
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] == -1) continue;
				
				int dust = board[r][c] / 5;
				int cnt = 0;
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isIn(nr, nc) || board[nr][nc] == -1) continue;
					
					cnt++;
					nextBoard[nr][nc] += dust;
				}
				
				nextBoard[r][c] -= cnt * dust;
			}
		}
		
		board = nextBoard;
	}

	private static void clear() {
		// 위
		for (int i = 1; i < top.size(); i++) {
			int r = top.get(i-1).r;
			int c = top.get(i-1).c;
			int nr = top.get(i).r;
			int nc = top.get(i).c;
			
			board[r][c] = board[nr][nc];
			if (board[r][c] == -1) board[r][c] = 0;
		}
		
		// 아래
		for (int i = 1; i < down.size(); i++) {
			int r = down.get(i-1).r;
			int c = down.get(i-1).c;
			int nr = down.get(i).r;
			int nc = down.get(i).c;
			
			board[r][c] = board[nr][nc];
			if (board[r][c] == -1) board[r][c] = 0;
		}
	}
	

	private static void makeClearPath(List<Pair> list, int d, int sR, int sC, int eR, int eC, boolean clockwise) {
		int r = list.get(0).r;
		int c = list.get(0).c;
		int er = r;
		int ec = c;
		boolean turn = false;
		list.remove(0);
		
		do {
			r += dirs[d][0];
			c += dirs[d][1];
			
			if (!isIn(r, c, sR, sC, eR, eC)) {
				r -= dirs[d][0];
				c -= dirs[d][1];
				if (!clockwise) d++;
				else d--;
				turn = true;
			}
			if (d == dirs.length) d = 0;
			if (d == -1) d = dirs.length - 1;
			
			if (turn) {
				r += dirs[d][0];
				c += dirs[d][1];
				turn = false;
			}
			
			list.add(new Pair(r, c));
		} while (r != er || c != ec);
		
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	private static void deepCopy(int[][] src, int[][] dst) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				dst[r][c] = src[r][c];
			}
		}
	}
	
	// 놓친부
	private static boolean isIn(int r, int c, int sR, int sC, int eR, int eC) {
		return r >= sR && r <= eR && c >= sC && c <= eC;
	}

	static class Pair {
		int r, c;

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
