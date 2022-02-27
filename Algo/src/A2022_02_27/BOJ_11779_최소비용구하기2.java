package A2022_02_27;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 39MIN
 * @author beaverbae
 * - 최적 경로 출력
 */

public class BOJ_11779_최소비용구하기2 {
	static int N, M;
	static List<Node>[] graph;
	static StringBuilder sb;
	static int s, e;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		sb = new StringBuilder();
		for (int v = 1; v <= N; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, w));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		dijkstra();
		System.out.println(sb);
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		int[] prev = new int[N+1];
		final int INF = 987664321;
		
		Arrays.fill(dist, INF);
		pq.offer(new Node(s, 0));
		dist[s] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int v = n.v;
			int w = n.w;
			
			if (visited[v]) continue;
			visited[v] = true;
			
			if (v == e) break;
			
			for (Node next : graph[v]) {
				if (dist[next.v] > dist[v] + next.w) {
					dist[next.v] = dist[v] + next.w;
					prev[next.v] = v;
					
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		LinkedList<Integer> list = new LinkedList<>();
		int v = e;
		list.addFirst(v);
		while(v != s) {
			v = prev[v];
			list.addFirst(v);
		}
		
		sb.append(dist[e]).append("\n");
		sb.append(list.size()).append("\n");
		while(!list.isEmpty()) {
			sb.append(list.removeFirst()).append(" ");
		}
		sb.append("\n");
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