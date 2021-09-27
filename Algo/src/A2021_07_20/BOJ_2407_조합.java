package A2021_07_20;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

/**
 * DP
 * - BigInteger...
 * @author beaverbae
 *
 */

public class BOJ_2407_조합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger[][] dp = new BigInteger[101][101];
		int N, M;
		
		dp[1][0] = new BigInteger("1");
		dp[1][1] = new BigInteger("1");
		
		for (int i = 2; i <= 100; i++) {
			dp[i][0] = new BigInteger("1");
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i-1][j].add(dp[i-1][j-1]);
			}
			dp[i][i] = new BigInteger("1");
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		System.out.println(dp[N][M]);
	}
}
