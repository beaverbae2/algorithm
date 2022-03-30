package A2022_03_30;

import java.util.*;
import java.io.*;

/**
 * DP
 * 51MIN
 * @author beaverbae
 * - 복습 필요
 */

public class BOJ_2342_DanceDanceRevolution {
	static List<Integer> pos;
	static int N;
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pos = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = 0;
		while ((n = Integer.parseInt(st.nextToken())) != 0) {
			pos.add(n);
			
		}
		N = pos.size();
		dp = new int[N][5][5];
	
		System.out.println(dfs(0, 0, 0));
	}
	
	private static int dfs(int i, int l, int r) {
		if (i == N) return 0;
		
		if (dp[i][l][r] != 0) return dp[i][l][r];
		
		int d = pos.get(i);
		int left = 0, right = 0;
		// 왼발 이동
		int point = getPoint(l, d);
		if (r != d) left = dfs(i+1, d, r) + point;
		
		// 오른발 이동
		point = getPoint(r, d);
		if (l != d) right = dfs(i+1, l, d) + point;
		
		if (left == 0) dp[i][l][r] = right;
		else if (right == 0) dp[i][l][r] = left;
		else dp[i][l][r] = Math.min(left, right);
		
		return dp[i][l][r];
	}
	
	private static int getPoint(int s, int d) {
		if (s == d) return 1;
		else if (s == 0) return 2;
		else {
			if (Math.abs(s - d) == 2) return 4;
			return 3;
		}
	}
}
