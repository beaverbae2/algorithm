package A2020_12_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//DP
public class BOJ_2579_계단오르기 {
	static int N;
	static int[] k;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		k = new int[N+1];
		for (int i = 1; i <= N; i++) {
			k[i] = Integer.parseInt(br.readLine());
		}
		
		if(N==1) {
			System.out.println(k[1]);
		}else if(N==2) {
			System.out.println(k[1]+k[2]);
		}else {
			// set init value
			dp = new int[N+1];
			dp[1] = k[1];
			dp[2] = k[1]+k[2];
			
			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i-3]+k[i-1]+k[i], dp[i-2]+k[i]);
			}
			System.out.println(dp[N]);
		
		}
	}
}
