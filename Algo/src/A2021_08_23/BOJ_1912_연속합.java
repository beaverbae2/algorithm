package A2021_08_23;

import java.util.*;
import java.io.*;

/**
 * DP
 * 9MIN
 * @author beaverbae
 *
 */

public class BOJ_1912_연속합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		int ans = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < dp.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < dp.length; i++) {
			dp[i] = arr[i];
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
