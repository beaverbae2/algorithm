package A2021_05_01;

import java.util.*;
import java.io.*;

public class BOJ_5569_출근_경로_fail {
	static int R, C;
	static int[][][] dp;
	static int[][] dirs = {{0, 1},{1, 0}};
	static final int MOD = 100000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		dp = new int[R][C][2];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		dfs(0, 0, -1, 0);
		System.out.println(dp[0][0][0]);
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int a = 0;
				for (int k = 0; k < 2; k++) {
					if (dp[i][j][k] == -1) continue;
					a += dp[i][j][k];
				}
				System.out.print(a+" ");
			}
			System.out.println();
		}
		
	}
	
	
	private static int dfs(int r, int c, int dir, int turn) {
		if (dp[r][c][turn] != -1) return dp[r][c][turn];
		if (r == R-1 && c == C-1) {
			return 1;
		}
		
		dp[r][c][turn] = 0;
		if (turn == 1) {// 직전에 돌았다면 현재 방향 대로 가야함
			for (int d = 0; d < dirs.length; d++) {
				if (dir != d) continue;
				
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) continue;
				
				dp[r][c][turn] = (dp[r][c][turn] + dfs(nr, nc, d, 0)) % MOD;
			}
		} else {
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) continue;
				
				if (dir == -1) {
					dp[r][c][turn]= (dp[r][c][turn] + dfs(nr, nc, d, 0)) % MOD;
				} else {
					if (d != dir) {
						dp[r][c][turn] = (dp[r][c][turn] + dfs(nr, nc, d, 1)) % MOD;
					} else {
						dp[r][c][turn] = (dp[r][c][turn] + dfs(nr, nc, d, 0)) % MOD;
					}
				}
			}
		}
		
		return dp[r][c][turn];
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
