package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * MST(Minimum Spanning Tree)
 * 12MIN
 * @author beaverbae
 * 
 * 최소 스패닝 트리 
 * - 그래프의 모든 정점을 연결하는 부분 그래프 중 간선의 합이 최소인 것
 * - 간선의 가중치가 음수여도 가능
 * 
 * 크루스칼 알고리즘
 * - MST를 구현하는 알고리즘 -> Greedy
 * - 간선 정보를 가중치의 오름차순으로 정렬하여 그래프 구성한 후, 간선을 하나씩 연결하여 사이클이 만들어지지 않게 함
 * - 사이클 확인 : Union find 활용 -> MST로 추가하려는 간선을 구성하는 두 정점의 조상 정점이 같다면 추가X  
 * 
 * Comparator 생성 방법
 */

public class BOJ_1197_최소_스패닝_트리 {
	static int V, E;
	static List<Node> graph;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = 0;
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		parent = new int[V+1];
		for (int v = 1; v <= V; v++) {
			parent[v] = v;
		}
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph.add(new Node(a, b, w));
		}
		
		Collections.sort(graph, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			}
		});
		
		int cnt = 0;
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			int w = node.w;
			
			if (findParent(a, b)) continue;
			unionParent(a, b);
			
			ans += w;
			cnt++;
			if (cnt == V-1) break;
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
		
		public Node (int a, int b, int w) {
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
