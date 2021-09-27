package A2021_02_25;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 * @see J.H.KIM
 *
 */

public class BOJ_7579_앱_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		int[][] dp = new int[N+1][10001];
		
		int[] W = new int[N+1];// 무게 : 실행시간
		int[] V = new int[N+1];// 가치 : 메모리
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < V.length; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < V.length; i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 10000; j++) {// 무게를 버틸때 까지
				dp[i][j] = dp[i-1][j];
				
				if (j >= W[i]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]]+V[i]);
				}
				
				if (dp[i][j]>=M) {
					answer = Math.min(answer, j);
				}
			}
		}
		
		System.out.println(answer);
	}
}
