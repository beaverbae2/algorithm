package A2022_05_14;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 *
 */

public class BOJ_13302_리조트 {
	static int N, M;
	static int[][] dp;
	static boolean[] isNot;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N+1][41];
		for (int i = 0; i < N+1; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		isNot = new boolean[N+1];
		
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				isNot[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		System.out.println(dfs(1, 0));
	}
	
	
	private static int dfs(int day, int coupon) {
		if (day > N) {
			return 0;
		}
		
		if (dp[day][coupon] != INF) {
			return dp[day][coupon];				
		}
		
		if (isNot[day]) {
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day+1, coupon));
		} else {
			if (coupon >= 3) {
				dp[day][coupon] = Math.min(dp[day][coupon], dfs(day+1, coupon-3));
			}
			
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day+1, coupon) + 10000);
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day+3, coupon+1) + 25000);
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day+5, coupon+2) + 37000);
		}
		
		
		return dp[day][coupon];
	}
}
