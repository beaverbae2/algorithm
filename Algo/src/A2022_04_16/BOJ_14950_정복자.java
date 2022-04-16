package A2022_04_16;

import java.util.*;
import java.io.*;

/**
 * MST
 * @author beaverbae
 * - 그새 MST를 잊었네..
 */

public class BOJ_14950_정복자 {
	static List<Node> graph;
	static int[] parent;
	static int N, M, T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.add(new Node(a, b, w));
			graph.add(new Node(b, a, w));
		}
		
		Collections.sort(graph, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			}
		});
	
		int ans = 0, cnt = 0;;
		
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			int w = node.w;
			
			if (findParent(a, b)) continue;
			unionParent(a, b);
			
			ans += w + T * cnt++;
			if (cnt == N-1) break; // 없어도 되긴함 -> cnt >= N 일떄는 사이클 생성되므로
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
		return getParent(a) == getParent(b);
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
