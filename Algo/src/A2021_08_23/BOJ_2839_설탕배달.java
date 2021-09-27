package A2021_08_23;

import java.util.*;
import java.io.*;

/**
 * DP
 * 34MIN
 * @author beaverbae
 * 5kg은 3kg포함
 */

public class BOJ_2839_설탕배달 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = {0, 3, 5};
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[6][N+1];
		final int INF = 2000;
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j <= N; j++) {
				dp[arr[i]][j] = dp[arr[i-1]][j];
				if (j % arr[i] == 0) dp[arr[i]][j] = j / arr[i];
				
				if (j >= arr[i]) dp[arr[i]][j] =  Math.min(dp[arr[i]][j], dp[arr[i]][j-arr[i]] + 1);
			}
		}
		
		if (dp[5][N] == INF) System.out.println(-1);
		else System.out.println(dp[5][N]);
	}
}
