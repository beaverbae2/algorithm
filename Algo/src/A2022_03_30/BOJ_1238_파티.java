package A2022_03_30;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 24MIN
 * @author beaverbae
 *
 */

public class BOJ_1238_파티 {
	static int N, M, X;
	static int[] x_dist;
	static List<Node>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		x_dist = new int[N+1];
		for (int v = 1; v < N+1; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, w));
		}
		
		dijkstra(X);
		
		for (int v = 1; v < N+1; v++) {
			if (v == X) continue;
			
			ans = Math.max(ans, dijkstra(v) + x_dist[v]);
		}
		
		System.out.println(ans);
	}
	
	private static int dijkstra(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		final int INF = 98765431;
		
		Arrays.fill(dist, INF);
		dist[s] = 0;
		pq.add(new Node(s, 0));
		
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int v = n.v;
			
			for (Node next : graph[v]) {
				if (dist[next.v] > dist[v] + next.w) {
					dist[next.v] = dist[v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		if (s == X) x_dist = dist;
		
		return dist[X];
	}
	
	static class Node implements Comparable<Node>{
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
