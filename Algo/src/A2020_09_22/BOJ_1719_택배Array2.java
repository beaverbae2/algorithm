package A2020_09_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//다익스트라 알고리즘
public class BOJ_1719_택배Array2 {
	static int[][] graph;
	static int[][] dist;
	static int[][] next_vertex;
	static boolean[][] visited;
	static int N,M;
	static int INF = 987654321;
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];
		dist = new int[N+1][N+1];
		next_vertex = new int [N+1][N+1];
		visited = new boolean[N+1][N+1];

		//graph 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i!=j) {
					graph[i][j] = INF;
					dist[i][j] = INF;
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
			
			//next_vertex[a][b] = b;
			//next_vertex[b][a] = a;
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
		visited[start_v][start_v] = true;
		int count = 1;
		
		int v = start_v;
		while(true) {
			if(count==graph.length) break;
			//인접한 정점들 확인
			int min = INF;
			int min_vertex = 0;
			
			for (int next_v = 1; next_v < graph.length; next_v++) {
				if(visited[start_v][next_v]) continue;
				
				int next_dist = dist[start_v][v]+graph[v][next_v];
				
				if(dist[start_v][next_v]>next_dist) {
					next_vertex[next_v][start_v] = v;
					dist[start_v][next_v] = next_dist;
				}
				
				if(min>dist[start_v][next_v]) {
					min_vertex = next_v;
					min = dist[start_v][next_v];
				}
			}
			//next_vertex[min_vertex][start_v] = v;
			visited[start_v][min_vertex] = true;
			v = min_vertex;
			count++;
		}
	}
}
