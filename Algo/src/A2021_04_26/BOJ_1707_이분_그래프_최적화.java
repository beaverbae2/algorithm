package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 최적화
 * - 메모리 최적화 -> Node객체, visited배열
 * @author beaverbae
 * 
 *
 */

public class BOJ_1707_이분_그래프_최적화 {
	static List<Integer>[] graph;
	static int[] visited;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new List[V+1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			
				graph[a].add(b);
				graph[b].add(a);
			}
			
			boolean result = true;
			visited = new int[V+1];
			for (int i = 1; i < visited.length; i++) {
				if (visited[i] != 0) continue;
				result = bfs(i);
				if (!result) break;
			}
			
			
			if (result) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		
		q.offer(new Node(start, true));
		visited[start] = 1;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 현재 정점과 인접 정점은 서로 속한 집합이 다르다 
			int f = 0;// 어느 집합에 속하는지
			
			if (cur.flag) {
				f = 2;
			} else {
				f = 1;
			}
			
			for (int next_v : graph[cur.v]) {
				if (visited[cur.v] == visited[next_v]) { 
					return false;
				}
				if (visited[cur.v] + visited[next_v] == 3) continue;
				if (visited[next_v] == 0) {
					visited[next_v] = f;
					q.offer(new Node(next_v, !cur.flag));
				}
			}
		}
		
		
		return true;
	}
	
	static class Node {
		int v;
		boolean flag;
		
		public Node(int v, boolean flag) {
			this.v = v;
			this.flag = flag;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", flag=" + flag + "]";
		}
	}
}
