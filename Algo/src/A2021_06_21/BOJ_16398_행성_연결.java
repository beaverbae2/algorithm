package A2021_06_21;

import java.util.*;
import java.io.*;

/**
 * MST
 * 11MIN
 * 
 * @author beaverbae
 *
 */

public class BOJ_16398_행성_연결 {
	static List<Node> graph;
	static int[] parent;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				graph.add(new Node(i, j, Long.parseLong(st.nextToken())));
			}
		}
		
		Collections.sort(graph, (o1, o2) -> Long.compare(o1.w, o2.w));
	
		long answer = 0;
		int cnt = 0;
		
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			long w = node.w;
			
			if (findParent(a, b)) continue;
			
			unionParent(a, b);
			answer += w;
			cnt++;
			
			if (cnt == N-1) break;
		}
		
		System.out.println(answer);
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
		int a, b;
		long w;
		
		public Node(int a, int b, long w) {
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
