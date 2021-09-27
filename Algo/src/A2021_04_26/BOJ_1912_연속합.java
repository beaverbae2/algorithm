package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 *
 */

public class BOJ_1912_연속합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int[] arr = new int[N+1];
		int answer = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
	}
}
