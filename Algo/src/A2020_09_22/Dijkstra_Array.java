package A2020_09_22;

import java.util.Arrays;

public class Dijkstra_Array {
	static int INF = 1000000;
	static int[][] graph = {{0,2,5,1,INF,INF},
							{2,0,3,2,INF,INF},
							{5,3,0,3,1,5},
							{1,2,3,0,1,INF},
							{INF,INF,1,1,0,2},
							{INF,INF,5,INF,2,0}};
	static int[] dist = new int[6];
	static boolean[] visited = new boolean[6];
	
	public static void main(String[] args) {
		Arrays.fill(dist, INF);
		dijkstra(0);
		System.out.println(Arrays.toString(dist));
	}
	
	private static int getSmallIndex() {
		int min = INF;
		int min_index = 0;
		
		for (int v = 0; v < graph.length; v++) {
			if(dist[v]<min && !visited[v]) {
				min = dist[v];
				min_index = v;
			}
		}
		return min_index;
	}
	
	private static void dijkstra(int start_v) {
		//초기화
		for (int i = 0; i < dist.length; i++) {
			dist[i] = graph[start_v][i];
		}
		
		visited[start_v] = true;
		
		for (int number = 0; number<graph.length-2; number++) {
			int current = getSmallIndex();
			visited[current] = true;
			
			for (int next_v = 0; next_v < graph.length; next_v++) {
				if (dist[next_v]>dist[current]+graph[current][next_v]) {
					dist[next_v]=dist[current]+graph[current][next_v];
				}
			}
		}
		
		
	}

	
}
