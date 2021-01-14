package A2021_01_15;

import java.util.*;
import java.io.*;

public class BOJ_11053_가장_긴_증가하는_부분_수열 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		Arrays.fill(dp, 1);
		int[] arr = new int[N+1];
		int answer = dp[1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
