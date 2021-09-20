package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * DP
 * 8MIN
 * @author beaverbae
 * 
 */

public class BOJ_1463_1로_만들기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[3000001];
		Arrays.fill(dp, 987654321);
		dp[1] = 0;
		
		for (int i = 1; i <= 1000000; i++) {
			dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
			dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
			dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
		}
		
		int N = Integer.parseInt(br.readLine());
		System.out.println(dp[N]);
	}
}
