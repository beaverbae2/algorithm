package A2020_12_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class BOJ_1463_1로만들기_BottomUp {
	static int[] dp;
	static int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		
		//set init value
		dp[1] = 0;
		if(N>=2) dp[2] = 1;
		if(N>=3) dp[3] = 1;
		
		if(N>3) {
			
			for (int i = 4; i <= N; i++) {
				int a = INF, b = INF, c = INF;
				if(i%3==0) a = 1+dp[i/3];
				if(i%2==0) b = 1+dp[i/2];
				c = 1+dp[i-1];
				dp[i] = min(a,b,c);
			}
		}
		System.out.println(dp[N]);
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
