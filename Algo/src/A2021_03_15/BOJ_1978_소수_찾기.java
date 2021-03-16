package A2021_03_15;

import java.util.*;
import java.io.*;

public class BOJ_1978_소수_찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int n : arr) {
			if (isPrime(n)) answer++;
		}
		
		System.out.println(answer);
	}
	
	private static boolean isPrime(int n) {
		if (n == 1) return false;
		
		for (int i = 2; i < n; i++) {
			if (n % i == 0) return false;
		}
		
		return true;
	}
}
