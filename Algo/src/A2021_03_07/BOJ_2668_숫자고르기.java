package A2021_03_07;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 46MIN
 * @author beaverbae
 *
 */

public class BOJ_2668_숫자고르기 {
	static boolean[] visited;
	static int[] graph;
	static TreeSet<Integer> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		graph = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		
		set = new TreeSet<>();
		
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			visited[i] = true;
			HashSet<Integer> temp_set = new HashSet<>();
			temp_set.add(i);
			dfs(i, i, temp_set);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(set.size()).append("\n");
		
		for (int v : set) {
			sb.append(v).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int start_v, int v, HashSet<Integer> temp_set) {
		int next_v = graph[v];
		
		if (!visited[next_v]) {
			visited[next_v] = true;
			temp_set.add(next_v);
			dfs(start_v, next_v, temp_set);
		} else if (start_v == next_v) {
			set.addAll(temp_set);
		}
	}
}
