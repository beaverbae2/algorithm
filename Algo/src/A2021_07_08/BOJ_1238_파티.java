package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 26MIN
 * @author beaverbae
 * 
 */

public class BOJ_1238_파티 {
	static int N, M, X;
	static List<Node>[] graph1, graph2;
	static int[] dist1, dist2;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
	
		graph1 = new List[N+1];
		graph2 = new List[N+1];
		dist1 = new int[N+1];
		dist2 = new int[N+1];
		for (int i = 1; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
			dist1[i] = INF;
			dist2[i] = INF;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph1[a].add(new Node(b, w));
			graph2[b].add(new Node(a, w));
		}
		
		dijkstra(graph1, dist1, X);
		dijkstra(graph2, dist2, X);
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, dist1[i] + dist2[i]);
		}
		
		System.out.println(ans);
	}	
	
	private static void dijkstra(List<Node>[] graph, int[] dist, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int v = cur.v;
			
			if (visited[v]) continue;
			visited[v] = true;
			
			for (Node next : graph[v]) {
				if (dist[next.v] > dist[v] + next.w) {
					dist[next.v] = dist[v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
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
			return this.w - o.w;
		}
	}
}
