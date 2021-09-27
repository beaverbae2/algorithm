package A2021_05_02;

import java.util.*;
import java.io.*;

public class BOJ_13302_리조트 {
	static int N, M;
	static int[][] dp;
	static boolean[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		dp = new int[N+1][201];// i: 일, j : 쿠폰
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		arr = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[Integer.parseInt(st.nextToken())] = true;
		}
		
		System.out.println(dfs(1, 0));
	}

	private static int dfs(int day, int coupon) {
		if (day > N) {// 마지막 일도 포함되어야함
			return 0;
		}
		
		if (dp[day][coupon] != -1) return dp[day][coupon];
		
		dp[day][coupon] = 0;
		
		if (arr[day]) {
			dp[day][coupon] = dfs(day+1, coupon);
		} else {
			int answer = Integer.MAX_VALUE;
			
			// 쿠폰 사용O
			if (coupon >= 3) answer = Math.min(answer, dfs(day+1, coupon-3));// 쿠폰 사용은 3장 부터....
			
			// 쿠폰 사용X
			answer = Math.min(answer, dfs(day+1, coupon)+10000);
			answer = Math.min(answer, dfs(day+3, coupon+1)+25000);
			answer = Math.min(answer, dfs(day+5, coupon+2)+37000);
			
			dp[day][coupon] = answer;
		}
		
		return dp[day][coupon];
		
	}
}
