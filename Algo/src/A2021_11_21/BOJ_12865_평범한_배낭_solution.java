package A2021_11_21;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 *
 */


public class BOJ_12865_평범한_배낭_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		int[] W, V;
		int[][] dp;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		W = new int[N+1];
		V = new int[N+1];
		dp = new int[N+1][K+1];
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int w = 1; w < K+1; w++) {
				dp[i][w] = dp[i-1][w]; // 놓친 부분
				if (w - W[i] >= 0) dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-W[i]] + V[i]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
