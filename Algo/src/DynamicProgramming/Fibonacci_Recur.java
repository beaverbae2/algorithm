package DynamicProgramming;


//time complexity : O(2^N)
public class Fibonacci_Recur {
	public static void main(String[] args) {
		System.out.println("fibo(5) : "+fibo(5));
		System.out.println("fibo(50) : "+fibo(50));//too much time 
	}
	
	private static long fibo(int n) {
		if(n==1||n==2) return 1;
		return fibo(n-1) + fibo(n-2);
	}
}
