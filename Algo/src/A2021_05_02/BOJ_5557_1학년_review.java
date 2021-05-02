package A2021_05_02;

import java.util.*;
import java.io.*;

public class BOJ_5557_1학년_review {
	static long[][] dp;
	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new long[N][21];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		// 0 때문에 문제가 생기네
		System.out.println(dfs(1, arr[0]));
	}

	private static long dfs(int idx, int sum) {
		if (sum < 0 || sum > 20) return 0;
		
		if (idx == N-1) {
			if (sum == arr[N-1]) return 1;
			
			return 0;
		}
		
		if (dp[idx][sum] != -1) return dp[idx][sum];
		
		dp[idx][sum] = 0;
		
		dp[idx][sum] += dfs(idx+1, sum+arr[idx]);
		dp[idx][sum] += dfs(idx+1, sum-arr[idx]);
		
		return dp[idx][sum];
	}
}
