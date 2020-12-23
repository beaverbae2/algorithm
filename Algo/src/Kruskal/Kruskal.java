package Kruskal;

import java.util.*;

public class Kruskal {
	static int[] parent;
	static List<Edge> list;
	static class Edge {
		int a, b, w;

		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
	}
	
	
	public static void main(String[] args) {
		int V = 7;
		int E = 11;
		
		//parent 배열 초기화
		parent = new int[V+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		list = new ArrayList<>();
		
		list.add(new Edge(1, 7, 12));
		list.add(new Edge(1, 4, 28));
		list.add(new Edge(1, 2, 67));
		list.add(new Edge(1, 5, 17));
		list.add(new Edge(2, 4, 24));
		list.add(new Edge(2, 5, 62));
		list.add(new Edge(3, 5, 20));
		list.add(new Edge(3, 6, 37));
		list.add(new Edge(4, 7, 13));
		list.add(new Edge(5, 6, 45));
		list.add(new Edge(5, 7, 73));
		
		//간선 비용 오름차순으로 정렬
		Collections.sort(list, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		
		System.out.println(list);
		
		//MST만들기
		int answer = 0;
		for (Edge edge : list) {
			int a = edge.a;
			int b = edge.b;
			int w = edge.w;
			
			if (findParent(a, b)) continue;//cycle형성
			unionParent(a, b);
			answer += w;
		}
		System.out.println(answer);
	}
	
	
	//부모 노드 찾기
	private static int getParent(int v) {
		if (v == parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	//각 부모 노드 합치기
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	//부모노드가 같은지 확인
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		else return false;
	}
}
