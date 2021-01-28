package A2020_12_28;

import java.util.*;
import java.io.*;

public class BOJ_1647_도시분할계획 {
	static List<Edge> list;
	static int[] parent;
	static int V,E;
	
	static class Edge implements Comparable<Edge>{
		int a,b,w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int answer = 0;
		int max = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		parent = new int[V+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.add(new Edge(a, b, w));
		}
		Collections.sort(list);
		int cnt = 0;
		for (Edge edge : list) {
			int a = edge.a;
			int b = edge.b;
			int w = edge.w;
			
			if (findParent(a, b)) continue;
			
			unionParent(a, b);
			answer += w;
			max = Math.max(max, w);
			cnt++;
			
			if(cnt == V-1) break;
		}
		answer -= max;
		System.out.println(answer);
	}
	
	static int getParent(int v) {
		if (v == parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		return a == b;
	}
}
