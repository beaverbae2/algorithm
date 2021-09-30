package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * Math
 * 10MIN
 * @author beaverbae
 * while 조건문 주의
 */

public class BOJ_2609_최대공약수와_최소공배수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		// 최대공약수 찾기
		int gcd = Math.min(N, M);
		while (gcd > 0) {
			if (N % gcd == 0 && M % gcd == 0) break;
			gcd--;
		}
		
		
		int lcm = Math.max(N, M);
		while (lcm % N != 0 || lcm % M != 0) {
			lcm += gcd;
		}
		
		System.out.println(gcd);
		System.out.println(lcm);
	}
}
