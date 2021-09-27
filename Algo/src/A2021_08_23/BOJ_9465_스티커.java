package A2021_08_23;

import java.util.*;
import java.io.*;

/**
 * DP
 * 20MIN
 * @author beaverbae
 *
 */

public class BOJ_9465_스티커 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N+1][2];
			int[][] dp = new int[N+1][2];
			int ans = 0;
			
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					arr[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 1; i <= N; i++) {
				dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i][0]);
				dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + arr[i][1]);
				
				ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
