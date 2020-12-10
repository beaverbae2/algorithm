package A2020_12_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BOJ_10844_쉬운계단수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long mod = 1000000000;
		long [][] dp = new long[N+1][10];
		long sum = 0;
		
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i-1][1];//0
			dp[i][9] = dp[i-1][8];//9
			for (int j = 1; j <= 8; j++) {
				dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1])%mod;
			}
		}
		for (int i = 0; i <= 9; i++) {
			sum = (sum+dp[N][i])%mod;
		}
		System.out.println(sum);
	}
}
