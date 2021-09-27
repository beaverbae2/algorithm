package A2021_07_16;

import java.io.*;

/**
 * DP
 * 
 * @author beaverbae
 * @see https://www.acmicpc.net/problem/4811
 *
 */

public class BOJ_4811_알약_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = 30;
		long[] dp = new long[N+1];
		StringBuilder sb = new StringBuilder();
		
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j- 1];
			}
		}
		
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break;
		
			sb.append(dp[n]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
