package A2021_10_11;

import java.util.*;
import java.io.*;

/**
 * DP
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_11053_가장_긴_증가하는_부분수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, ans = 0;
		int[] arr, dp;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] >= arr[i]) continue;
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
