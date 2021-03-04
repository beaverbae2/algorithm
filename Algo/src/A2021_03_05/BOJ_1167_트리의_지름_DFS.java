package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * @see https://moonsbeen.tistory.com/101?category=1184369
 */

public class BOJ_1167_트리의_지름_DFS {
	static List<Node>[] graph;
	static int V;
	static boolean[] visited;
	static int max_dist, max_v;
	
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
		
		visited = new boolean[V+1];
		dfs(1, 0);
		visited = new boolean[V+1];
		dfs(max_v, 0);
		System.out.println(max_dist);
	}
	
	private static void dfs(int v, int dist) {
		visited[v] = true;
		
		if (max_dist < dist) {
			max_dist = dist;
			max_v = v;
		}
		
		for (Node next : graph[v]) {
			if (visited[next.v]) continue;
			
			dfs(next.v, dist+next.w);
		}
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
