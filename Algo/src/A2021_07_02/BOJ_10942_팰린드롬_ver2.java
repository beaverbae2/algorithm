package A2021_07_02;

import java.util.*;
import java.io.*;

/**
 * DP
 *  
 * @see https://mygumi.tistory.com/176
 * @author beaverbae
 *
 */

public class BOJ_10942_팰린드롬_ver2 {
	static int N, M;
	static int[] arr;
	static boolean[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new boolean[N+1][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			dp[i][i] = true;// 숫자 1개
			if (arr[i-1] == arr[i]) {// 숫자 2개
				dp[i-1][i] = true;
			}
		}
		
		// 숫자 3개 부터
		for (int i = 2; i <= N-1; i++) {
			for (int j = 1; j <= N-i; j++) {
				if (arr[j] == arr[j+i] && dp[j+1][j+i-1]) {
					dp[j][j+i] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if (dp[s][e]) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
