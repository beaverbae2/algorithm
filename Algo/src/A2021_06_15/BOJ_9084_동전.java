package A2021_06_15;

import java.util.*;
import java.io.*;

/**
 * DP (Knapsack)
 * 어려웠던 부분
 * - 점화식(배열)은 해당 인덱스가 무엇을 의미하는지 파악하는 것이 중요
 * @author beaverbae
 *
 */

public class BOJ_9084_동전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coin = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			int[][] dp = new int[N+1][M+1];
			
			// 초기화
			for (int i = 1; i <= N; i++) {
				dp[i][0] = 1;
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (j - coin[i] >= 0) {
						dp[i][j] = dp[i-1][j] + dp[i][j-coin[i]] ;  
					} else {
						dp[i][j] = dp[i-1][j];
					}
					
				}
			}
			
			sb.append(dp[N][M]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
