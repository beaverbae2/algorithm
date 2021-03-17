package A2021_03_17;

import java.util.*;
import java.io.*;

/**
 * 
 * Tree, UnionFind, Cycle check
 * 61MIN
 * @author beaverbae
 *
 */

public class BOJ_4803_트리 {
	static List<Integer>[] graph;
	static int V, E;
	static boolean[] checked;
	static boolean[] notTree;
	static boolean[] visited;
	static int[] parent;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int idx = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			if (V == 0 && E == 0) break;
			
			init();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				graph[b].add(a);
				
				int min_v = Math.min(a, b);
				int max_v = Math.max(a, b);
				
				unionParent(min_v, max_v);
			}
			
			int ans = 0;
			
			for (int v = 1; v <= V; v++) {
				int parent_v = getParent(v);
				flag = false;
				
				if (checked[parent_v]) {
					notTree[v] = notTree[parent_v];
					checked[v] = true;
					continue;
				}
				
				checked[v] = true;
				visited = new boolean[V+1];
				isCycle(0, v);
				notTree[v] = flag;
				if (!flag) ans++;
			}
			
			sb.append("Case ").append(idx);
			if (ans >= 2) sb.append(": A forest of ").append(ans).append(" trees.").append("\n");
			else if (ans == 1) sb.append(": There is one tree.").append("\n");
			else sb.append(": No trees.").append("\n");
			
			idx++;
		}
		
		System.out.println(sb.toString());
	}

	private static void isCycle(int prev_v, int v) {
		visited[v] = true;
		
		for (int next_v : graph[v]) {
			if (next_v != prev_v && visited[next_v]) {
				flag = true;
				return;
			}
			
			if (!visited[next_v]) isCycle(v, next_v);
		}
	}

	private static void init() {
		parent = new int[V+1];
		graph = new List[V+1];
		checked = new boolean[V+1];
		notTree = new boolean[V+1];
		
		for (int v = 1; v <= V; v++) {
			parent[v] = v;
			graph[v] = new ArrayList<>();
		}
	}

	private static int getParent(int v) {
		if (v == parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
}
