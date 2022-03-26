package A2022_03_26;

import java.io.*;

/**
 * Divide and Conquer
 * 18MIN
 * @author beaverbae
 *
 */

public class BOJ_1992_쿼드트리 {
	static int[][] board;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		board = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			String s = br.readLine();
			for (int c = 0; c < N; c++) {
				board[r][c] = s.charAt(c) - '0';
			}
		}
		
		dfs(0, 0, N, N);
		System.out.println(sb);
	}
	
	private static void dfs(int sr, int sc, int er, int ec) {
		if (er - sr == 1) {
			sb.append(board[sr][sc]);
			return;
		}
		
		int n = getNum(sr, sc, er, ec);
		if (n > -1) {
			sb.append(n);
		} else {
			sb.append("(");
			dfs(sr, sc, (sr + er) / 2, (sc + ec) / 2);
			dfs(sr, (sc + ec) / 2, (sr + er) / 2, ec);
			dfs((sr + er) / 2, sc, er, (sc + ec) / 2);
			dfs((sr + er) / 2, (sc + ec) / 2 , er, ec);
			sb.append(")");
		}
		
	}
	
	private static int getNum(int sr, int sc, int er, int ec) {
		int n = board[sr][sc];
		for (int r = sr; r < er; r++) {
			for (int c = sc; c < ec; c++) {
				if (board[r][c] != n) return -1;
			}
		}
		
		return n;
	}
}
