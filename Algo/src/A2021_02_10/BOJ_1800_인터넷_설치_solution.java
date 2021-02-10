package A2021_02_10;

import java.util.*;
import java.io.*;

/**
 * Dijkstra + Binary Search
 * 
 * @author beaverbae
 * @see https://bellog.tistory.com/132
 *
 */

public class BOJ_1800_인터넷_설치_solution {
	static List<Node>[] graph;
	static int V, E, K;
	static int[] dist; // dist[V] : V까지 이동하는데 필요한 기준 가중치보다 큰 가중치의 최소 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int max_w = 0; // 간선 중에서 가장 큰 가중치

		graph = new List[V + 1];
		dist = new int[V + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));

			max_w = Math.max(max_w, w); // 가장 큰 가중치를 가져옴
		}

		// 이분 탐색
		int min_w = 0;
		int answer = -1;

		while (min_w <= max_w) {
			int mid_w = (min_w + max_w) / 2;

			if (dijkstra(mid_w)) {
				answer = mid_w;
				max_w = mid_w - 1;
			} else {
				min_w = mid_w + 1;
			}
		}

		System.out.println(answer);
	}

	private static boolean dijkstra(int mid_w) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		pq.offer(new Node(1, 0));
		dist[1] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;
			int w = node.w;

			if (dist[v] < w) {
				continue;
			}

			for (Node next : graph[v]) {
				int next_v = next.v;
				int next_w = w;

				// 기준 가중치 보다 크면 1 증가
				if (next.w > mid_w) {
					next_w++;
				}

				if (dist[next_v] > next_w) {
					dist[next_v] = next_w;
					pq.offer(new Node(next_v, next_w));
				}
			}
		}
		// V까지 이동할 떄 mid_w보다 큰 가중치의 개수가 K개 이하 여야 한다
		return dist[V] <= K;
	}

	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return v + ", " + w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
