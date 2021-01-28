package A2020_12_23;

import java.util.*;
import java.io.*;

public class BOJ_1753_최단경로 {
	static List<Pair>[] graph;
	static int[] dist;
	static final int INF = 987654321;
	static int V, E;
	
	static class Pair implements Comparable<Pair> {
		int v, w;

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Pair [v=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Pair o) {
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
		
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		
		int start_v = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Pair(b,w));
		}
		
		dijkstra(start_v);
	
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < dist.length; i++) {
			if(dist[i] != INF) sb.append(dist[i]).append("\n");
			else sb.append("INF").append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(int start_v) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		
		dist[start_v] = 0;
		//visited[start_v] = true;
		pq.add(new Pair(start_v, 0));
		
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			int v = p.v;
			
			if (visited[v]) continue;
			visited[v] = true;
			
			for (Pair next : graph[v] ) {
				int next_v = next.v;
				int next_w = next.w;
				
				//if (visited[next_v]) continue;
				
				if (dist[next_v] > dist[v]+next_w) {
					dist[next_v] = dist[v]+next_w;
					//visited[next_v] = true;
					pq.add(new Pair(next_v, dist[next_v]));
				}
			}
			
		}
	}
}