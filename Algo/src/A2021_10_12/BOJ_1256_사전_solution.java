package A2021_10_12;

import java.util.*;
import java.io.*;

/**
 * DP, 조합론
 * @author beaverbae
 * 
 * 이항계수 -> nCr = n-1Cr-1 + n-1Cr
 * 어려웠던 점
 * - 전체 경우의 수 < K 인 경우 : 최대 경우가 200! / (100! * 100!) 인데 범위 초과 였음 -> 해결 : 최댓값 INF를 10억으로 잡아 dp배열에 활용
 * - k의 범위에 따라 a 또는 b를 선택해야 했는데 기준을 못잡았음 -> 해결 : 기준을  dp[n-1][r-1] 로 잡음
 */

public class BOJ_1256_사전_solution {
	static int N, M;
	static long K;
	static long[][] dp;
	static StringBuilder sb;
	static int INF = 1000000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new long[201][201];
		for (int n = 0; n <= 200; n++) {
			for (int r = 0; r <= n; r++) {
				if (r == 0 || r == n) dp[n][r] = 1;
				else dp[n][r] = Math.min(INF, dp[n-1][r-1] + dp[n-1][r]);
			}
		}
		
		if (dp[N+M][N] < K) {
			System.out.println(-1);
			return;
		}
		
		sb = new StringBuilder();
		findAnswer();
		System.out.println(sb);
	}

	private static void findAnswer() {
		int n = M+N;
		int m = M;
		long k = K;
		int r = N;
		
		while (r > 0) {
			if (k <= dp[n-1][r-1]) {// a 선택
				n--;
				r--;
				sb.append("a");
			} else {// z 선택
				k -= dp[n-1][r-1];
				n--;
				m--;
				sb.append("z");
			}
		}
		
		while (m-- > 0) {
			sb.append("z");
		}
	}
}
