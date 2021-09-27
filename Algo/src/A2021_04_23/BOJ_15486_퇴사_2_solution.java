package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * DP
 * 오래 걸린 이유
 * - dp배열의 정의를 잘못함 -> 일이 끝나는 날을 기준으로 생각해야함
 * @author beaverbae
 *
 */

public class BOJ_15486_퇴사_2_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N+2];
		int[] P = new int[N+2];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+2];
		int answer = 0;
		for (int i = 1; i <= N+1; i++) {
			dp[i] = Math.max(dp[i], answer);
			if (i+T[i] <= N+1) {
				dp[i+T[i]] = Math.max(dp[i+T[i]], dp[i]+P[i]);
			}
			answer = Math.max(dp[i], answer);
		}
		System.out.println(answer);
	}
}
