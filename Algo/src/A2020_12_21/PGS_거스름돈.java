package A2020_12_21;

import java.util.*;

public class PGS_거스름돈 {
	public int solution(int n, int[] money) {
		int answer = 0;
		int mod = 1000000007;
		Arrays.sort(money);

		int[] dp = new int[n + 1];

		for (int i = 0; i < dp.length; i++) {
			if(i % money[0] == 0) dp[i] = 1;
		}

		for (int i = 1; i < money.length; i++) {
			for (int j = money[i]; j < dp.length; j++) {
				dp[j] += dp[j-money[i]]%mod;
			}
		}
		
		answer = dp[n];

		return answer;
	}

	public static void main(String[] args) {
		PGS_거스름돈 a = new PGS_거스름돈();
		System.out.println(a.solution(5, new int[] { 1, 2, 5 }));

	}
}
