package A2021_09_19;

import java.util.*;
import java.io.*;

/**
 * 18MIN
 * DP
 * @author beaverbae
 * 유의점
 * - DP는 초기화를 어떻게 할지 그리고 점화식을 어떻게 짤지 고민
 */

public class BOJ_1149_RGB거리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] houses, dp;
	
		int N = Integer.parseInt(br.readLine());
		houses = new int[N+1][3];
		dp = new int[N+1][3];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], 987654321);
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				houses[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][0] = houses[1][0];
		dp[1][1] = houses[1][1];
		dp[1][2] = houses[1][2];
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				int k1 = j-1;
				int k2 = j+1;
				
				if (k1 < 0) k1 += 3;
				if (k2 > 2) k2 -= 3;
			
				dp[i][j] = Math.min(dp[i-1][k1] + houses[i][j], dp[i-1][k2] + houses[i][j]);
			}
		}
		
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}
}
