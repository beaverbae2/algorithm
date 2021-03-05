package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * Two Pointer, Prime Number(에라토스테네스의 체)
 * @author beaverbae
 * @see https://marobiana.tistory.com/91
 *
 */

public class BOJ_1644_소수의_연속합_빠른버전 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> primes =  new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isChecked = new boolean[N+1];
		isChecked[0] = true;
		isChecked[1] = true;
		for (int n = 2; n <= N; n++) {
			if (isChecked[n]) continue;
			for (int i = n+n; i <= N; i+=n) {
				isChecked[i] = true;
			}
		}
		
		for (int i = 2; i <= N; i++) {
			if (!isChecked[i]) {
				primes.add(i);
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
}
