package A2020_12_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS_인접리스트 {
	static List<Integer>[] graph;
	static int N,M;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start_v = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);//순서대로 정렬
		}
		
		visited = new boolean[N+1];
		dfs(start_v);
		sb.append("\n");
		
		visited = new boolean[N+1];
		bfs(start_v);
		
		System.out.println(sb);
	}
	
	private static void dfs(int v) {
		visited[v] = true;
		sb.append(v).append(' ');
		
		for (int next_v : graph[v]) {
			if(!visited[next_v]) dfs(next_v);
		}
	}
	
	private static void bfs(int start_v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start_v);
		visited[start_v] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(' ');
			
			for(int next_v : graph[v]) {
				if(!visited[next_v]) {
					q.offer(next_v);
					visited[next_v] = true;
				}
			}
		}
	}
}
