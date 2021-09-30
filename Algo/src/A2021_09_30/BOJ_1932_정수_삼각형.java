package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * DP
 * 12MIN
 * @author beaverbae
 * 
 */

public class BOJ_1932_정수_삼각형 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans, N;
		int[][] arr, dp;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = dp[1][1] = arr[1][1];
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				if (j == 1) {
					dp[i][j] = dp[i-1][j] + arr[i][j];
				} else if (j == i) {
					dp[i][j] = dp[i-1][j-1] + arr[i][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j] + arr[i][j], dp[i-1][j-1] + arr[i][j]);
				}
				ans = Math.max(ans, dp[i][j]);
			}
		}
		
		System.out.println(ans);
	}
}
