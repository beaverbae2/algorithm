package A2020_12_21;

import java.util.*;
import java.io.*;

public class BOJ_2502_떡먹는호랑이 {
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		dp = new int[D+1][2];
		dp[1][0] = 1;
		dp[1][1] = 0;
		dp[2][0] = 0;
		dp[2][1] = 1;
		
		for (int i = 3; i < dp.length; i++) {
			dp[i][0] = dp[i-2][0] + dp[i-1][0];
			dp[i][1] = dp[i-2][1] + dp[i-1][1];
		}
		
		
		int a = 1;
		int b = 0;
		while(true) {
			int sub = K - a*dp[D][0];
			if (sub % dp[D][1] == 0) {
				b = sub/dp[D][1];
				break;
			}
			a++;
		}
		
		System.out.println(a);
		System.out.println(b);
	}
}
