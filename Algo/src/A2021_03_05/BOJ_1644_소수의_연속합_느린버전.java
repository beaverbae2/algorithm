package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * Two Pointer, Prime Number
 * @author beaverbae
 * @see https://myjamong.tistory.com/139
 *
 */

public class BOJ_1644_소수의_연속합_느린버전 {
	static ArrayList<Integer> primes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		primes =  new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		
		for (int n = 2; n <= N; n++) {
			if (isPrime(n)) {
				primes.add(n);
			}
		}
		
		int start = 0;
		int end = 0;
		int size = primes.size();
		int answer = 0;
		int sum = 0;
		
		while (true) {
			if (sum >= N) {
				if (sum == N) answer++;
				sum -= primes.get(start++);
				if (start > end) end = start;
			} else {
				if (end >= size) break;
				sum += primes.get(end++);
			}
		}
		System.out.println(answer);
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i*i <= n; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
}
