package A2021_03_15;

import java.util.*;
import java.io.*;

public class BOJ_2581_소수 {
	static int sum;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		
		for (int n = a ; n <= b; n++) {
			if (isPrime(n)) {
				sum += n;
				min = Math.min(min, n);
			}
		}
		
		if (sum != 0) {
			System.out.println(sum);
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	}

	private static boolean isPrime(int n) {
		if (n == 1) return false;
		
		for (int i = 2; i < n; i++) {
			if (n % i == 0) return false;
		}
		
		return true;
	}
	
	
}
