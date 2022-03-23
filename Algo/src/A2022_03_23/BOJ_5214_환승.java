package A2022_03_23;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 * @see https://yabmoons.tistory.com/260
 * - 하이퍼튜브 -> dummy node로 표현
 */

public class BOJ_5214_환승 {
	static int N, K, M;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N+M+1];
		for (int i = 1; i < graph.length; i++) graph[i] = new ArrayList<>();
		
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			
			for (int k = 0; k < K; k++) {
				int v = Integer.parseInt(st.nextToken());
				graph[v].add(N+m);
				graph[N+m].add(v);
			}
		}
		
		System.out.println(bfs(1));
	}
	
	private static int bfs(int s) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		final int INF = 987654321;
		int[] dist = new int[N+M+1];
		Arrays.fill(dist, INF);
		
		pq.add(new int[] {s, 1});
		dist[s] = 1;
		
		while (!pq.isEmpty()) {
			int[] arr = pq.poll();
			int v = arr[0];
			int d = arr[1];
			
			for (int nv : graph[v]) {
				if (isHyper(v) && dist[nv] > d) {
					pq.add(new int[] {nv, d});
					dist[nv] = d;
				} else if (!isHyper(v) && dist[nv] > d + 1){
					pq.add(new int[] {nv, d+1});
					dist[nv] = d+1;
				}
			}
		}
		
		return dist[N] == INF ? -1 : dist[N] ;
	}
	
	private static boolean isHyper(int v) {
		return v >= N && v < N+M+1;
	}
}