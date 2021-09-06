package A2021_09_06;

import java.util.*;
import java.io.*;

/**
 * DP(DFS)
 * @author beaverbae
 * @see https://velog.io/@kjihye0340/%EB%B0%B1%EC%A4%80-1633%EC%B5%9C%EA%B3%A0%EC%9D%98-%ED%8C%80-%EB%A7%8C%EB%93%A4%EA%B8%B0
 */

public class BOJ_1633_최고의_팀_만들기_solution {
	static int[][][] dp;
	static int[] white, black;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		dp = new int[1001][16][16];
		white = new int[1001];
		black = new int[1001];
	
		while ((str = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str);
//			if (!st.hasMoreElements()) break;
			white[N] = Integer.parseInt(st.nextToken());
			black[N] = Integer.parseInt(st.nextToken());
			N++;
		}
		
		System.out.println(dfs(0, 0, 0));
	}
	
	private static int dfs(int idx, int wIdx, int bIdx) {
		if (idx == N || (wIdx == 15 && bIdx == 15)) return 0;
	
		if (dp[idx][wIdx][bIdx] != 0) return dp[idx][wIdx][bIdx];
		
		// 선택X
		int ans = dfs(idx+1, wIdx, bIdx);
		// 백
		if (wIdx < 15) ans = Math.max(ans, dfs(idx+1, wIdx+1, bIdx) + white[idx]);
		// 흑
		if (bIdx < 15) ans = Math.max(ans, dfs(idx+1, wIdx, bIdx+1) + black[idx]);
		
		dp[idx][wIdx][bIdx] = ans;
		return dp[idx][wIdx][bIdx];
	}
}
