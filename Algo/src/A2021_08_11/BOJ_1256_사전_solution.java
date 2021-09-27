package A2021_08_11;

import java.util.*;
import java.io.*;

/**
 * DP
 * nCr = n-1Cr + n-1Cr-1의 의미
 * 
 * @author beaverbae
 * @see 예전 솔루션
 *
 */

public class BOJ_1256_사전_solution {
	static int N, M;
	static long K;
	static long[][] dp;
	static final int INF = 1000000000;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		dp = new long[201][201];
		for (int n = 0; n <= 200; n++) {
			for (int r = 0; r <= n; r++) {
				if (r == 0 || n == r) {
					dp[n][r] = 1;
				} else {
					dp[n][r] = Math.min(INF, dp[n-1][r] + dp[n-1][r-1]);
				}
			}
		}
		
		if (dp[N+M][N] < K) {
			System.out.println(-1);
			return;
		}
		
		sb = new StringBuilder();
		findString(N+M, N, K);
		System.out.println(sb.toString());
	}

	private static void findString(int n, int r, long k) {
		while (r > 0) {
			if (k <= dp[n-1][r-1]) {// a포함
				n--;
				r--;
				N--;
				sb.append("a");
			} else {// z포함
				k -= dp[n-1][r-1];// 으이구
				n--;
				M--;
				sb.append("z");
			}
		}
		
		while (N-- > 0) {
			sb.append("a");
		}
		
		while (M-- > 0) {
			sb.append("z");
		}
	}
}

