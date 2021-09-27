package A2021_06_09;

import java.util.*;
import java.io.*;

/**
 * 
 * 와 어렵다ㄷㄷ
 * 
 * 어려웠던 부분
 * - 세권 구하는 부분 : 방문 배열 사용하는 기존 방식으로 했더니 시간 초과 발생 -> 맥날, 스벅의 위치를 모두 pq에 추가하고, 방문배열 없는 것으로 해결
 * 
 * 
 * @author beaverbae
 * @see https://velog.io/@pss407/%EB%B0%B1%EC%A4%8013911-%EC%A7%91-%EA%B5%AC%ED%95%98%EA%B8%B0
 *
 */

public class BOJ_13911_집_구하기_solution {
	static int V, E, M, S;
	static int X, Y;
	static List<Node>[] graph;
	static final int INF = 987654321;
	static PriorityQueue<Node> pq;
	static int[] distM, distS;
	static int answer = INF;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	
		graph = new List[V+1];
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[u].add(new Node(v, w));
			graph[v].add(new Node(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>();
		distM = new int[V+1];
		Arrays.fill(distM, INF);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int v = Integer.parseInt(st.nextToken());
			pq.offer(new Node(v, 0));
			distM[v] = 0;
		}
		dijkstra(distM);
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>();
		distS = new int[V+1];
		Arrays.fill(distS, INF);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; i++) {
			int v = Integer.parseInt(st.nextToken());
			pq.offer(new Node(v, 0));
			distS[v] = 0;
		}
		dijkstra(distS);
		
		for (int i = 1; i <= V; i++) {
			if (distM[i] <= 0 || distM[i] > X || distS[i] <= 0 ||distS[i] > Y) continue;
			
			answer = Math.min(answer, distM[i]+distS[i]);
		}
		
		if (answer == INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	private static void dijkstra(int[] dist) {
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (Node next : graph[cur.v]) {
				if (dist[next.v] > dist[cur.v] + next.w) {
					dist[next.v] = dist[cur.v] + next.w;
					pq.offer(new Node(next.v, dist[next.v]));
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
