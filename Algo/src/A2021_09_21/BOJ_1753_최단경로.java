package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 12MIN
 * @author beaverbae
 * 준비물이 많아요
 */

public class BOJ_1753_최단경로 {
	static int V, E;
	static List<Node>[] graph;
	static int[] dist;
	static final int INF = 3000001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int start;
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	
		graph = new List[V+1];
		for (int v = 1; v <= V; v++) {
			graph[v] = new ArrayList<>();
		}
		
		start = Integer.parseInt(br.readLine());
		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
		}
		
		dijkstra(start);
		
		StringBuilder sb = new StringBuilder();
		for (int v = 1; v <= V; v++) {
			if (dist[v] == INF) sb.append("INF");
			else sb.append(dist[v]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		// 탐색시 재방문에 유의
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visited[cur.v]) continue;
			visited[cur.v] = true;// 현재 정점이 최소 거리를 가짐
			
			for (Node next : graph[cur.v]) {
				if (dist[next.v] > dist[cur.v] + next.w) {
					dist[next.v] = dist[cur.v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
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
		
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
}
