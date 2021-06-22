package A2021_06_23;

import java.util.*;
import java.io.*;

/**
 * DFS, BFS
 * 어려웠떤 부분
 * - 문제에서 주어진 그래프의 형태를 잘 이용해야함
 * - 순환선 체크(DFS)
 *     - 처음에 1번 노드를 출발점으로 사이클 확인 : 직전 노드 재방문시 오류 발생
 *     - 각 노드를 출발점으로 지정하여 사이클 확인하는 방식으로 해결 : 이떄 확인한 정점이 3개이상 이어야함
 * BFS로 한 번 써서 모든 노드의 거리 구함 -> 이전 풀이 보다 빠름     
 * @author beaverbae
 *
 */

public class BOJ_16947_서울_지하철_2호선_review {
	static int N, start_v;
	static List<Integer>[] graph;
	static int[] dist;
	static boolean[] visited, isCycle;
	static boolean findCycle;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		dist = new int[N+1];
		isCycle = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			start_v = i;
			visited = new boolean[N+1];
			visited[i] = true;
			dfs(i, 0);

			if (findCycle) break;
		}
		
		bfs(start_v);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dist[i]).append(" ");
		}
		System.out.println(sb.toString());
		
	}
	
	
	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[N+1];
		
		q.offer(new Node(start, 0));
		visited[start] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			int v = cur.v;
			int w = cur.w;
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				if (isCycle[next_v]) {
					q.offer(new Node(next_v, 0));
				} else {
					q.offer(new Node(next_v, w+1));
					dist[next_v] = w+1;
				}
				visited[next_v] = true;
			}
		}
	}


	private static void dfs(int v, int cnt) {
		
		for (int next_v : graph[v]) {
			if (visited[next_v]) {
				if (next_v == start_v && cnt >= 2) {
					findCycle = true;
				}
			} else {
				visited[next_v] = true;
				dfs(next_v, cnt+1);
			}
			
			if (findCycle) {
				isCycle[v] = true;
				return;
			}
		}
	}
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
