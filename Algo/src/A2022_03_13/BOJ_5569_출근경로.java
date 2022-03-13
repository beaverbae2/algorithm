package A2022_03_13;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * - dp배열을 어떻게 잡을 것인가가 어려웠음..
 * - (r, c)에 도착했을 때 방향 뿐만 아니라 방향전환여부도 확인해야함
 */

public class BOJ_5569_출근경로 {
	static final int MOD = 100000;
	static int R, C;
	static int[][][][] dp;
	static final int[][] dirs = {{1, 0}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		dp = new int[R][C][2][2];
		
		System.out.println((dfs(0, 1, 1, 0) + dfs(1, 0, 0, 0)) % MOD);
	}
	
	private static int dfs(int r, int c, int d, int turn) {
		if (r == R-1 && c == C-1) return 1;
		if (dp[r][c][d][turn] != 0) return dp[r][c][d][turn];
		
		if (turn == 1) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if (isIn(nr, nc)) dp[r][c][d][turn] += dfs(nr, nc, d, 0);
		} else {
			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];
				
				if (!isIn(nr, nc)) continue;
				
				if (i != d) dp[r][c][d][turn] += dfs(nr, nc, i, 1);
				else dp[r][c][d][turn] += dfs(nr, nc, i, 0);
			}
		}
		
		dp[r][c][d][turn] %= MOD;
		return dp[r][c][d][turn];
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
