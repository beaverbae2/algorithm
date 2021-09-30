package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 25MIN
 * @author beaverbae
 * 트리에서 두 정점간 경로는 유일함
 * 노드의 개수가 1일때 유의
 */

public class BOJ_1967_트리의_지름 {
	static List<Node>[] graph;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Node(e, w));
			graph[e].add(new Node(s, w));
		}
		
		int[] result = bfs(1);
		result = bfs(result[0]);
		System.out.println(result[1]);
	}
	
	private static int[] bfs(int s) {
		int max_v = 1, max_w = 0;
		
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(new Node(s, 0));
		visited[s] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.w > max_w) {
				max_v = cur.v;
				max_w = cur.w;
			}
			
			for (Node next : graph[cur.v]) {
				if (visited[next.v]) continue;
				
				q.offer(new Node(next.v, cur.w + next.w));
				visited[next.v] = true;
			}
		}
		
		return new int[] {max_v, max_w};
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
