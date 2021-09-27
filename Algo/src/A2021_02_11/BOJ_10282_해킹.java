package A2021_02_11;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 
 * @author beaverbae
 *
 */

public class BOJ_10282_해킹 {
	static List<Node>[] graph;
	static int[] dist;
	static int V, E, start_v;
	static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			start_v = Integer.parseInt(st.nextToken());

			graph = new List[V + 1];
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				graph[b].add(new Node(a, w));
			}

			dijkstra();

			int total_v = 0;
			int time = 0;

			for (int i = 1; i < dist.length; i++) {
				if (dist[i] == INF)
					continue;

				time = Math.max(time, dist[i]);
				total_v++;
			}
			sb.append(total_v).append(" ").append(time).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[V + 1];

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
				}
			}
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
