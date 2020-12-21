package A2020_12_21;

import java.util.*;

public class PGS_거스름돈Fail {
	public int solution(int n, int[] money) {
		int answer = 0;
		int mod = 1000000007;
		Arrays.sort(money);

		int[][] dp = new int[n + 1][money.length];

		for (int i = 0; i < money.length; i++) {
			dp[0][i] = 1;
		}
		
		for (int i = 1; i < dp.length; ++i) {
			if (i % money[0] == 0) dp[i][0] = 1;
			
			for (int j = 1; j < dp[i].length; ++j) {
				if (i - money[j] >= 0) {
					dp[i][j] = (dp[i][j - 1] + dp[i - money[j]][j])%mod;
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}
		answer = dp[n][money.length - 1];

		return answer;
	}

	public static void main(String[] args) {
		PGS_거스름돈Fail a = new PGS_거스름돈Fail();
		System.out.println(a.solution(5, new int[] { 1, 2, 5 }));

	}
}
