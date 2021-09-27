package A2021_06_23;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 29MIN
 * 어려웠던 부분
 * - DFS로 구현시 재방문이 가능해야함 -> 사이클이 있을떄(1번테케)
 * - 걍 BFS로 구현하는게 좋음
 * @author beaverbae
 *
 */

public class BOJ_5567_결혼식 {
	static int N, M, ans;
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		if (!graph[1].isEmpty()) {
			visited[1] = true;
			dfs(1, 0);
		}
		
		System.out.println(ans);
	}

	private static void dfs(int v, int cnt) {
		for (int next_v : graph[v]) {
			if (cnt <= 1) {
				if (!visited[next_v]) ans++;
				visited[next_v] = true;
				dfs(next_v, cnt+1);
			}
		}
	}
}
