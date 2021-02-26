package A2021_02_26;

import java.util.*;
import java.io.*;

public class BOJ_1934_최소공배수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			int max = Math.max(a, b);
			int min = Math.min(a, b);
			
			int g = gcd(max, min);
			
			int lcm = g * min/g * max/g;
			sb.append(lcm).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// 유클리드 호제법 : a >= b
	private static int gcd(int a, int b) {
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
	
}
