package A2021_09_03;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 25MIN
 * @author beaverbae
 *
 */

public class BOJ_15900_나무_탈출 {
	static int N;
	static List<Integer>[] graph;
	static long ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
	
		System.out.println(bfs());
	}
	
	private static String bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(new Node(1, 0));
		visited[1] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int v = cur.v;
			long w = cur.w;
			
			for (int next_v : graph[v]) {
				if (!visited[next_v]) {
					q.offer(new Node(next_v, w+1));
					visited[next_v] = true;
					
					// 리프노드인 경우만
					int alreadyVisitedCnt = 0;
					for (int prev_v : graph[next_v]) {
						if (visited[prev_v]) alreadyVisitedCnt++;
					}
					
					if (alreadyVisitedCnt == graph[next_v].size()) ans += w+1;
				}
			}
		}
		
		if (ans % 2 == 1) return "Yes";
		return "No";
	}
	
	static class Node {
		int v;
		long w;
		
		public Node(int v, long w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
