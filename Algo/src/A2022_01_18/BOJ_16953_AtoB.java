package A2022_01_18;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 16MIN
 * @author beaverbae
 *
 */

public class BOJ_16953_AtoB {
	static long A, B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
	
		System.out.println(bfs());
	}

	private static long bfs() {
		Queue<long[]> q = new LinkedList<>();
		HashSet<Long> set = new HashSet<>();
		
		q.offer(new long[] {A, 1});
		set.add(A);
		
		while (!q.isEmpty()) {
			long[] arr = q.poll();
			long v = arr[0];
			long cnt = arr[1];
			
			if (v == B) return cnt;
			
			// *2 하기
			long next = v * 2;
			if (next <= B && !set.contains(next)) {
				q.offer(new long[] {next, cnt + 1});
				set.add(next);
			}
			// 왼쪽에 1추가
			next = v * 10 + 1;
			if (next <= B &&!set.contains(next)) {
				q.offer(new long[] {next, cnt + 1});
				set.add(next);
			}
		}
		
		return -1;
	}
}
