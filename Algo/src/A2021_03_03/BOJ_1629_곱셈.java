package A2021_03_03;

import java.util.*;
import java.io.*;

/**
 * Divide and Conquer
 * 25MIN
 * @author beaverbae
 *
 */

public class BOJ_1629_곱셈 {
	static long A, B, C;
	static HashMap<Integer, Long> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		map = new HashMap<>();
		map.put(0, 1L);
		map.put(1, A%C);
		System.out.println(mul(B));
	}

	private static long mul(long x) {
		if (map.containsKey((int) x)) return map.get((int) x);

		if (x % 2 == 0) {
			long result = (mul(x / 2) % C * mul(x / 2) % C) % C;
			
			map.put((int) x, result);
			return result;
		} else {
			long result = ((((mul(x / 2) % C * mul(x / 2) % C) % C) * A) % C) % C;
			map.put((int) x, result);
			return result;
		}
	}
}
