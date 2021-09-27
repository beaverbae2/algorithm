package A2021_07_19;

import java.util.*;
import java.io.*;

/**
 * DP
 * 배낭문제
 * @author beaverbae
 *
 */

public class BOJ_14728_벼락치기_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, T;
		int[] W, V;
		int[][] dp;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		W = new int[N+1];
		V = new int[N+1];
		dp = new int[N+1][T+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 1; i <= N; i++) {
			int w = W[i];
			for (int j = 0; j <= T; j++) {
				if (j - w < 0) dp[i][j] = dp[i-1][j];
				else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + V[i]);
			}
		}
		
		System.out.println(dp[N][T]);
	}
}
