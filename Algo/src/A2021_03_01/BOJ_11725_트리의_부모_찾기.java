package A2021_03_01;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 18MIN
 * @author beaverbae
 *
 */

public class BOJ_11725_트리의_부모_찾기 {
	static List<Integer>[] graph;
	static int[] parent;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		parent = new int[N+1];
		bfs(1);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < parent.length; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				q.offer(next_v);
				visited[next_v] = true;
				parent[next_v] = v;
			}
		}
	}
}
