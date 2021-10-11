package A2021_10_11;

import java.util.*;
import java.io.*;

/**
 * DP
 * 30MIN
 * @author beaverbae
 * 증가와 감소를 나눠서 생각
 *
 */

public class BOJ_11054_가장_긴_바이토닉_부분_수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp;
		int[] arr;
		int N, ans = 0;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0;  i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1);
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {// 증가
					dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
				} else if (arr[i] < arr[j]) {// 감소
					dp[i][1] = Math.max(dp[i][1], Math.max(dp[j][0] + 1, dp[j][1] + 1));
				}
			}
			ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.println(ans);
	}
}
