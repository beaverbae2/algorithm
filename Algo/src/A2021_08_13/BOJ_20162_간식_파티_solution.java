package A2021_08_13;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * @see https://kangeee.tistory.com/35
 * 
 */

public class BOJ_20162_간식_파티_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = arr[1];
		for (int i = 2; i <= N; i++) {
			dp[i] = arr[i];
			for (int j = 1; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
