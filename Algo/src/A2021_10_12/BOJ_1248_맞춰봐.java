package A2021_10_12;

import java.util.*;
import java.io.*;


/**
 * Backtracking
 * 31MIN
 * @author beaverbae
 * 무식하게 풀었는데 됐다
 *
 */

public class BOJ_1248_맞춰봐 {
	static int N;
	static char[][] S;
	static int[] sum, ans;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new char[N][N];
		sum = new int[N];
		ans = new int[N];
		
		String s = br.readLine();
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				S[i][j] = s.charAt(idx++);
			}
		}
		
		dfs(0);
	}
	
	private static void dfs(int idx) {
		
		if (idx == N) {
			StringBuilder sb = new StringBuilder();
			for (int n : ans) {
				sb.append(n).append(" ");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		if (S[idx][idx] == '+') {
			for (int i = 1; i <= 10; i++) {
				if (check(idx, i)) {
					add(idx, i);
					ans[idx] = i;
					dfs(idx+1);
					minus(idx, i);
				}
			}
		} else if (S[idx][idx] == '-') {
			for (int i = -10; i < 0; i++) {
				if (check(idx, i)) {
					add(idx, i);
					ans[idx] = i;
					dfs(idx+1);
					minus(idx, i);
				}
			}
		} else {
			if (check(idx, 0)) {
				ans[idx] = 0;
				dfs(idx+1);
			}
		}
	}

	private static boolean check(int idx, int num) {
		for (int i = 0; i <= idx; i++) {
			if (S[i][idx] == '+') {
				if (sum[i] + num <= 0) return false; 
			} else if (S[i][idx] == '-') {
				if (sum[i] + num >= 0) return false; 
			} else {
				if (sum[i] + num != 0) return false; 
			}
		}
		return true;
	}

	private static void minus(int idx, int num) {
		for (int i = 0; i <= idx; i++) {
			sum[i] -= num;
		}
	}

	private static void add(int idx, int num) {
		for (int i = 0; i <= idx; i++) {
			sum[i] += num;
		}
	}
}
