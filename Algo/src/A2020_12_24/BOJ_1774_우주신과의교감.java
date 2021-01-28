package A2020_12_24;

import java.util.*;
import java.io.*;

public class BOJ_1774_우주신과의교감 {
	static int N, M;
	static int[] parent;
	static int[][] pos;
	static boolean[][] checked;
	static List<Edge> list;
	
	static class Edge implements Comparable<Edge> {
		int a, b;
		double w;
		
		public Edge(int a, int b, double w) {
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
			return Double.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pos = new int[N+1][2];
		checked = new boolean[N+1][N+1];
		list = new ArrayList<>();
		parent = new int[N+1];
		int cnt = 0;
		double answer = 0;
		
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		
		for (int i = 1; i < pos.length; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			checked[a][b] = true;
			checked[b][a] = true;
			unionParent(a, b);
		}
		
		for (int i = 1; i < pos.length; i++) {
			for (int j = i+1; j < pos.length; j++) {
				if (checked[i][j]) { 
					cnt++;
					continue;
				}
				
				double a1 = (double) (pos[i][0]);
				double b1 = (double) (pos[i][1]);
				
				double a2 = (double) (pos[j][0]);
				double b2 = (double) (pos[j][1]);
				
				list.add(new Edge(i, j, getWeight(a1, b1, a2, b2)));
			}
		}
		Collections.sort(list);
	
		for (Edge edge : list) {
			int a = edge.a;
			int b = edge.b;
			double w = edge.w;
			
			if (findParent(a, b)) continue;
			unionParent(a, b);
			answer += w;
			cnt++;
			
			if (cnt == N-1) break;
		}
		System.out.println(String.format("%.2f", answer));
	}
	
	static double getWeight(double a1, double b1, double a2, double b2) {
		double a = Math.pow(Math.abs(a1-a2), 2);
		double b = Math.pow(Math.abs(b1-b2), 2);
		
		return Math.pow(a+b, 0.5);
	}
	
	static int getParent(int v) {
		if (parent[v] == v) return v;
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
		
		if (a == b) return true;
		else return false;
	}
}
