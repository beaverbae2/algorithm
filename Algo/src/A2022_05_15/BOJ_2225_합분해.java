package A2022_05_15;

import java.util.*;
import java.io.*;

/**
 * DP
 * 68MIN
 * - 점화식 잘못 세웠었음
 * - 상태공간트리 그리기
 * - 합이 최대 40000 이구나..
 * 
 * @author beaverbae
 *
 */

public class BOJ_2225_합분해 {
	static int N, K;
	static final int MOD = 1000000000;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		dp = new int[K+1][40001];
		for (int k = 0; k < K+1; k++) {
			Arrays.fill(dp[k], -1);
		}
		
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int i, int sum) {
		if (sum > N) {
			return 0;
		}
		
		if (i == K) {
			return sum == N ? 1 : 0;
		}
		
		if (dp[i][sum] != -1) {
			return dp[i][sum];
		}
		
		dp[i][sum] = 0;
		for (int n = 0; n < N+1; n++) {
			dp[i][sum] += dfs(i+1, sum+n);
			dp[i][sum] %= MOD;
		}
		
		return dp[i][sum];
	}
}
