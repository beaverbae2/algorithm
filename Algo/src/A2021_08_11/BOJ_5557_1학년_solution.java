package A2021_08_11;

import java.util.*;
import java.io.*;

/**
 * DP
 * dp배열의 정의 및 초기화
 * @author beaverbae
 * @see 예전 솔루션
 *
 */

public class BOJ_5557_1학년_solution {
	static int N;
	static int[] arr;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new long[N][21];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, arr[0]);
		
		long ans = 0;
		for (int i = 0; i <= 20; i++) {
			if (dp[1][i] == -1) continue;
			ans += dp[1][i];
		}
		
		System.out.println(ans);
	}

	private static long dfs(int idx, int sum) {
		if (sum < 0 || sum > 20) return 0;
		
		if (idx == N-1) {
			if (sum == arr[idx]) return 1;
			else return 0;
		}
		
		if (dp[idx][sum] != -1) return dp[idx][sum];
		
		dp[idx][sum] = 0;
		dp[idx][sum] += dfs(idx+1, sum+arr[idx]);
		dp[idx][sum] += dfs(idx+1, sum-arr[idx]);
		
		return dp[idx][sum];
	}
}
