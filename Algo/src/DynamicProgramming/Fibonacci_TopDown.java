package DynamicProgramming;

/*
 * time complexity : O(N)
 * TopDown : solved by memoization 
 *
 */
public class Fibonacci_TopDown {
	static long[] dp;
	
	public static void main(String[] args) {
		int N = 51;
		dp = new long[N];
		System.out.println("fibo(5) : "+fibo(5));
		System.out.println("fibo(50) : "+fibo(50));
	}
	
	private static long fibo(int n) {
		if(n==1||n==2) dp[n] = 1;
		if(dp[n]!=0) return dp[n];
		
		dp[n] = fibo(n-1) + fibo(n-2);
		return dp[n];
	}
}
