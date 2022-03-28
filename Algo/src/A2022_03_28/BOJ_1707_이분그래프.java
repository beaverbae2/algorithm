package A2022_03_28;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * @see https://www.acmicpc.net/board/view/77198
 * - 사이클이 있어도 이분그래프 가능
 * - 그래프가 2개 이상 존재 가능 -> 모든 정점을 시작점으로 해서 검사 필요
 *
 */

public class BOJ_1707_이분그래프 {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] color;
	static boolean flag;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			graph = new List[V+1];
			color = new int[V+1];
			visited = new boolean[V+1];
			flag = false;
			for (int v = 1; v <= V; v++) {
				graph[v] = new ArrayList<>();
			}
			
			while (E-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				graph[b].add(a);
			}
			
			for (int v = 1; v <= V; v++) {
				if (visited[v]) continue;
				
				color[v] = 1;
				visited[v] = true;
				dfs(v, 1);
			}
			
			if (flag) sb.append("NO");
			else sb.append("YES");
			sb.append("\n");
		}
		
		System.out.println(sb);	
	}

	private static void dfs(int v, int n) {
		for (int nv : graph[v]) {
			if (visited[nv]) { 
				if (color[v] == color[nv]) flag = true;
			} else {
				visited[nv] = true;
				color[nv] = n * -1;
				dfs(nv, n * -1);
			}
			
			if (flag) {
				flag = true;
				return;
			}
		}
	}
}
