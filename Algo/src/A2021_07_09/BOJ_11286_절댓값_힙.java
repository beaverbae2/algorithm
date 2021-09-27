package A2021_07_09;

import java.util.*;
import java.io.*;

/**
 * Heap
 * 8 MIN
 * @author beaverbae
 *
 */

public class BOJ_11286_절댓값_힙 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int n1 = Math.abs(o1);
				int n2 = Math.abs(o2);
				
				if (n1 != n2) {
					return n1 - n2;
				} else {
					return o1 - o2;
				}
			}
		});
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if (n == 0) {
				if (pq.isEmpty()) {
					sb.append(0);
				} else {
					sb.append(pq.poll());
				}
				sb.append("\n");
			} else {
				pq.add(n);
			}
		}
		
		System.out.println(sb.toString());
	}
}
