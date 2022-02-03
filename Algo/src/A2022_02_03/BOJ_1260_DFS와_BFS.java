package A2022_02_03;

import java.util.*;
import java.io.*;

public class BOJ_1260_DFS와_BFS {
	static int N, M, S;
	static boolean[] visited;
	static List<Integer>[] graph;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		graph = new List[N+1];
		sb = new StringBuilder();
		
		for (int v = 1; v <= N; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int v = 1; v <= N; v++) {
			Collections.sort(graph[v]);
		}
		
		dfs(S);
		sb.append("\n");
		bfs(S);
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int v) {
		if (visited[v]) return;
		visited[v] = true;
		sb.append(v).append(" ");
		
		for (int next : graph[v]) {
			if (visited[next]) continue;
			dfs(next);
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		sb.append(start).append(" ");
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for (int next : graph[v]) {
				if (visited[next]) continue;
				
				q.offer(next);
				visited[next] = true;
				sb.append(next).append(" ");
			}
		}
	}
}
