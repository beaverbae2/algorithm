package A2021_04_30;

import java.util.*;
import java.io.*;

/**
 * 40MIN
 * 어려웠던 점
 * - dp배열을 어떻게 짜야하는가 -> 첨에 1차원으로 함
 * - dfs 결과
 * @author beaverbae
 *
 */

public class BOJ_5557_1학년 {
	static long answer;
	static int[] arr;
	static int N;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		dp = new long[N][21];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, arr[0]);
		for (int i = 0; i < 21; i++) {
			if (dp[1][i] == -1) continue;
			answer += dp[1][i];
		}
		System.out.println(answer);
	}

	private static long dfs(int idx, int sum) {
		if (sum < 0 || sum > 20) return 0;
		if (idx == N-1) {
			if (sum == arr[idx]) {
				return 1;
			}
			return 0;
		}
		
		if (dp[idx][sum] != -1) return dp[idx][sum];
		
		long ans = 0;
		ans += dfs(idx+1, sum+arr[idx]);
		ans += dfs(idx+1, sum-arr[idx]);
	
		dp[idx][sum] = ans;
		return dp[idx][sum];
	}
}
