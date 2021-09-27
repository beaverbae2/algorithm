package A2021_09_19;

import java.util.*;
import java.io.*;

/**
 * DP
 * 5MIN
 * @author beaverbae
 * DP 입문문제
 */

public class BOJ_1003_피보나치_함수 {
	static int[][] dp;
	static final int N = 40;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp = new int[N+1][2];
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 2; j++) {
				dp[i][j] = dp[i-2][j] + dp[i-1][j];
			}
		}
		
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			int i = Integer.parseInt(br.readLine());
			sb.append(dp[i][0]).append(" ").append(dp[i][1]).append("\n");
		}
		System.out.println(sb);
	}
}
