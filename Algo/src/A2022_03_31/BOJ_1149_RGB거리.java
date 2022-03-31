package A2022_03_31;

import java.util.*;
import java.io.*;

/**
 * DP
 * 12MIN
 * @author beaverbae
 *
 */

public class BOJ_1149_RGB거리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][3];
		final int INF = 987654321;
		int ans = INF;
		for (int i = 1; i < N+1; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			dp[i][0] = Math.min(dp[i-1][1] + r, dp[i-1][2] + r);
			dp[i][1] = Math.min(dp[i-1][0] + g, dp[i-1][2] + g);
			dp[i][2] = Math.min(dp[i-1][0] + b, dp[i-1][1] + b);
		}
		
		for (int i = 0; i < 3; i++) {
			ans = Math.min(ans, dp[N][i]);
		}
		
		System.out.println(ans);
	}
}
