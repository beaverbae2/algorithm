package A2021_02_18;

import java.util.*;
import java.io.*;

/**
 * DP
 * 
 * @author beaverbae
 * @see https://fbtmdwhd33.tistory.com/60
 */

public class BOJ_12865_평범한_배낭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		int[] W = new int[N+1];// 무게
		int[] V = new int[N+1];// 가치
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];// i : 물건 번호, j : 무게
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i-1][j]; // 이전 아이템의 가치 저장
				if (j - W[i] >= 0) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
