package A2021_02_06;

import java.util.*;

/**
 * Floyd-Warshall + Dijkstra 
 * @author beaverbae
 *
 */

public class PGS_합승_택시_요금 {
	private int n, s, a, b;
	private int answer;
	private final int INF = 987654321;
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
		answer = INF;
		this.n = n;
		this.s = s;
		this.a = a;
		this.b = b;
		
		List<Node>[] list_graph = new List[n+1];
		for (int i = 1; i < list_graph.length; i++) {
			list_graph[i] = new ArrayList<>();
		}
		
		int[][] matrix_graph = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == j) continue;
				matrix_graph[i][j] = INF;
			}
		}
		
		for (int i = 0; i < fares.length; i++) {
			int v1 = fares[i][0];
			int v2 = fares[i][1];
			int w = fares[i][2];
		
			matrix_graph[v1][v2] = w;
			matrix_graph[v2][v1] = w;
			
			
			list_graph[v1].add(new Node(v2, w));
			list_graph[v2].add(new Node(v1, w));
		}
		
		floyd_warshall(matrix_graph);
	
		dijkstra(list_graph, matrix_graph);
		
		System.out.println(answer);
		return answer;
	}
	
	public void dijkstra(List<Node>[] list_graph, int[][] matrix_graph) {
		// 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[n+1];
				
		//시작점
		pq.add(new Node(s, 0));
		dist[s] = 0;
		answer = Math.min(answer, dist[s]+matrix_graph[s][a]+matrix_graph[s][b]);
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;
			
			if (visited[v]) continue;
			visited[v] = true;
			
			for (Node next : list_graph[v]) {
				int next_v = next.v;
				int next_w = next.w;
				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;
					pq.add(new Node(next_v, dist[next_v]));
					answer = Math.min(answer, dist[next_v]+matrix_graph[next_v][a]+matrix_graph[next_v][b]);
				}
			}
		}
	}

	public void floyd_warshall(int[][] g) {
		for (int k = 1; k < g.length; k++) {
			for (int i = 1; i < g.length; i++) {
				for (int j = 1; j < g.length; j++) {
					g[i][j] = Math.min(g[i][j], g[i][k]+g[k][j]);
				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) {
		new PGS_합승_택시_요금().solution(6, 4, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
		new PGS_합승_택시_요금().solution(7, 3, 4, 1, new int[][] {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
		new PGS_합승_택시_요금().solution(6, 4, 5, 6, new int[][] {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}});
	}
}
