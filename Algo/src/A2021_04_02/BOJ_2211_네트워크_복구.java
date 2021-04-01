package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 41MIN -> see algorithm classification
 * @author beaverbae
 *
 */

public class BOJ_2211_네트워크_복구 {
	static List<Pair>[] graph;// 그래프
	static int V, E;
	static int cnt;// 복구 간선 개수
 	static StringBuilder sb;// 복구 간선
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[V+1];
		sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			// 양방향이므로
			graph[a].add(new Pair(b, w));
			graph[b].add(new Pair(a, w));
		}
		
		
		dijkstra(1);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	
	
	
	private static void dijkstra(int start_v) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V+1];
		final int INF = 987654321;
		for (int i = 1; i < dist.length; i++) {
			dist[i] = INF;
		}
		
		boolean[] visited = new boolean[V+1];
		
		pq.add(new Node(0, 1, 0));
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int prev_v = node.prev_v;
			int v = node.v;
			int w = node.w;
			
			if (visited[v]) continue;
			visited[v] = true;

			// 여기까지 왔으면 최단 경로인 경우임
			
			if (cnt >= 1) {// 0인 경우는 뺴고
				sb.append(prev_v).append(" ").append(v).append("\n");
			}
			cnt++;// 복구 간선 추가
			
			for (Pair next : graph[v]) {
				int next_v = next.v;
				int next_w = next.w;
				
				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;
					pq.add(new Node(v, next_v, dist[next_v]));
				}
			}
		}
		cnt--;
	}
	
	static class Pair {
		int v, w;

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Pair [v=" + v + ", w=" + w + "]";
		}
		
		
	}

	static class Node implements Comparable<Node>{
		int prev_v, v, w;// prev_v : 이전 노드, v : 현재 노드, w : 가중치

		public Node(int prev_v, int v, int w) {
			this.prev_v = prev_v;
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [prev_v=" + prev_v + ", v=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}	
}
