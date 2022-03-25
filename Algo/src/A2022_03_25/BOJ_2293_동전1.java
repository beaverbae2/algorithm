package A2022_03_25;

import java.util.*;
import java.io.*;

/**
 * 17MIN
 * DP
 * @author beaverbae
 *
 */

public class BOJ_2293_동전1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		int[] coins, dp;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N];
		dp = new int[K+1];
		dp[0] = 1;
		
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coins);
		for (int c : coins) {
			for (int j = c; j < K+1; j++) {
				dp[j] += dp[j-c];
			}
		}
		
		
		System.out.println(dp[K]);
	}
}
