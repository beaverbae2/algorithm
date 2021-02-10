package A2021_02_10;

import java.util.*;
import java.io.*;

public class BOJ_1800_인터넷_설치_Fail1 {
	static List<Node>[] graph;
	static ArrayList<Integer> list;
	static int answer = Integer.MAX_VALUE;
	static int V, E, K;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new List[V+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		
		visited = new boolean[V+1];
		list = new ArrayList<>();
		
		visited[1] = true;
		dfs(1, 0);
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}
	}
	
	private static void dfs(int v, int idx) {
		if (v == V) {
			if (list.size() <= K) {
				answer = 0;
			}else {
				Collections.sort(list);
				answer = Math.min(answer, list.get(K));
			}
			return;
		}
		
		for (Node next : graph[v]) {
			int next_v = next.v;
			int next_w = next.w;
			
			if (visited[next_v]) continue;
			
			visited[next_v] = true;
			list.add(next_w);
			dfs(next_v, idx+1);
			visited[next_v] = false;
			list.remove(idx);
		}
	}

	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return v + ", " + w;
		}
	}
}
