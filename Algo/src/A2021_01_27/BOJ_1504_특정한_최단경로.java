package A2021_01_27;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 
 * @author beaverbae
 *
 */

public class BOJ_1504_특정한_최단경로 {
	static List<Node>[] graph;
	static int V, E;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	
		graph = new List[V+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
		
			graph[start].add(new Node(end, weight));
			graph[end].add(new Node(start, weight));
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		
		int answer = INF;
		int temp1 = INF, temp2 = INF;
		
		int a = dijkstra(1,v1);
		int b = dijkstra(v1,v2);
		int c = dijkstra(v2,V);
		
		if (a != INF && b != INF && c != INF) {
			temp1 = a+b+c; 
		}
		
		a = dijkstra(1,v2);
		c = dijkstra(v1,V);
		
		if (a != INF && b != INF && c != INF) {
			temp2 = a+b+c; 
		}
		
		answer = Math.min(temp1, temp2);
		
		if (answer == INF) System.out.println(-1);
		else System.out.println(answer);
	}
	
	private static int dijkstra(int start, int dest) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		int[] dist = new int[V+1];
		
		Arrays.fill(dist, INF);
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;
			
			if (v == dest) return dist[v];// 중요
			
			if (visited[v]) continue;
			visited[v] = true;
		
			for (Node next : graph[v]) {
				if (dist[next.v] > dist[v] + next.w) {
					dist[next.v] = dist[v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		return INF;
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
			return Integer.compare(this.w, o.w);
		}
	}
}
