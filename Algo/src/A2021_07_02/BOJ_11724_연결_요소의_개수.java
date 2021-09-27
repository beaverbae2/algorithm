package A2021_07_02;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 7MIN
 * @author beaverbae
 *
 */

public class BOJ_11724_연결_요소의_개수 {
	static int N, M;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N+1];
		visited = new boolean[N+1];
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
		
		for (int v = 1; v <= N; v++) {
			if (visited[v]) continue;
			
			bfs(v);
			ans++;
		}
		
		System.out.println(ans);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				q.offer(next_v);
				visited[next_v] = true;
			}
		}
	}
}
