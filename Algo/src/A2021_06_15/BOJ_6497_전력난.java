package A2021_06_15;

import java.util.*;
import java.io.*;

/**
 * MST
 * 12MIN
 * @author beaverbae
 *
 */

public class BOJ_6497_전력난 {
	static List<Node> graph;
	static int[] parent;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			if (V == 0 && E == 0) break;
			
			int total = 0;
			graph = new ArrayList<>();
			parent = new int[V];
			for (int i = 0; i < V; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
			
				graph.add(new Node(a, b, w));
				total += w;
			}
			
			Collections.sort(graph, (o1, o2) -> o1.w - o2.w);
		
			int sum = 0, cnt = 0;
			for (Node node : graph) {
				int a = node.a;
				int b = node.b;
				int w = node.w;
				
				if (findParent(a, b)) continue;
				
				unionParent(a, b);
				sum += w;
				cnt += 1;
				
				if (cnt == V-1) break;
			}
			
			int answer = total - sum;
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
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
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		return a == b;
	}
	
	static class Node {
		int a, b ,w;

		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
	}
}
