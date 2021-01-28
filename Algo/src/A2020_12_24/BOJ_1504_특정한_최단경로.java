package A2020_12_24;

import java.util.*;
import java.io.*;

public class BOJ_1504_특정한_최단경로 {
	static List<Node>[] graph;
	static int[] dist;
	static int V, E;
	static int v1, v2;
	static final int INF = 987654321;
	
	
	static class Node implements Comparable<Node>{
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [b=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		dijkstra(v1, v2);
		
		int answer = INF;
		int e1 = dist[v2];
		if(e1 == INF) System.out.println(-1);
		else {
			dijkstra(1, v1);
			int e2 = dist[v1];
			dijkstra(V, v2);
			int e3 = dist[v2];
			int sum = e1 + e2 + e3;
			if(e2 != INF && e3 != INF) {
				answer = Math.min(answer, sum);
			}
			
			dijkstra(1, v2);
			e2 = dist[v2];
			dijkstra(V, v1);
			e3 = dist[v1];
			sum = e1 + e2 + e3;
			if(e2 != INF && e3 != INF) {
				answer = Math.min(answer, sum);
			}
			
			if(answer == INF) System.out.println(-1);
			else System.out.println(answer);
		}
	}

	static void dijkstra(int v1, int v2) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		
		pq.add(new Node(v1, 0));
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		dist[v1] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;
			
			if (visited[v]) continue;
			visited[v] = true;
			
			for (Node n : graph[v]) {
				int next_v = n.v;
				int next_w = n.w;
				
				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;
					pq.add(new Node(next_v, dist[next_v]));
				}
			}
			
			if (v==v2) break;
		}
	}
}
