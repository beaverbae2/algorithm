package A2021_06_28;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 20MIN
 * 
 * @author beaverbae
 *
 */

public class BOJ_14284_간선_이어가기_2 {
	static int N, M;
	static List<Node>[] graph;
	static int[] dist;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		for (int v = 1; v <= N; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra(start, dest));
	}
	
	private static int dijkstra(int start, int dest) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		for (int v = 1; v <= N; v++) {
			dist[v] = INF;
		}
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int v = cur.v;
			
			if (visited[v]) continue;
			visited[v] = true;
			
			if (v == dest) {
				return dist[v];
			}
			
			for (Node next : graph[v]) {
				int next_v = next.v;
				int next_w = next.w;
				
				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;
					pq.add(new Node(next_v, dist[next_v]));
				}
			}
		}
		
		return -1;// 실제로 동작X
	} 
	
	static class Node implements Comparable<Node> {
		int v, w;
		
		public Node (int v, int w) {
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
