package A2021_01_12;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 *
 */

public class BOJ_13023_ABCDE {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int N, M;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 0; i < graph.length; i++) {
			visited = new boolean[N];
			dfs(i, 1);
			
			if (flag) break;
		}
		
		if(flag) System.out.println(1);
		else System.out.println(0);
	}

	private static void dfs(int i, int cnt) {
		visited[i] = true;
		
		if (cnt == 5) {
			flag = true;
			return;
		}
		
		for (int next : graph[i]) {
			if (!visited[next]) {
				dfs(next, cnt+1);
			}
		}
		
		visited[i] = false;
	}
}
