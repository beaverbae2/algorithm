package A2021_04_02;

import java.io.*;
import java.util.*;

public class BOJ_2211_네트워크_복구_최단거리역추적 {
	static List<Edge>[] graph;
	static int path[];
	static int N, M;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		path = new int[N + 1];

		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, v));
			graph[b].add(new Edge(a, v));
		}

		dijkstra(1);		//다 연결되어 있으므로 한번만 

		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {		//경로가 있는 것만 집어넣는다. 
			if (path[i] != 0) {
				list.add(new int[] { i, path[i] });
			}
		}
		System.out.println(list.size());
		for (int[] is : list) {
			System.out.println(is[0] + " " + is[1]);
		}
	}

	private static void dijkstra(int start) {
		boolean[] visited = new boolean[N + 1];
		int[] D = new int[N + 1];
		Arrays.fill(D, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		D[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (!visited[cur.v]) {
				visited[cur.v] = true;

				for (Edge next : graph[cur.v]) {
					if (!visited[next.v] && D[next.v] > D[cur.v] + next.w) {
						D[next.v] = D[cur.v] + next.w;
						path[next.v] = cur.v;	//경로 추적 
						pq.add(new Edge(next.v, D[next.v]));
					}
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}