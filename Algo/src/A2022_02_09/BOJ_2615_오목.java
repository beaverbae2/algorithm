package A2022_02_09;

import java.util.*;
import java.io.*;

public class BOJ_2615_오목 {
	public static void main(String[] args) throws Exception {
		Board board = new Board();
		board.setUp();
		board.clac();
	}
}

class Board {
	final int N = 19;
	int[][] board = new int[N][N];
	int[][] dirs1 = {{-1, 0}, {0, -1}, {-1, -1}, {-1, 1}};
	int[][] dirs2 = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};
	

	public void print() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
	
	public void setUp() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	
	}
	
	public void clac() {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				if (board[r][c] == 0) continue;
				
				flag = check(r, c);
				
				if (flag) {
					sb.append(board[r][c]).append("\n");
					sb.append((r+1)).append(" ").append((c+1)).append("\n");
					break;
				}
			}
			
			if (flag) break;
		}
		
		if (!flag) sb.append(0).append("\n");
		System.out.println(sb.toString());
	}

	private boolean check(int sr, int sc) {
		boolean result = false;
		for (int d = 0; d < dirs1.length; d++) {
			result = lineCheck(sr, sc, d);
			if (result) break;
		}
		
		return result;
	}

	private boolean lineCheck(int sr, int sc, int d) {
		int sameCnt = 1;
		
		for (int i = 1; i <= 5; i++) {
			int r = sr + i * dirs1[d][0];
			int c = sc + i * dirs1[d][1];
			
			if (!isIn(r, c) || board[sr][sc] != board[r][c]) break;
			sameCnt++;
		}
		
		for (int i = 1; i <= 5; i++) {
			int r = sr + i * dirs2[d][0];
			int c = sc + i * dirs2[d][1];
			
			if (!isIn(r, c) || board[sr][sc] != board[r][c]) break;
			sameCnt++;
		}
		
		return sameCnt == 5;
	}
	
	private boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
