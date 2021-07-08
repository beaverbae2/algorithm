package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * DFS(Tree)
 * 
 * 루트에서 부터 다른 노드로 가는 경로가 유일할을 활용
 * 
 * @author beaverbae
 * @see https://data-make.tistory.com/473
 */

public class BOJ_19542_전단지_돌리기_solution {
	static int N, S, D;
	static int[] dist;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int ans, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	
		dist = new int[N+1];
		graph = new List[N+1];
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		visited[S] = true;
		dfs(S);
		
		for (int v = 1; v <= N; v++) {
			if (v !=S && dist[v] >= D) {
				ans++;
			}
		}
		
		ans *= 2;
		System.out.println(ans);
	}

	private static int dfs(int v) {
		if (v != S && graph[v].size() == 1) return dist[v]; // leaf node
		
		for (int next_v : graph[v]) {
			if (visited[next_v]) continue;
			
			visited[next_v] = true;
			dist[v] = Math.max(dist[v], dfs(next_v) + 1);
		}
		
		return dist[v];
	}
}
