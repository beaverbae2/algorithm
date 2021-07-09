package A2021_07_09;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 40MIN
 * 어려웠던 부분
 * - 다익스트라
 *     - 양의 간선만 있는 경우, 하나의 시작 정점에서 다른 정점 까지의 최단 거리 구할떄 사용
 *     - 하지만 모든 집인 정점을 시작점으로해서 다익스트라를 적용하면 시간초과 발생
 *     - 최적화 고민 
 *         - 맥도날드와 스타벅스인 정점을 모두 시작 정점으로 취급
 *         - 여러 정점이 시작점이므로 다익스트라의 사용조건에 위배된다고 생각했음 -> 고민 시작
 *         - 맥도날드와 스타벅스가 모두 간선 크기가 0인 암의의 한 정점(ex) 0번)과 연결 되어 있다고 접근 했더니 고민 해결
 *         - 개념이 확장 되었다
 *          
 * - 주의 : 스타벅스와 맥도날드는 집이 될수 없음
 * 
 * @author beaverbae
 *
 */

public class BOJ_13911_집_구하기 {
	static List<Node>[] graph;
	static int N, E, X, Y;
	static int[] S, M;
	static int[] distS, distM;
	static boolean[] isNotHouse;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		distS = new int[N+1];
		distM = new int[N+1];
		isNotHouse = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			distS[i] = INF;
			distM[i] = INF;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		
		st = new StringTokenizer(br.readLine());
		M = new int[Integer.parseInt(st.nextToken())];
		X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M.length; i++) {
			M[i] = Integer.parseInt(st.nextToken());
			isNotHouse[M[i]] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		S = new int[Integer.parseInt(st.nextToken())];
		Y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S.length; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			isNotHouse[S[i]] = true;
		}
		
		dijkstra(M, distM, X);
		dijkstra(S, distS, Y);
		
		int ans = INF;
		for (int v = 1; v <= N; v++) {
			if (isNotHouse[v] || distM[v] > X || distS[v] > Y) continue;
			
			ans = Math.min(ans, distM[v] + distS[v]);
		}
		
		if (ans == INF) ans = -1;
		System.out.println(ans);
	}
	
	private static void dijkstra(int[] arr, int[] dist, int max) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		
		for (int start : arr) {
			pq.add(new Node(start, 0));
			dist[start] = 0;
		}
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int v = cur.v;
			
			if (dist[v] > max) break;
			if (visited[v]) continue;
			visited[v] = true;
			
			for (Node next : graph[v]) {
				if (dist[next.v] > dist[v] + next.w) {
					dist[next.v] = dist[v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
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
			return this.w - o.w;
		}
	}
}
