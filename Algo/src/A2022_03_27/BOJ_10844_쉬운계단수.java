package A2022_03_27;

import java.io.*;

/**
 * DP
 * @author beaverbae
 * - 표현해야하는 것 : 자리수 i에서 숫자 j로 끝나는 계단수
 */

public class BOJ_10844_쉬운계단수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 100;
		int MOD = 1000000000;
		long[][] dp = new long[N+1][10];
		long ans = 0;
		
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for (int i = 2; i < N+1; i++) {
			dp[i][0] = dp[i-1][1];
			for (int j = 1; j <= 8; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
			}
			dp[i][9] = dp[i-1][8];
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i <= 9; i++) {
			ans = (ans + dp[K][i]) % MOD;
		}
		
		System.out.println(ans);
	}
}
