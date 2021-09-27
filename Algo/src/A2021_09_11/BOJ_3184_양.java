package A2021_09_11;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 25MIN
 * @author beaverbae
 *
 */

public class BOJ_3184_양 {
	static char[][] board;
	static int R, C;
	static boolean[][] visited;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int total_lamb, total_wolf, lamb, wolf;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				board[r][c] = str.charAt(c);
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!isOk(r, c)) continue;
				
				visited[r][c] = true;
				check(r, c);
				dfs(r, c);
				add();
			}
		}
		
		System.out.println(total_lamb+" "+total_wolf);
	}
	
	private static void dfs(int r, int c) {
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0]; 
			int nc = c + dirs[d][1];
			
			if (isOk(nr, nc)) {
				visited[nr][nc] = true;
				check(nr, nc);
				dfs(nr, nc);
			}
		}
	}
	
	private static void check(int r, int c) {
		if (isLamb(board[r][c])) lamb++;
		else if (isWolf(board[r][c])) wolf++;
	}
	
	private static void add() {
		if (lamb > wolf) total_lamb += lamb;
		else total_wolf += wolf;
		lamb = 0;
		wolf = 0;
	}
	
	private static boolean isLamb(char ch) {
		return ch == 'o';
	}
	
	private static boolean isWolf(char ch) {
		return ch == 'v';
	}
	
	private static boolean isOk(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C && board[r][c] != '#' && !visited[r][c]; 
	}
}
