package A2021_01_15;

import java.util.*;
import java.io.*;

/**
 * 63MIN. 776ms 
 * DFS
 * @author beaverbae
 *
 */

public class BOJ_17089_세_친구 {
	static List<Integer>[] graph;
	static int V, E;
	static List<Triple> list;
	static boolean[] visited;
	static LinkedList<Integer> selected_vertex;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	
		graph = new List[V+1];
		visited = new boolean[V+1];
		list = new ArrayList<>();
		selected_vertex = new LinkedList<>();
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		
		for (int v = 1; v <= V; v++) {
			selected_vertex.addLast(v);
			visited[v] = true;
			find3friends(v, v, 0);
			selected_vertex.removeLast();
			visited[v] = false;
		}
		
		if (list.isEmpty()) System.out.println(-1);
		else {
			int answer = Integer.MAX_VALUE;
			for (Triple t : list) {
				int a_len = graph[t.a].size()-2;
				int b_len = graph[t.b].size()-2;
				int c_len = graph[t.c].size()-2;
				
				int sum = a_len + b_len + c_len;
				
				answer = Math.min(answer, sum);
			}
			System.out.println(answer);
		}
	}
	
	private static void find3friends(int start_v, int v, int cnt) {
		if (cnt == 3) return;
		
		for (int next_v : graph[v]) {
			if (cnt == 2 && next_v == start_v && visited[start_v]) {
				list.add(new Triple(selected_vertex.get(0), selected_vertex.get(1), selected_vertex.get(2)));
				return;
			}
			
			if (visited[next_v]) continue;
			
			selected_vertex.addLast(next_v);
			visited[next_v] = true;
			find3friends(start_v, next_v, cnt+1);
			selected_vertex.removeLast();
			visited[next_v] = false;
		}
	}

	static class Triple {
		int a, b ,c;

		public Triple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Triple [a=" + a + ", b=" + b + ", c=" + c + "]";
		}
	}
}
