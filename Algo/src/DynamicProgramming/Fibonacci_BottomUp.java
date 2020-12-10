package DynamicProgramming;


/*
 * time complexity : O(N)
 * BottomUp : solved by loop
 */
public class Fibonacci_BottomUp {
	static long[] dp;
	
	public static void main(String[] args) {
		int N = 51;
		dp = new long[51];
		 
		// set init values
		dp[1] = 1;
		dp[2] = 1;

		// fill dp array by recurrence relation 
		for (int i = 3; i < N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		
		System.out.println("fibo(5) : "+dp[5]);
		System.out.println("fibo(50) : "+dp[50]);//too much time 
	}
	
	
}
