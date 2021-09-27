package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 60MIN
 * 오래 걸린 이유
 * - 문제 해석을 잘못함
 * - bfs로 탐색시, 정점이 어느 집합에 속하는지를 구현할때 오래 걸림
 * - 그래프의 개수가 여러개 일때를 고려하지 못함
 * @author beaverbae
 * 
 *
 */

public class BOJ_1707_이분_그래프 {
	static List<Integer>[] graph;
	static boolean[] visited;
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
			visited = new boolean[V+1];
			for (int i = 1; i < visited.length; i++) {
				if (visited[i]) continue;
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
		int[] flag = new int[V+1];
		
		q.offer(new Node(start, 0));
		flag[start] = 1;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 현재 정점과 인접 정점은 서로 속한 집합이 다르다 
			int f = 0;// 어느 집합에 속하는지
			
			if (cur.w % 2 == 0) {
				f = 2;
			} else {
				f = 1;
			}
			
			for (int next_v : graph[cur.v]) {
				if (flag[cur.v] == flag[next_v]) { 
					return false;
				}
				if (flag[cur.v] + flag[next_v] == 3) continue;
				if (flag[next_v] == 0) {
					flag[next_v] = f;
					visited[next_v] = true;
					q.offer(new Node(next_v, cur.w+1));
				}
			}
		}
		
		
		return true;
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
