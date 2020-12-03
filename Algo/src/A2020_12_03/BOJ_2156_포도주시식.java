package A2020_12_03;

import java.io.*;
import java.util.*;

public class BOJ_2156_포도주시식 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if(N>=1) {
			dp[1] = arr[1];
		}if(N >= 2) {
			dp[2] = arr[1]+arr[2];
		}if(N >= 3) {
			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i-3]+arr[i-1]+arr[i], Math.max(dp[i-2]+arr[i], dp[i-1]));
			}
		}
		
		System.out.println(dp[N]);
	}
}
