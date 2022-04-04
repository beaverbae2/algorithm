package A2022_04_04;

import java.util.*;
import java.io.*;

/**
 * Divide and Conquer
 * 34MIN
 * @author beaverbae
 * - 리턴타입이 void가 아닌 재귀함수 실행 과정
 *
 */

public class BOJ_1629_곱셈 {
	static HashMap<Long, Long> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long A, B, C;
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map.put(1L,  A % C);
		System.out.println(pow(A, B, C));
	}
	
	private static long pow(long a, long b, long c) {
		if (map.containsKey(b)) return map.get(b);
		
		if (b % 2 == 0) {
			map.put(b, (pow(a, b/2, c) % c * pow(a, b/2, c) % c) % c);
		} else {
			map.put(b, (pow(a, b/2, c) % c * pow(a, b/2, c) % c * a % c) % c);
		}
		
		return map.get(b);
	}
}
