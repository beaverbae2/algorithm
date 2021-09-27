package A2021_06_11;

import java.util.*;
import java.io.*;

/**
 * MST
 * 어려웠던 부분
 * - 직접 파는 경우와 연결 하는 경우를 연관 짓는 방법 : 직접 파는 경우는 0번(없는 노드)과 연결 
 * 
 * @author beaverbae
 *
 */

public class BOJ_1368_물대기 {
	static List<Node> graph;
	static int[] parent;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		parent = new int[N+1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			graph.add(new Node(0, i, Integer.parseInt(br.readLine())));
		}
		
		for (int a = 1; a <= N; a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int b = 1; b <= N; b++) {
				int w = Integer.parseInt(st.nextToken());
				
				graph.add(new Node(a, b, w));
			}
		}
		
		Collections.sort(graph, (o1, o2) -> o1.w - o2.w);
		
		int cnt = 0;
		int ans = 0;
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			int w = node.w;
			
			if (findParent(a, b)) continue; // 사이클 확인
			unionParent(a, b);
			
			ans += w;
			
			if (cnt == N) break;
		}
		
		System.out.println(ans);
	}

	private static int getParent(int v) {
		if (parent[v] == v) return v;
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
