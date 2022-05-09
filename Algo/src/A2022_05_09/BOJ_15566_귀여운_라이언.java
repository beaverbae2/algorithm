package A2022_05_09;

import java.util.*;
import java.io.*;

/**
 * Data Structure
 * 13 MIN
 * @author beaverbae
 * 
 */

public class BOJ_15566_귀여운_라이언 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		Queue<Integer> q = new LinkedList<>();
		Deque<Integer> dq = new ArrayDeque<>();
		
		final int INF = 1000001;
		int ans = INF;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (n == 1) q.offer(i);
		}
		
		while (!q.isEmpty()) {
			dq.add(q.poll());
			
			if (dq.size() == K) {
				ans = Math.min(ans, dq.getLast() - dq.getFirst() + 1);
				dq.poll();
			}
		}
		
		if (ans == INF) ans = -1;
		
		System.out.println(ans);
	}
}
