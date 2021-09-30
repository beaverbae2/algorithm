package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * Heap
 * 4MIN
 * @author beave
 *
 */

public class BOJ_1927_최소_힙 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pq.isEmpty()) sb.append(0);
				else sb.append(pq.poll());
				sb.append("\n");
			} else pq.add(x);
		}
		
		System.out.println(sb);
	}
}
