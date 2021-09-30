package A2021_10_01;

import java.util.*;
import java.io.*;

/**
 * Euclidean Algorithm
 * @author beaverbae
 * 유클리드 호제법 : 최대공약수를 O(log N)로 구할 수 있음
 */

public class BOJ_2609_최대공약수와_최소공배수_유클리드호제법 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int n1 = Math.max(N, M);
		int n2 = Math.min(N, M);
		
		int gcd = gcd(n1, n2);
		int lcm = n1 * n2 / gcd;
		
		System.out.println(gcd);
		System.out.println(lcm);
	}
	
	private static int gcd(int n1, int n2) {
		if (n1 % n2 == 0) return n2;
		return gcd(n2, n1 % n2);
	}
}
