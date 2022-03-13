package A2022_03_13;

import java.util.*;
import java.io.*;

/**
 * String
 * 20MIN
 * @author beaverbae
 *
 */

public class BOJ_20437_문자열_게임2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			char[] input = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			for (char c = 'a'; c <= 'z'; c++) {
				Deque<Integer> dq = new ArrayDeque<>();
				for (int i = 0; i < input.length; i++) {
					if (c == input[i]) dq.add(i);
					if (dq.size() == K) {
						int l = dq.getLast() - dq.poll() + 1;
						min = Math.min(min, l);
						max = Math.max(max, l);
					}
				}
			
			}
			
			if (min == Integer.MAX_VALUE) sb.append("-1");
			else sb.append(min).append(" ").append(max);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
