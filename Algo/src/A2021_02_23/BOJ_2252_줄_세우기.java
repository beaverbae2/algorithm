package A2021_02_23;

import java.util.*;
import java.io.*;

/**
 * Topological Sort
 * 
 * @author beaverbae
 *
 */

public class BOJ_2252_줄_세우기 {
	static List<Integer>[] graph;// 방향 그래프
	static int[] enter_degree;// 진입 차수
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		enter_degree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
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
		// 초기화 - 진입 차수가 0인 모든 정점 추가
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < enter_degree.length; i++) {
			if (enter_degree[i] != 0)
				continue;
			q.offer(i);
			sb.append(i).append(" ");
		}

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next : graph[v]) {
				enter_degree[next]--;
				if (enter_degree[next] == 0) {
					q.offer(next);
					sb.append(next).append(" ");
				}
			}
		}
	}
}
