package A2020_12_27;


//피보나치
public class PGS_멀리뛰기 {
	public long solution(int n) {
		long answer = 0;
		long[] dp = new long[n+1];
		final long MOD = 1234567;
		
		if (n >= 1) {
			dp[1] = 1;
		}
		if (n >= 2) {
			dp[2] = 2;
		}
		
		if (n >= 3) {
			for (int i = 3; i < dp.length; i++) {
				dp[i] = (dp[i-2]+dp[i-1])%MOD;
			}
		}
		
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(new PGS_멀리뛰기().solution(4));
		System.out.println(new PGS_멀리뛰기().solution(3));
	}
}
