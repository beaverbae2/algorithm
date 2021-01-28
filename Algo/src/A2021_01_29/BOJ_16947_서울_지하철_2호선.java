package A2021_01_29;

import java.util.*;
import java.io.*;

/**
 * DFS + BFS
 * 
 * @author beaverbae
 *
 */

public class BOJ_16947_서울_지하철_2호선 {
	static List<Integer>[] graph;
	static boolean[] visited;
	static boolean[] isCycle;
	static int N;
	static boolean flag;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		for (int v = 1; v <= N; v++) {
			visited = new boolean[N + 1];
			isCycle = new boolean[N + 1];
			visited[v] = true;
			checkCycle(v, v, 0);
			if (flag) {
				break;
			}
		}
		dist = new int[N + 1];
		for (int v = 1; v <= N; v++) {
			if (!isCycle[v])
				continue;

			bfs(v);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dist[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void checkCycle(int start_v, int v, int cnt) {
		if (flag)
			return;

		if (cnt >= 2) {
			for (int next_v : graph[v]) {
				if (visited[next_v] && next_v == start_v) {
					flag = true;
					break;
				}
			}
			if (flag) {
				for (int i = 0; i < visited.length; i++) {
					isCycle[i] = visited[i];
				}
				return;
			}
		}

		for (int next_v : graph[v]) {
			if (visited[next_v])
				continue;

			visited[next_v] = true;
			checkCycle(start_v, next_v, cnt + 1);
			visited[next_v] = false;
		}
	}

	private static void bfs(int start_v) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(new Node(start_v, 0));
		visited[start_v] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int v = node.v;
			int d = node.d;

			for (int next_v : graph[v]) {
				if (isCycle[next_v] || visited[next_v])
					continue;

				dist[next_v] = d + 1;
				visited[next_v] = true;
				q.offer(new Node(next_v, d + 1));
			}
		}
	}

	static class Node {
		int v, d;

		public Node(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", d=" + d + "]";
		}
	}
}
