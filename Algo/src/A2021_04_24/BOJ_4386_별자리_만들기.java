package A2021_04_24;

import java.util.*;
import java.io.*;

/**
 * MST
 * 17MIN
 * @author beaverbae
 *
 */

public class BOJ_4386_별자리_만들기 {
	static int N;
	static List<Node> graph;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Pair[] arr = new Pair[N+1];
		graph = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			arr[i] = new Pair(x, y);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				
				double x1 = arr[i].x;
				double y1 = arr[i].y;
				double x2 = arr[j].x;
				double y2 = arr[j].y;
				
				double dx = Math.abs(x1-x2);
				double dy = Math.abs(y1-y2);
				
				double d = Math.sqrt(Math.pow(dx, 2)+ Math.pow(dy, 2));
				
				graph.add(new Node(i, j, d));
			}
		}
		
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		Collections.sort(graph);
		
		double answer = 0;
		int cnt = 0;
		
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			double w = node.w;
			
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
	
	
	static class Pair {
		double x, y;

		public Pair(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
	}
	
	static class Node implements Comparable<Node>{
		int a, b;
		double w;
		
		public Node(int a, int b, double w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.w, o.w);
		}
		
	}
}
