package A2021_01_06;

import java.util.*;
import java.io.*;

public class BOJ_14226_이모티콘Fail {
	static int[] dp;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		
		dp = new int[1001];
		
		dp[1] = 0;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = i;
		}
		
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.min(dp[i], getMinByDivisor(i)); 
			dp[i-1] = Math.min(dp[i-1],dp[i]+1);
		}
		System.out.println(dp[S]);
	}

	private static int getMinByDivisor(int n) {
		int min = INF;
		
		for (int divisor = 2; divisor <=n ; divisor++) {
			int quotient = n/divisor;
			
			if (quotient < divisor) break;
			if (n%divisor != 0) continue;
			
			min = Math.min(min, dp[quotient]+dp[divisor]);
		}
		
		return min;
	}
}
