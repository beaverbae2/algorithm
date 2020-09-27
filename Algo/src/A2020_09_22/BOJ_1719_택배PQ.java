package A2020_09_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라 알고리즘
public class BOJ_1719_택배PQ {
	static int[][] graph;
	static int[][] dist;
	static int[][] next_vertex;
	static int N,M;
	
	static class Elem implements Comparable<Elem>{
		int v, cost;

		public Elem(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Elem [next_v=" + v + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Elem e) {
			int c1 = this.cost;
			int c2 = e.cost;
			
			return Integer.compare(c1, c2);
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];
		dist = new int[N+1][N+1];
		next_vertex = new int [N+1][N+1];
	

		//graph 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i!=j) {
					graph[i][j] = 987654321;
					dist[i][j] = 987654321;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			//무방향
			graph[a][b] = cost;
			graph[b][a] = cost;
			
			next_vertex[a][b] = b;
			next_vertex[b][a] = a;
		}
		for (int i = 1; i <= N; i++) {
			dijkstra(i);
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i==j) answer.append('-').append(' ');
				else answer.append(next_vertex[i][j]).append(' ');
			}
			answer.append('\n');
		}
		System.out.println(answer);
	
	}

	private static void dijkstra(int start_v) {
		dist[start_v][start_v] = 0;
		PriorityQueue<Elem> pq = new PriorityQueue<>();
		pq.add(new Elem(start_v, 0));
	
		while(!pq.isEmpty()) {
			Elem e = pq.poll();
			int v = e.v;
			int cost = e.cost;
			
			if(dist[start_v][v]<cost) continue;
			
			for (int next_v = 1; next_v <= N; next_v++) {
				int next_cost = cost+graph[v][next_v];
				
				if(next_cost<dist[start_v][next_v]) {
					dist[start_v][next_v] = next_cost;
					next_vertex[next_v][start_v] = v;
					pq.add(new Elem(next_v, next_cost));
				}
			}
		}
	}
}
