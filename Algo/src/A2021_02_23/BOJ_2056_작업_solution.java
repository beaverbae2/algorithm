package A2021_02_23;

import java.util.*;
import java.io.*;

/**
 * Topological Sort + DP
 * 
 * @author beaverbae
 * @see https://steady-coding.tistory.com/182
 *
 */

public class BOJ_2056_작업_solution {
	static List<Integer>[] graph;
	static int[] enter_degree;
	static int[] times;
	static int N;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		enter_degree = new int[N + 1];
		times = new int[N + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			times[i] = time;

			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int pre_work = Integer.parseInt(st.nextToken());
				graph[pre_work].add(i);
				enter_degree[i]++;
			}
		}

		topological_sort();
		System.out.println(answer);
	}

	private static void topological_sort() {
		Queue<Integer> q = new LinkedList<>();
		int[] dp = new int[N + 1];
		for (int i = 1; i < enter_degree.length; i++) {
			if (enter_degree[i] != 0)
				continue;
			q.offer(i);
			dp[i] = times[i];
		}

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : graph[v]) {
				enter_degree[next_v]--;
				dp[next_v] = Math.max(dp[next_v], dp[v] + times[next_v]);// next_v로 들어오는 노드가 여러 개 일때를 생각해보자

				if (enter_degree[next_v] == 0) {
					q.offer(next_v);
				}
			}
		}

		for (int i = 1; i < dp.length; i++) {
			answer = Math.max(answer, dp[i]);
		}
	}
}
