package A2020_09_22;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra_pq {
	static int INF = 1000000;
	static int[][] graph = {{0,2,5,1,INF,INF},
							{2,0,3,2,INF,INF},
							{5,3,0,3,1,5},
							{1,2,3,0,1,INF},
							{INF,INF,1,1,0,2},
							{INF,INF,5,INF,2,0}};
	static int[] dist = new int[6];
	
	static class Elem implements Comparable<Elem>{
		int v, cost;

		public Elem(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Elem e) {
			int c1 = this.cost;
			int c2 = e.cost;
			return Integer.compare(c1, c2);
		}
	}
	
	public static void main(String[] args) {
		Arrays.fill(dist, INF);
		dijkstra(0);
		System.out.println(Arrays.toString(dist));
	}
	
	private static void dijkstra(int start_v) {
		PriorityQueue<Elem> pq = new PriorityQueue<>();
		dist[start_v] = 0;
		pq.add(new Elem(start_v, 0));
		
		while(!pq.isEmpty()) {
			Elem e = pq.poll();
			int v = e.v;
			int cost = e.cost;
			
			if(dist[v]<cost) continue;
			
			
			for (int next_v = 0; next_v < graph.length; next_v++) {
				int next_cost = cost+graph[v][next_v];
				if(next_cost<dist[next_v]) {
					dist[next_v] = next_cost;
					pq.add(new Elem(next_v, next_cost));
				}
			} 
		}
	}
}
