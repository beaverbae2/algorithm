package A2021_09_24;

import java.util.*;
import java.io.*;

/**
 * Math
 * 6MIN
 * @author beaverbae
 *
 */

public class BOJ_1978_소수_찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, ans;
		int[] arr;
		
		N = Integer.parseInt(br.readLine());
		ans = 0;
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int n : arr) {
			if (isPrime(n)) ans++;
		}
		
		System.out.println(ans);
	}
	
	private static boolean isPrime(int n) {
		if (n == 0 || n == 1) return false;
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (n % i == 0) return false; 
		}
		
		return true;
	}
}
