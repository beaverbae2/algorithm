package A2021_02_23;

import java.util.*;
import java.io.*;

/**
 * Topological Sort(Priority Queue)
 * 
 * @author beaverbae
 *
 */

public class BOJ_1766_문제집 {
	static List<Integer>[] graph;
	static int[] enter_degree;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		enter_degree = new int[N + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			enter_degree[b]++;
		}

		sb = new StringBuilder();
		topological_sort();
		System.out.println(sb.toString());
	}

	private static void topological_sort() {
		// 초기화
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int v = 1; v < enter_degree.length; v++) {
			if (enter_degree[v] != 0)
				continue;
			pq.offer(v);
		}

		// 탐색
		while (!pq.isEmpty()) {
			int v = pq.poll();
			sb.append(v).append(" ");

			for (int next_v : graph[v]) {
				enter_degree[next_v]--;
				if (enter_degree[next_v] == 0) {
					pq.offer(next_v);
				}
			}
		}
	}
}
