package A2021_04_15;

import java.util.*;
import java.io.*;

/**
 * DP
 * 오래 걸린 이유 : 초기화(dp[0][j] = 1, dp[0]의 존재), 탐색(현재 동전 가치 보다 작은 가치인 경우)
 * @author beaverbae
 *
 */

public class BOJ_2293_동전1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			int k = Integer.parseInt(br.readLine());
			
			for (int j = 1; j <= K; j++) {
				if (j-k >=0) {
					dp[i][j] = dp[i][j-k] + dp[i-1][j];
				} else {// 오래 걸린 부분
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
