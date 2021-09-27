package A2021_02_26;

import java.util.*;
import java.io.*;

public class BOJ_2981_검문 {
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] sub = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			sub[i] = arr[i] - arr[0];
		}
		int g = gcd(sub[1], sub[0]);
		for (int i = 2; i < sub.length; i++) {
			g = gcd(sub[i], g);
		}
		
		sb = new StringBuilder();
		findDivisor(g);
		System.out.println(sb.toString());
	}

	private static void findDivisor(int n) {
		for (int i = 2; i <= n; i++) {
			if (n % i == 0) { 
				sb.append(i).append(" ");
			}
		}
	}

	private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}
