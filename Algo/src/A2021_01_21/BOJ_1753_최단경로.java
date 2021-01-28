package A2021_01_21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Dijkstra 알고리즘
 * @author beaverbae
 *
 */

public class BOJ_1753_최단경로 {
	static List<Node>[] graph;// 인접 리스트
	static final int INF = 987654321;
	static int V, E;// V : 정점의 개수, E : 간선의 개수
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
	
		int start = Integer.parseInt(br.readLine());
		
		graph = new List[V+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph[a].add(new Node(b, w));
		}
		
		dijkstra(start);
		System.out.println(sb);
	}
	
	private static void dijkstra(int start) {
		// 초기화 및 출발 노드 설정
		PriorityQueue<Node> pq = new PriorityQueue<>();// 우선순위큐 사용
		int[] dist = new int[V+1];
		boolean[] visited = new boolean[V+1];
		
		pq.add(new Node(start, 0));
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		int cnt = 0;// 최단 거리를 구한(방문처리를 한) 정점의 개수
		while(!pq.isEmpty()) {
			int v = pq.poll().v; // 현재 정점
			
			if (visited[v]) continue;// 이미 방문한 경우 pass
			visited[v] = true;// 방문 처리
			cnt++;// 방문한 정점 개수 1 추가
			if (cnt == V) break;// 모든 정점을 다 방문 했다면 끝 
			
			for (Node next : graph[v]) {// 현재 정점과 연결된 모든 정점에 대해
				int next_v = next.v;
				int next_w = next.w;
				
				// 다익스트라 공식
				// [이전에 구한 다음 정점으로 가는 최단 경로] 보다 [현재 정점까지 최단경로 + 현재 정점에서 다음 정점으로 가는 가중치] 가 크다면 최단 거리 갱신
				if (dist[next_v] > dist[v] + next_w) { 
					dist[next_v] = dist[v] + next_w;
					pq.add(new Node(next_v, dist[next_v]));
				}
			}
		}
		
		sb = new StringBuilder();
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] != INF) sb.append(dist[i]).append("\n");
			else sb.append("INF").append("\n");
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
		public int compareTo(Node o) {// 가중치가 작은 순으로 정렬
			return Integer.compare(this.w, o.w);
		}
	}
}
