package A2021_06_03;

import java.util.*;
import java.io.*;

/**
 * Topological sort
 * - 비용 갱신하는 부분이 어렵네...
 * @author beaverbae
 *
 */

public class BOJ_2637_장난감_조립_위상정렬 {
	static int N, M;
	static List<Node>[] graph1, graph2;// 방향이 서로 반대인 두 그래프
	static int[] enter_degree;
	
	static boolean[] isBasic;
	static int[] costs;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int root = -1;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph1 = new List[N+1];
		graph2 = new List[N+1];
		enter_degree = new int[N+1];
		isBasic = new boolean[N+1];
		costs = new int[N+1];
		sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int end = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			graph1[start].add(new Node(end, cost));
			graph2[end].add(new Node(start, cost));
			enter_degree[start]++;
		}
		
		// 기본 부품 찾기
		for (int v = 1; v <= N; v++) {
			if (graph2[v].size() != 0) {
				if (graph1[v].size() == 0) {
					root = v;
				}
				continue;
			}
			
			isBasic[v] = true;
		}
		
		topological_sort(root);
	
		for (int i = 1; i <= N; i++) {
			if (!isBasic[i]) continue;
			
			sb.append(i).append(" ").append(costs[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void topological_sort(int root) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(root, 1));
		
		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (Node next : graph2[cur.v]) {
				enter_degree[next.v]--;

				costs[next.v] += cur.w * next.w;
				
				if (enter_degree[next.v] == 0) {
					q.offer(new Node(next.v, costs[next.v]));
				}
			}
		}
	}

	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
