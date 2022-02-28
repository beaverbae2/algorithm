package A2022_02_28;

import java.util.*;
import java.io.*;

/**
 * Queue
 * 15MIN
 * @author beaverbae
 *
 */

public class BOJ_11866_요세푸스문제0 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		int cnt = 1;
		
		sb.append("<");
		for (int n = 1; n <= N; n++) {
			q.offer(n);
		}
		
		while (!q.isEmpty()) {
			if (cnt == K) {
				sb.append(q.poll());
				if (q.size() > 0) sb.append(",").append(" ");
				cnt = 1;
			} else {
				q.offer(q.poll());
				cnt++;
			}
		}
		
		sb.append(">");
		System.out.println(sb);
	}
}
