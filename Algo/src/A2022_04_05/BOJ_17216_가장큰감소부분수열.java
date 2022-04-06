package A2022_04_05;

import java.util.*;
import java.io.*;

/**
 * DP
 * 21MIN
 * @author beaverbae
 *
 */

public class BOJ_17216_가장큰감소부분수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr, dp;
		int N, ans = 0;
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			dp[i] = arr[i];
			for (int j = 0; j < i; j++) {
				if (arr[i] >= arr[j]) continue;
				
				dp[i] = Math.max(dp[i], dp[j] + arr[i]);
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
