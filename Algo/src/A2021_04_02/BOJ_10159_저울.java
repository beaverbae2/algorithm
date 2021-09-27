package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 23MIN
 * @author beaverbae
 *
 */

public class BOJ_10159_저울 {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Integer>[] graph1 = new List[N+1];
		List<Integer>[] graph2 = new List[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph1[a].add(b);
			graph2[b].add(a);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int compare1 = bfs(graph1, i);
			int compare2 = bfs(graph2, i);
			int ans = N - compare1 - compare2 - 1;
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int bfs(List<Integer>[] graph, int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		
		int w = 0;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			for (int next_v :  graph[v]) {
				if (visited[next_v]) continue;
				
				w++;
				q.offer(next_v);
				visited[next_v] = true;
			}
		}
		
		return w;
	}

	static class Node {
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
