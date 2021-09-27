package A2021_04_29;

import java.util.*;
import java.io.*;

/**
 * 오래걸린이유 - findString() -> 원하는 문자열 찾는데서 오래걸림.. 수학 너무 어려움 - dp 배열 ->
 * 1000000000초과는 1000000000으로 해야함(오버플로우 나더라...)
 * 
 * @author beaverbae
 *
 */

public class BOJ_1256_사전 {
	static long[][] dp;
	static int N, M;
	static long K;
	static StringBuilder sb;
	static int answer;
	static int[] alpha;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		alpha = new int[2];
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		alpha[0] = N;
		alpha[1] = M;

		dp = new long[201][201];
		sb = new StringBuilder();

		for (int i = 0; i <= 200; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || i == j) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = Math.min(1000000000, dp[i - 1][j] + dp[i - 1][j - 1]);
			}
		}

		if (dp[M + N][M] < K) {
			System.out.println(-1);
			return;
		}

		findString(K, M + N, N);
		System.out.println(sb.toString());
	}

	private static void findString(long k, int n, int r) {
		while (r > 0) {
			if (k <= dp[n - 1][r - 1]) {
				n = n - 1;
				r = r - 1;
				alpha[0]--;
				sb.append("a");
			} else if (k > dp[n - 1][r - 1]) {
				k -= dp[n - 1][r - 1];
				n = n - 1;
				alpha[1]--;
				sb.append("z");
			}
		}

		while (alpha[0] > 0) {
			sb.append("a");
			alpha[0]--;
		}

		while (alpha[1] > 0) {
			sb.append("z");
			alpha[1]--;
		}
	}

}