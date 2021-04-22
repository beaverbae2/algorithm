package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * DP
 * 44MIN
 * 오래걸린 이유
 * - 열 탐색해야되는데 행으로 탐색했음...
 * - 점화식 짜는거 어렵..
 * @author beaverbae
 *
 */

public class BOJ_18427_함께_블록_쌓기 {
	static final int MOD = 10007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
	
		int[][] arr = new int[N+1][M];
		
		for (int i = 1; i <= N; i++) {
			String[] s_arr = br.readLine().split(" ");
			for (int j = 0; j < s_arr.length; j++) {
				arr[i][j] = Integer.parseInt(s_arr[j]);
			}
		}
		
		int[][] dp = new int[N+1][1001];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		
		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = dp[i-1][j];
			}
			
			for (int a : arr[i]) {
				if (a == 0) break;
				
				for (int j = a; j < dp[i].length; j++) {
					dp[i][j] = (dp[i][j] + dp[i-1][j-a]) % MOD;
				}
			}
		}
		
		System.out.println(dp[N][H]);
	}
}
