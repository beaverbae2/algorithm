package A2021_08_02;

import java.util.*;
import java.io.*;

/**
 * 13MIN
 * Topological sort
 * @author beaverbae
 *
 */

public class BOJ_1766_문제집 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] enterDegree;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		enterDegree = new int[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph[start].add(end);
			enterDegree[end]++;
		}
	
		sb = new StringBuilder();
		topological_sort();
		System.out.println(sb);
	}

	private static void topological_sort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (enterDegree[i] == 0) {
				pq.add(i);
			}
		}
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(" ");
			
			for (int next : graph[cur]) {
				enterDegree[next]--;
				if (enterDegree[next] == 0) {
					pq.add(next);
				}
			}
		}
	}
}
