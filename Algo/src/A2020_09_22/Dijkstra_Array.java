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
	
	private static void dijkstra(int start_v) {
		dist[start_v] = 0;//출발지와 도착지가 같음
		visited[start_v] = true;
		int count = 1;
		
		
		int v = start_v;
		while(true) {
			System.out.println(v);
			System.out.println(Arrays.toString(dist));
			if(count==graph.length) break;
			//인접한 정점들 확인
			int min_dist = INF;
			int post_v = v;
			for (int next_v = 0; next_v < graph.length; next_v++) {
				if(visited[next_v]) continue;//방문한 노드인 경우 패스
				
				int next_dist = dist[v] + graph[v][next_v];
				
				if(next_dist<dist[next_v]) {
					dist[next_v] = next_dist;
				}
					
				//갱신이 발생하지 않는 경우 존재(정점을 추가해도 dist변화 없음)
				if(min_dist>dist[next_v]) {
					post_v = next_v;
					min_dist = dist[next_v];
				}
			}
			v = post_v;
			visited[v] = true;
			count++;
			//break;
		}
		
		
	}
	
	private static boolean isAllVisited() {
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i]) return false;
		}
		return true;
	}
}
