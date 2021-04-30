package A2021_05_01;

import java.util.*;
import java.io.*;

/**
 * DP
 * 틀렸던 이유
 * - 점화식 잘못 만듦 (3차원으로함)
 * @author beaverbae
 * @see https://huiung.tistory.com/136
 * 
 */

public class BOJ_5569_출근_경로_solution {
	static int R, C;
	static int[][][][] dp;
	static final int MOD = 100000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		dp = new int[R+1][C+1][2][2];// 0 : 방향전환 가능, 1: 방향전환 불가 / 0 : 북, 1: 동
		for (int i = 2; i <= R; i++) {
			dp[i][1][0][0] = 1;
		}
		
		for (int i = 2; i <= C; i++) {
			dp[1][i][0][1] = 1;
		}
		
		for (int i = 2; i <= R; i++) {
			for (int j = 2; j <= C; j++) {
				dp[i][j][0][0] = (dp[i-1][j][0][0] + dp[i-1][j][1][0]) % MOD;
				dp[i][j][0][1] = (dp[i][j-1][0][1] + dp[i][j-1][1][1]) % MOD;
				dp[i][j][1][0] = (dp[i-1][j][0][1]) % MOD;
				dp[i][j][1][1] = (dp[i][j-1][0][0]) % MOD;
			}
		}
		
		int answer = (dp[R][C][0][0] + dp[R][C][0][1] + dp[R][C][1][0] + dp[R][C][1][1])%MOD;
		System.out.println(answer);
	}
}
