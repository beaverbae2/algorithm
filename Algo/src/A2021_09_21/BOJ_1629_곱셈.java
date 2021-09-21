package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * 13MIN
 * Divide and Conquer
 * @author beaverbae
 * 호출은 최소화, 나머지 연산 까다로웠음
 */

public class BOJ_1629_곱셈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
	
		System.out.println(pow(a, b, c));
	}
	
	private static long pow(long a, long b, long c) {
		if (b == 0) return 1;
		if (b == 1) return a % c;
	
		long n = pow(a, b/2, c) % c;
		if (b % 2 == 0) return n * n % c;
		return a * n % c * n % c;
	}
}
