package A2021_06_10;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 12MIN
 * 그래프의 depth가 5이상인 경우 찾기
 * @author beaverbae
 *
 */

public class BOJ_13023_ABCDE {
	static int N, M;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			if (answer == 1) break;
			
			visited[i] = true;
			dfs(i, 1);
			visited[i] = false;
		}
		
		System.out.println(answer);
	}


	private static void dfs(int v, int cnt) {
		if (answer == 1) return;
		if (cnt == 5) {
			answer = 1;
			return;
		}
		
		for (int next : graph[v]) {
			if (visited[next]) continue;
			
			visited[next] = true;
			dfs(next, cnt+1);
			visited[next] = false;
		}
	}
}
