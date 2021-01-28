package A2021_01_14;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * @see JAEHYUN's code
 * 점화식 짜기 너무 어렵고~~
 */

public class BOJ_15486_퇴사2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N+2];
		int[] P = new int[N+2];
		int[] dp = new int[N+2];//dp[i] : i번쨰 상담을 받을 때까지 최대 비용
		int answer = 0;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N+1; i++) {
			dp[i] = Math.max(dp[i], answer);
			if (T[i] + i < N+2) {
				dp[T[i] + i] = Math.max(dp[T[i] + i], P[i] + dp[i]);
			}
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
	}
}
