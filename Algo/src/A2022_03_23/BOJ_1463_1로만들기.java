package A2022_03_23;

import java.util.*;
import java.io.*;

public class BOJ_1463_1로만들기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[1000001];
		final int INF = 987654321;
		Arrays.fill(dp, INF);
		dp[1] = 0;
		
		for (int i = 2; i < dp.length; i++) {
			if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
			if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
		}
		
		System.out.println(dp[Integer.parseInt(br.readLine())]);
	}
}
