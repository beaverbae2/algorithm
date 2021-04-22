package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * DFS + Memoization
 * 36MIN
 * 오래 걸린 이유
 * - dp배열의 필요성을 생각 못했다
 * @author beaverbae
 *
 */

public class BOJ_1103_게임 {
	static int R, C;
	static char[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[][] visited;
	static int answer;
	static final int INF = 987654321;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		dp = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < arr.length; j++) {
				map[i][j] = arr[j];
			}
		}
		
		dfs(0,0,1);
		if (answer == INF) System.out.println(-1);
		else {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					answer = Math.max(answer, dp[i][j]);
				}
			}
			System.out.println(answer);
		}
	}
	
	
	private static void dfs(int r, int c, int cnt) {
		if (answer == INF) return;
		
		dp[r][c] = cnt;
		visited[r][c] = true;
		
		int m = move(map[r][c]);
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0]*m;
			int nc = c + dirs[d][1]*m;
			
			if (!isGameOver(nr, nc)) {
				if (visited[nr][nc]) {
					answer = INF;
				} else if (dp[nr][nc] < cnt+1) {
					dfs(nr, nc, cnt+1);
				}
			}
		}
		
		visited[r][c] = false;
	}
	
	private static int move(char ch) {
		return ch-'0';
	}


	private static boolean isGameOver(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'H';
	}
}
