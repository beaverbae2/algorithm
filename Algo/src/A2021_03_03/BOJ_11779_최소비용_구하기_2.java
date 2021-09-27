package A2021_03_03;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 26MIN
 * @author beaverbae
 *
 */

public class BOJ_11779_최소비용_구하기_2 {
	static List<Node>[] graph;
	static StringBuilder sb;
	static int V, E;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		E = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, w));
		}

		sb = new StringBuilder();
		int start_v, end_v;
		StringTokenizer st = new StringTokenizer(br.readLine());
		start_v = Integer.parseInt(st.nextToken());
		end_v = Integer.parseInt(st.nextToken());

		dijkstra(start_v, end_v);
		System.out.println(sb.toString());
	}

	private static void dijkstra(int start_v, int end_v) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V + 1];
		final int INF = 987654321;
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[V + 1];
		HashMap<Integer, Integer> parent = new HashMap<>();

		pq.offer(new Node(start_v, 0));
		dist[start_v] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;

			if (visited[v])
				continue;
			visited[v] = true;

			for (Node next : graph[v]) {
				int next_v = next.v;
				int next_w = next.w;

				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;

					pq.offer(new Node(next_v, dist[next_v]));
					parent.put(next_v, v);
				}
			}
		}

		Stack<Integer> stack = new Stack<>();
		stack.push(end_v);

		int v = end_v;
		while (true) {
			if (!parent.containsKey(v))
				break;

			int parent_v = parent.get(v);
			stack.push(parent_v);
			v = parent_v;
		}

		int weight = dist[end_v];
		int size = stack.size();
		sb.append(weight).append("\n").append(size).append("\n");

		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
	}

	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
