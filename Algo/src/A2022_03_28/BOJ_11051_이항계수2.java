package A2022_03_28;

import java.util.*;
import java.io.*;

/**
 * DP
 * 14MIN
 * @author beaverbae
 *
 */

public class BOJ_11051_이항계수2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp = new int[1001][1001];
		int N, K;
		final int MOD = 10007;
		
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) dp[i][j] = 1;
				else {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % MOD;
				}
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(dp[N][K]);
	}
}
