package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * Heap
 * 5MIN
 * @author beaverbae
 *
 */

public class BOJ_11279_최대_힙 {
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x == 0) {
				if (pq.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
			}
			
			else {
				pq.offer(x);
			}
		}
		
		System.out.println(sb.toString());
	}
}
