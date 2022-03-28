package A2022_03_28;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * @see https://www.acmicpc.net/board/view/82868
 * - 현재 순서의 도주를 안마시는 경우 고려해야함
 */

public class BOJ_2156_포도주시식 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp;
		int N, ans;
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][3];
		ans = dp[1][1] = Integer.parseInt(br.readLine()); 
		
		for (int i = 2; i < N+1; i++) {
			int n = Integer.parseInt(br.readLine());
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
			dp[i][1] += dp[i-1][0] + n;
			dp[i][2] += dp[i-1][1] + n;
		}
		
		ans = Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2]));
		System.out.println(ans);
	}
}
