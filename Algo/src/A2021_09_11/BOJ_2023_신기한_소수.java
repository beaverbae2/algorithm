package A2021_09_11;

import java.util.*;
import java.io.*;

/**
 * Backtracking
 * 21MIN
 * 작은 소수부터 비교해야한다
 * @author beaverbae
 *
 */

public class BOJ_2023_신기한_소수 {
	static final int TEN = 10;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		int start = (int) Math.pow(TEN, N-1);
		int end = start * TEN;
		
		findCuriousPrime(start, end, N);
		System.out.println(sb);
	}
	
	private static void findCuriousPrime(int start, int end, int N) {
		for (int num = start; num < end; num++) {
			int cnt = 0;
			
			for (int i = start; i >= 1; i/=10) {
				int n = num / i;
				if (!isPrime(n)) break;
				
				cnt++;
			}
			
			if (cnt == N) {
				sb.append(num).append("\n");
			}
		}
	}
	
	private static boolean isPrime(int n) {
		if (n == 1) return false;
		
		int k = (int) Math.sqrt(n);
		for (int i = 2; i <= k; i++) {
			if (n % i == 0) return false;
		}
		
		return true;
	}
}
