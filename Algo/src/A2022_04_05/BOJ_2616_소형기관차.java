package A2022_04_05;

import java.util.*;
import java.io.*;

/**
 * DP, Prefix sum
 * 52MIN
 * @author beaverbae
 * - 알고리즘 분류를 봐버려서... 
 */

public class BOJ_2616_소형기관차 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr, max;
		int[][] dp;
		int N, M, ans = 0;
		
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		max = new int[3];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		dp = new int[N-M+1][4];
		for (int i = M; i <= N; i++) {
			 dp[i-M][1] = arr[i] - arr[i-M];
		}
		
		for (int i = 0; i <= N-M; i++) {
			for (int j = 2; j <= 3; j++) {
				if (i - M < 0) continue;
				
				max[j-1] = Math.max(max[j-1], dp[i-M][j-1]);
				if (max[j-1] == 0) continue;
				
				dp[i][j] = max[j-1] + dp[i][1];
				if (j == 3) ans = Math.max(ans, dp[i][j]);
			}
		}
		
		System.out.println(ans);
	}
}
