package A2021_09_20;

import java.util.*;
import java.io.*;
/**
 * BFS
 * 예전 솔루션 참고
 * 
 * 트리의 특성
 * - 정점 v1과 v2 사이의 경로는 유일하다 -> 1보다 큰 간선이 있어도 다익스트라가 아닌 bfs로 구현 가능
 * 
 */
  
public class BOJ_1167_트리의_지름_solution {
	static int V;
	static List<Node>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V+1];
		for (int v = 1; v <= V; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			while (true) {
				int b = Integer.parseInt(st.nextToken());
				if (b == -1) break;
				int w = Integer.parseInt(st.nextToken());
				
				graph[a].add(new Node(b, w));
			}
		}
		
		System.out.println(bfs(bfs(1).v).w);
	}
	
	private static Node bfs(int v) {
		Node result = new Node(v, 0);
		Queue<Node> q = new LinkedList<>();

		dist = new int[V+1];
		final int INF = 1000000001;
		
		Arrays.fill(dist, INF);
		dist[v] = 0;
		q.offer(new Node(v, 0));
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (Node next : graph[cur.v]) {
				if (dist[next.v] != INF) continue;
				
				dist[next.v] = next.w + dist[cur.v];
				q.offer(new Node(next.v, dist[next.v]));
				if (result.w < dist[next.v]) {
					result.w = dist[next.v]; 
					result.v = next.v;
				}
			}
		}
		
		return result;
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
