package A2020_09_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용구하기 {
	static int[][] graph;
	static int[] dist;
	static int N,M,start,end;
	static int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1];
		graph = new int[N+1][N+1];
		for (int i = 1; i < graph.length; i++) {
			for (int j = 1; j < graph.length; j++) {
				if(i==j) continue;
				graph[i][j] = INF;
			}
		}
		
		for (int i = 0; i < dist.length; i++) {
			dist[i] = INF;
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//단방향이었다....
			graph[a][b] = Math.min(graph[a][b], cost);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		System.out.println(dist[end]);
	}

	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		dist[start] = 0;
		pq.add(new int[] {start,0});
		
		while(!pq.isEmpty()) {
			int[] elem = pq.poll();
			int v = elem[0];
			int cost = elem[1];
			
			if(v==end) break;
			
			if(dist[v]<cost) continue;
			
			for (int next_v = 1; next_v < graph.length; next_v++) {
				if(dist[next_v]>dist[v]+graph[v][next_v]) {
					dist[next_v] = dist[v]+graph[v][next_v];
					pq.add(new int[] {next_v, dist[next_v]});
				}
			}
		}
	}
}
