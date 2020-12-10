package A2020_12_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11726_2xn타일링_TopDown {
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		
		dfs(N);
		System.out.println(dp[N]);
	}

	private static int dfs(int n) {
		//System.out.println("f("+n+")");
		if(n==1) return dp[1] = 1;
		if(n==2) return dp[2] = 2;
		
		if(dp[n]!=0) return dp[n];
		
		return dp[n] = (dfs(n-1) + dfs(n-2))%10007;
	}
}
