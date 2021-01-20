package A2021_01_20;

import java.util.*;

/**
 * MST - Kruskal
 * @author beaverbae
 *
 */


public class PGS_섬_연결하기 {
	private int[] parent;
	private List<Node> graph;

	public int solution(int n, int[][] costs) {
		// parent 초기화
		parent = new int[n];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		// graph 초기화 및 간선 추가
		graph = new ArrayList<>();
		for (int i = 0; i < costs.length; i++) {
			int start = costs[i][0];
			int end = costs[i][1];
			int cost = costs[i][2];

			graph.add(new Node(start, end, cost));
		}

		// 비용의 오름차순으로 graph 정렬
		Collections.sort(graph);

		int cnt = 0;// MST에 추가된 간선의 개수
		int answer = 0;
		for (Node node : graph) {// MST 생성
			int start = node.start;
			int end = node.end;
			int cost = node.cost;

			if (findParent(start, end))// 사이클이 존재하면 pass
				continue;

			unionParent(start, end);
			answer += cost;
			cnt++;

			if (cnt == n - 1)
				break;
		}

		return answer;
	}

	public int getParent(int v) {
		if (parent[v] == v)
			return parent[v];
		return parent[v] = getParent(parent[v]);
	}

	public void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	public boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a == b)
			return true;
		return false;
	}

	static class Node implements Comparable<Node> {
		int start, end, cost;

		public Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) {
		System.out.println(new PGS_섬_연결하기().solution(4,
				new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } }));
	}
}
