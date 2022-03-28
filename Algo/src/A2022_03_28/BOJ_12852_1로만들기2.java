package A2022_03_28;

import java.util.*;
import java.io.*;

/**
 * DP
 * 16MIN
 * @author beaverbae
 * 
 */

public class BOJ_12852_1로만들기2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[1000001];
		int[] prev = new int[1000001];
		final int INF = 987654321;
		Arrays.fill(dp, INF);
		
		dp[1] = 0;
		for (int i = 1; i < dp.length; i++) {
			if (i * 3 < dp.length && dp[i * 3] > dp[i] + 1) {
				dp[i * 3] = dp[i] + 1;
				prev[i * 3] = i;
			}
			if (i * 2 < dp.length && dp[i * 2] > dp[i] + 1) {
				dp[i * 2] = dp[i] + 1;
				prev[i * 2] = i;
			}
			if (i + 1 < dp.length && dp[i + 1] > dp[i] + 1) {
				dp[i + 1] = dp[i] + 1;
				prev[i + 1] = i;
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		sb.append(dp[N]).append("\n");
		sb.append(N).append(" ");
		while (N != 1) {
			sb.append(prev[N]).append(" ");
			N = prev[N];
		}
		
		System.out.println(sb);
	}
}
