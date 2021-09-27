package A2021_08_23;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * @see https://reakwon.tistory.com/126
 *
 */

public class BOJ_2839_설탕배달_ver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[5001];
		dp[3] = dp[5] = 1;
		
		for (int i = 6; i <= N; i++) {
			if (dp[i-3] > 0) dp[i] = dp[i-3] + 1;
			
			if (dp[i-5] > 0) {
				if (dp[i] > 0) {// 3의 배수
					dp[i] = Math.min(dp[i], dp[i-5] + 1);
				} else {// 3의 배수가 아닌경우 -> 5의 배수
					dp[i] = dp[i-5] + 1;
				}
			}
		}
		
		if (dp[N] == 0) System.out.println(-1);
		else System.out.println(dp[N]);
	}
}
