package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * FAIL
 * MST
 * @author beave
 *
 */

public class BOJ_2211_네트워크_복구_fail {
	static List<Node> graph;
	static int V, E;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(a, b, w));
			graph.add(new Node(b, a, w));
		}
		
		// 가중치에 대한 오름차순 정렬
		Collections.sort(graph, (o1, o2) -> o1.w - o2.w);
		
		parent = new int[V+1];
		for (int v = 1; v <= V; v++) {
			parent[v] = v;
		}
		
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < graph.size(); i++) {
			Node node = graph.get(i);
			int a = node.a;
			int b = node.b;
			
			if (findParent(a, b)) continue;
			
			sb.append(a).append(" ").append(b).append("\n");
			unionParent(a, b);
			cnt++;
			if (cnt == V-1) break;
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		return false;
	}
	
	static class Node {
		int a, b, w;

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
