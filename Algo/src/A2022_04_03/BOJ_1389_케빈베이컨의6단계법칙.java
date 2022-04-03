package A2022_04_03;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 13MIN
 * @author beaverbae
 *
 */

public class BOJ_1389_케빈베이컨의6단계법칙 {
	static int N, M, ans, minSum = 987654321;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		
		System.out.println(ans);
	}
	
	private static void bfs(int s) {
		int sum = 0;
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(new Node(s, 0));
		visited[s] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int v = node.v;
			int w = node.w;
			
			sum += w;
			
			for (int nv : graph[v]) {
				if (visited[nv]) continue;
				
				q.offer(new Node(nv, w + 1));
				visited[nv] = true;
			}
		}
		
		if (minSum > sum) {
			minSum = sum;
			ans = s;
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
