package A2020_12_23;

//피보나치
public class PGS_2xn타일링 {
	private final int MOD = 1000000007;
	
	public int solution(int n) {
        int[] dp = new int[n+1];
        
        if (n >= 1) {
        	dp[1] = 1;
        }if (n >= 2) {
        	dp[2] = 2;
        }if (n >= 3) {
        	for (int i = 3; i < dp.length; i++) {
				dp[i] = (dp[i-1] + dp[i-2])%MOD;
			}
        }
        
        
		return dp[n];
    }
	
	public static void main(String[] args) {
		
	}
}
