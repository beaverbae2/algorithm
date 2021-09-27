package A2021_04_15;

import java.util.*;
import java.io.*;

/**
 * DP
 * 오래 걸린 이유 : dp 배열 설정, 초기화 설정
 * @author beaverbae
 *
 */

public class BOJ_1003_피보나치_함수_bottomup {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp = new int[41][2];
		
		// 초기값 설정
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		for (int i = 2; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = dp[i-1][j] + dp[i-2][j];
			}
		}
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
}
