package A2022_03_30;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 30MIN
 * @author beaverbae
 * - 경로 없는 경우 생각 안함 : 문제 똑바로 보자..
 */

public class BOJ_1504_특정한최단경로 {
	static List<Node>[] graph;
	static int N, E;
	static final int INF = 200000001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		for (int v = 1; v < N+1; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int w = Integer.parseInt(st.nextToken()); 
		
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int via_w = dijkstra(v1, v2);
		int d1 = dijkstra(1, v1) + dijkstra(N, v2) + via_w;
		int d2 = dijkstra(1, v2) + dijkstra(N, v1) + via_w;
	
		if (d1 >= INF && d2 >= INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(Math.min(d1, d2));
	}
	
	private static int dijkstra(int s, int e) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, INF);
		pq.add(new Node(s, 0));
		dist[s] = 0;
		
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int v = n.v;
			int w = n.w;
			
			if (v == e) return w;
			
			for (Node next : graph[v]) {
				if (dist[next.v] > dist[v] + next.w) {
					dist[next.v] = dist[v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		return INF;
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
