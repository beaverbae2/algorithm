package A2021_04_15;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 *
 */

public class BOJ_1003_피보나치_함수_topdown {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp = new int[41][2];
		
		// 초기값 설정
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		// 진행
		dfs(dp, 40, 0);
		dfs(dp, 40, 1);
		
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int dfs(int[][] dp, int n, int k) {
		if (dp[n][k] != -1) return dp[n][k];
	
		return dp[n][k] = dfs(dp, n-1, k) + dfs(dp, n-2, k);
	}
}
