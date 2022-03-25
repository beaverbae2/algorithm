package A2022_03_25;

import java.util.*;
import java.io.*;

/**
 * DP(knapsack)
 * @author beaverbae
 * - 직전의 가치와 비교해야함
 */

public class BOJ_12865_평범한배낭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K, ans = 0;
		int[][] dp;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[2][K+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < K+1; j++) {
				int r, s;
				if (i % 2 == 0) {
					r = 1;
					s = 0;
				} else {
					r = 0;
					s = 1;
				}
				
				dp[r][j] = dp[s][j];
				if (j - w < 0) continue;
				
				dp[r][j] = Math.max(dp[r][j], dp[s][j-w] + v);
				ans = Math.max(ans, dp[r][j]);
			}
		}
		System.out.println(ans);
	}
}
