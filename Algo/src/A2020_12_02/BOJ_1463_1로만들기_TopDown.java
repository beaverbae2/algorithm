package A2020_12_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class BOJ_1463_1로만들기_TopDown {
	static int[] dp;
	static int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		dfs(N);
		System.out.println(dp[N]);
	}

	private static int dfs(int n) {
		if(n==1) return 0;
		
		if(dp[n]!=0) return dp[n];
		
		int a=INF,b=INF,c=INF;
		if(n%3==0) a = 1+dfs(n/3);
		if(n%2==0) b = 1+dfs(n/2);
		c = 1+dfs(n-1); 
		
		dp[n] = min(a,b,c);
		return dp[n];
	}

	private static int min(int a, int b, int c) {
		if(a<=b&&b<=c) return a;
		if(a<=c&&c<=b) return a;
		if(b<=a&&a<=c) return b;
		if(b<=c&&c<=a) return b;
		if(c<=a&&a<=b) return c;
		if(c<=b&&b<=a) return c;
		
		return 0;
	}
}
