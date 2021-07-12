package A2021_07_12;

import java.util.*;
import java.io.*;

/**
 * Topological sort
 * 14MIN
 * 위상정렬은 정의를 잘 알아야함
 * @author beaverbae
 *
 */

public class BOJ_14567_선수과목 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] enterDegree;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N+1];
		enterDegree = new int[N+1];
		dist = new int[N+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			enterDegree[e]++;
		}
		
		topological_sort();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dist[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static void topological_sort() {
		Queue<Node> q = new LinkedList<>();
		for (int i = 1; i < enterDegree.length; i++) {
			if (enterDegree[i] != 0) continue;
			q.offer(new Node(i, 1));
			dist[i] = 1;
		}
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int next : graph[cur.v]) {
				enterDegree[next]--;
				if (enterDegree[next] == 0) {
					dist[next] = cur.w + 1;
					q.offer(new Node(next, cur.w + 1));
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
