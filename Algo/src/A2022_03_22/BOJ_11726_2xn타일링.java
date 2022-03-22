package A2022_03_22;

import java.io.*;

/**
 * 11MIN
 * DP
 * @author beaverbae
 *
 */

public class BOJ_11726_2xn타일링 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[1001];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= 1000; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007; 
		}
		
		int N = Integer.parseInt(br.readLine());
		System.out.println(dp[N]);
	}
}
