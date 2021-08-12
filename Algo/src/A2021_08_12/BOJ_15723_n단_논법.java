package A2021_08_12;

import java.util.*;
import java.io.*;

/**
 * Graph
 * 21MIN
 * Union Find는 양방향!!
 * @author beaverbae
 *
 */

public class BOJ_15723_n단_논법 {
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		graph = new List[26];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String[] arr = br.readLine().split(" is ");
			int a = getVertex(arr[0].toCharArray()[0]);
			int b = getVertex(arr[1].toCharArray()[0]);
			
			graph[a].add(b);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] arr = br.readLine().split(" is ");
			int a = getVertex(arr[0].toCharArray()[0]);
			int b = getVertex(arr[1].toCharArray()[0]);
			
			if (bfs(a, b)) {
				sb.append("T");
			} else {
				sb.append("F");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	
	private static boolean bfs(int start, int dest) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[26];
		
		q.offer(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if (cur == dest) return true;
			
			for (int next : graph[cur]) { 
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
		
		return false;
	}

	private static int getVertex(char c) {
		return c - 'a';
	}
}
