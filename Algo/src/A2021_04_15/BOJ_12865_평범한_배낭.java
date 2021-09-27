package A2021_04_15;

import java.util.*;
import java.io.*;

/**
 * DP
 * 오래 걸린 이유 : dp배열 만들기, 탐색
 * @author beaver
 *
 */

public class BOJ_12865_평범한_배낭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] w, v;
		int[][] dp;
		int N, K;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		w = new int[N+1];
		v = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i-1][j];
				if (j - w[i] >= 0) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
