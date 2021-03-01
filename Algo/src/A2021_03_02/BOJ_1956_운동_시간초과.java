package A2021_03_02;

import java.util.*;
import java.io.*;

/**
 * FAIL
 * DFS
 * 30MIN
 * @author beaverbae
 *
 */

public class BOJ_1956_운동_시간초과 {
	static List<Node>[] graph;
	static int V, E;
	static int[] dist;
	static int answer;
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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
		}
		
		answer = INF;
		
		for (int start_v = 1; start_v < graph.length; start_v++) {
			dist = new int[V+1];
			Arrays.fill(dist, INF);
			
			dfs(start_v, start_v, 0);
		}
		
		if (answer == INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	private static void dfs(int start_v, int v, int w) {
		
		for (Node next : graph[v]) {
			int next_v = next.v;
			int next_w = next.w;
			
			if (next_v == start_v) {
				answer = Math.min(answer, w+next_w);
			} else if (dist[next_v] > w + next_w) {
				dist[next_v] = w + next_w;
				
				dfs(start_v, next_v, dist[next_v]);
			}
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
