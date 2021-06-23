package A2021_06_24;

import java.util.*;
import java.io.*;

/**
 * BFS(우선순위큐)
 * 백준 질문 검색 반례 참고
 * 
 * 참고한 반례
 * 1 2
 * answer : 0
 * 
 * 어려웠던 부분
 * - 재방문 가능 : 방문 배열을 boolean이 아닌 int형으로 사용해야 한다
 * 
 * @author beaverbae
 *
 */

public class BOJ_13549_숨바꼭질_3 {
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		System.out.println(bfs());
	}
	
	private static int bfs() {
		// 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		final int INF = 200001;
		int[] visited = new int[INF+1];
		Arrays.fill(visited, INF);
		
		pq.offer(new Node(N, 0));
		visited[N] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int v = cur.v;
			int t = cur.t;
			
			if (v == K) return t;
			
			if (v-1 >=0 && visited[v-1] > t+1) {
				pq.offer(new Node(v-1, t+1));
				visited[v-1] = t+1;
			}
			
			if (v+1 < INF && visited[v+1] > t+1) {
				pq.offer(new Node(v+1, t+1));
				visited[v+1] = t+1;
			}
			
			if (2*v < INF && visited[2*v] > t) {
				pq.offer(new Node(2*v, t));
				visited[2*v] = t;
			}
			
		}
		
		return -1; // 실제론 실행X
	}
	
	static class Node implements Comparable<Node> {
		int v, t;

		public Node(int v, int t) {
			this.v = v;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", t=" + t + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.t - o.t;
		}
	}
}
