package A2021_08_02;

import java.util.*;
import java.io.*;

/**
 * DP
 * 52MIN
 * 하나씩 식을 나열해 보면서 점화식을 짜자
 * @author beaverbae
 *
 */

public class BOJ_11052_카드_구매하기 {
	static int N, ans;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new int[N+1][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = arr[i];
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				for (int k = 1; k <= j; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i-j][k]+arr[j]);
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
	
		System.out.println(ans);
	}
}
