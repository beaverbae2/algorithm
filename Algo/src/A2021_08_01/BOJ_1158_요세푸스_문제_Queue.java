package A2021_08_01;

import java.util.*;
import java.io.*;

/**
 * Queue
 * @author beaverbae
 *
 */

public class BOJ_1158_요세푸스_문제_Queue {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int N, K;
		Queue<Integer> q = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		for (int n = 1; n <= N; n++) {
			q.offer(n);
		}
		
		sb.append("<");
		while(true) {
			int cnt = 1;
			int n = 0;
			
			while (true) {
				n = q.poll();
				if (cnt == K) {
					break; 
				}
				q.offer(n);
				cnt++;
			}
			
			sb.append(n);
			if (!q.isEmpty()) {
				sb.append(",").append(" ");
			} else {
				break;
			}
		}
		sb.append(">");
		
		System.out.println(sb);
	}
}
