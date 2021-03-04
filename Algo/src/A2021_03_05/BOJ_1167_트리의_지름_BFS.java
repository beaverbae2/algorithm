package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 * @see https://dundung.tistory.com/34
 */

public class BOJ_1167_트리의_지름_BFS {
	static List<Node>[] graph;
	static int V;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V+1];
		
		for (int v = 1; v <= V; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int i = 1; i <= V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			while(true) {
				int b = Integer.parseInt(st.nextToken());
				
				if (b == -1) break;
				
				int w = Integer.parseInt(st.nextToken());
				graph[a].add(new Node(b, w));
			}
		}
		
		int[] dist = bfs(1);
		
		int max_v = -1;// 가장 먼 정점
		int max = 0;
		for (int v = 1; v <= V; v++) {
			if (max < dist[v]) {
				max_v = v;
				max = dist[v];
			}
		}
		
		
		dist = bfs(max_v);
		
		int answer = 0;
		for (int v = 1; v <= V; v++) {
			answer = Math.max(answer, dist[v]);
		}
	
		System.out.println(answer);
	}
	
	private static int[] bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		int[] dist = new int[V+1];
		boolean[] visited = new boolean[V+1];
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for (Node next : graph[v]) {
				int next_v = next.v;
				int next_w = next.w;
				
				if (!visited[next_v]) {
					visited[next_v] = true;
					dist[next_v] = dist[v] + next_w;
					q.offer(next_v);
				}
			}
		}
		
		return dist;
	}
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
