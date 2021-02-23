package A2021_02_24;

import java.util.*;
import java.io.*;

/**
 * Topological Sort + DP
 * 13MIN
 * @author beaverbae
 *
 */

public class BOJ_1005_ACM_Craft {
	static List<Integer>[] graph;
	static int[] enter_degree;
	static int[] times;
	static int N, K, end_v;

	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			graph = new List[N + 1];
			enter_degree = new int[N + 1];
			times = new int[N + 1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph[a].add(b);
				enter_degree[b]++;
			}
			end_v = Integer.parseInt(br.readLine());

			topological_sort();
		}

		System.out.println(sb.toString());
	}

	private static void topological_sort() {
		// 초기화
		Queue<Integer> q = new LinkedList<>();
		int[] dp = new int[N + 1];

		for (int i = 1; i < enter_degree.length; i++) {
			if (enter_degree[i] != 0)
				continue;

			q.offer(i);
			dp[i] = times[i];
		}

		// 탐색
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

		sb.append(dp[end_v]).append("\n");
	}
}
