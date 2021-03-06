package A2021_03_07;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * @see https://zoonvivor.tistory.com/97
 *
 */

public class BOJ_2668_숫자고르기_최적화 {
	static boolean[] visited;
	static int[] graph;
	static ArrayList<Integer> list;
	static int last;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		graph = new int[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		
		list = new ArrayList<>();
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			last = i;
			dfs(i);
			visited[i] = false;
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		
		for (int v : list) {
			sb.append(v).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int v) {
		int next_v = graph[v];
		
		if (!visited[next_v]) {
			visited[next_v] = true;
			dfs(next_v);
			visited[next_v] = false;
		} else if (last == next_v) {
			list.add(next_v);
		}
	}
}
