package A2021_08_31;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 17MIN
 * @author beaverbae
 * BFS처럼 방문체크함
 */

public class BOJ_18352_특정거리의_도시_찾기 {
	static int N, M, K, X;
	static List<Node>[] graph;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		graph[X].add(new Node(X, 0));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, 1));
		}
		
		sb = new StringBuilder();
		bfs(X);
		
		if (sb.length() == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sb);
		}
		
	}
	
	private static void bfs(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		
		pq.add(new Node(start, 0));
		visited[start] = true;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int v = cur.v;
			int w = cur.w;

			if (w == K+1) break;
			if (w == K) sb.append(v).append("\n");
			
			for (Node next : graph[v]) {
				int next_v = next.v;
				if (!visited[next_v]) {
					visited[next_v] = true;
					pq.add(new Node(next_v, w + 1));
				}
			}
		}
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
			if (this.w != o.w) return this.w - o.w;
			
			return this.v - o.v;
		}
	}
}
