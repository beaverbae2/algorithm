package A2021_02_23;

import java.util.*;
import java.io.*;

/**
 * Fail to Solve(Topological Sort)
 * 
 * @author beaverbae
 *
 */

public class BOJ_2056_작업_fail {
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
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(1, times[1]));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			int v = node.v;
			int time = node.time;
			answer = time;

			for (int next_v : graph[v]) {
				enter_degree[next_v]--;
				if (enter_degree[next_v] == 0) {
					pq.offer(new Node(next_v, time + times[next_v]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int v, time;

		public Node(int v, int time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", time=" + time + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
}
