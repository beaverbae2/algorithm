package A2022_05_14;

import java.util.*;
import java.io.*;

public class BOJ_5557_1학년 {
	static int N;
	static int[] num;
	static long[][] dp;
	static final int MAX = 20, MIN = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		dp = new long[N][MAX+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(1, num[0]));
	}
	
	private static long dfs(int i, int sum) {
		if (sum > MAX || sum < MIN) return 0;

		if (i == N-1) {
			return sum == num[i] ? 1 : 0;
		}

		if (dp[i][sum] != -1) {
			return dp[i][sum];
		}
		
		dp[i][sum] = 0;
		dp[i][sum] += dfs(i+1, sum + num[i]);
		dp[i][sum] += dfs(i+1, sum - num[i]);
			
		return dp[i][sum];
	}
}
