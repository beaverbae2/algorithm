package A2021_06_23;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 편안
 * @author beaverbae
 *
 */

public class BOJ_5567_결혼식_ver3 {
	static int N, M, ans;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		bfs(1);
		
		System.out.println(ans);
	}

	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(new Node(start, 0));
		visited[start] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int v = cur.v;
			int w = cur.w;
		
			if (w == 3) break;
			
			if (w >= 1 && w <= 2) {
				ans++;
			}
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				q.offer(new Node(next_v, w+1));
				visited[next_v] = true;
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
