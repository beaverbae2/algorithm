package A2022_03_25;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 *
 */


public class BOJ_11047_동전0_dp {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		int N, K;
		int[] coins, dp;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N];
		dp = new int[K+1];
		final int INF = 987654321;
		
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for (int c : coins) {
			for (int i = c; i <= K; i++) {
				dp[i] = Math.min(dp[i], dp[i-c] + 1);
			}
		}
		
		System.out.println(dp[K]);
	}
}
