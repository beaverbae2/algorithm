package A2021_02_23;

import java.util.*;
import java.io.*;

/**
 * Topological Sort + DP
 * 
 * @author beaverbae
 *
 */

public class BOJ_1516_게임_개발 {
	static List<Integer>[] graph;
	static int[] enter_degree;
	static int[] times;
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		enter_degree = new int[N + 1];
		times = new int[N + 1];

		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int v = 1; v <= N; v++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			times[v] = time;

			while (true) {
				int pre_vertex = Integer.parseInt(st.nextToken());
				if (pre_vertex == -1)
					break;

				graph[pre_vertex].add(v);
				enter_degree[v]++;
			}
		}

		sb = new StringBuilder();
		topological_sort();
		System.out.println(sb.toString());
	}

	private static void topological_sort() {
		Queue<Integer> q = new LinkedList<>();
		int[] dp = new int[N + 1];

		for (int v = 1; v < enter_degree.length; v++) {
			if (enter_degree[v] != 0)
				continue;

			q.offer(v);
			dp[v] = times[v];
		}

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : graph[v]) {
				enter_degree[next_v]--;
				dp[next_v] = Math.max(dp[next_v], dp[v] + times[next_v]);

				if (enter_degree[next_v] == 0) {
					q.offer(next_v);
				}
			}
		}

		for (int i = 1; i < dp.length; i++) {
			sb.append(dp[i]).append("\n");
		}
	}
}
