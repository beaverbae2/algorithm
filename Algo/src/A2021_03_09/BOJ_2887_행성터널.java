package A2021_03_09;

import java.util.*;
import java.io.*;

/**
 * MST, Sorting
 * @author beaverbae
 * @see https://steady-coding.tistory.com/117
 *
 */

public class BOJ_2887_행성터널 {
	static List<Point> list;
	static List<Node> graph;
	static int[] parent;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			list.add(new Point(i, a, b, c));
		}
		
		Collections.sort(list, (p1, p2) -> p1.x - p2.x);
		
		for (int i = 1; i < list.size(); i++) {
			Point p1 = list.get(i-1);
			Point p2 = list.get(i);
			
			int a = p1.idx;
			int b = p2.idx;
			int w = Math.abs(p1.x-p2.x);
			
			graph.add(new Node(a, b, w));
		}
		
		Collections.sort(list, (p1, p2) -> p1.y - p2.y);
		
		for (int i = 1; i < list.size(); i++) {
			Point p1 = list.get(i-1);
			Point p2 = list.get(i);
			
			int a = p1.idx;
			int b = p2.idx;
			int w = Math.abs(p1.y-p2.y);
			
			graph.add(new Node(a, b, w));
		}
		
		Collections.sort(list, (p1, p2) -> p1.z - p2.z);
		
		for (int i = 1; i < list.size(); i++) {
			Point p1 = list.get(i-1);
			Point p2 = list.get(i);
			
			int a = p1.idx;
			int b = p2.idx;
			int w = Math.abs(p1.z-p2.z);
			
			graph.add(new Node(a, b, w));
		}
		
		parent = new int[list.size()];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		
		Collections.sort(graph, (n1, n2) -> n1.w - n2.w);
		
		int cnt = 0;
		int answer = 0;
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			int w = node.w;
			
			if (findParent(a, b)) continue;
			
			unionParent(a, b);
			answer += w;
			cnt++;
			
			if (cnt == list.size()) break;
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
	
	static class Point {
		int idx, x, y, z;

		public Point(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Point [idx=" + idx + ", x=" + x + ", y=" + y + ", z=" + z + "]";
		}
	}
	
}
