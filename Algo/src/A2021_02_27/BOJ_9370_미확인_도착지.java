package A2021_02_27;

import java.util.*;
import java.io.*;

/**
 * Dijkstra
 * 66MIN
 * @author beaverbae
 *
 */

public class BOJ_9370_미확인_도착지 {
	static List<Node>[] graph;
	static int[] dest;
	static int N, M, T;
	static int S, G, H;
	static StringBuilder sb;
	static int d;
	static int INF = 50000001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());

			graph = new List[N + 1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				graph[a].add(new Node(b, w));
				graph[b].add(new Node(a, w));
			}

			dest = new int[T];
			for (int i = 0; i < dest.length; i++) {
				dest[i] = Integer.parseInt(br.readLine());
			}

			Arrays.sort(dest);

			int[] dist_S, dist_H, dist_G;
			dist_S = new int[N + 1];// S -> dest[i]
			dijkstra(S, 0, dist_S);

			dist_H = new int[N + 1];// H -> dest[i];
			dijkstra(H, 0, dist_H);

			dist_G = new int[N + 1];// G -> dest[i];
			dijkstra(G, 0, dist_G);

			d = dijkstra(G, H, new int[N + 1]);// 무조건 경로 있음
			int dSG = dist_S[G];
			int dSH = dist_S[H];

			for (int i = 0; i < dest.length; i++) {
				boolean flag1 = true;
				boolean flag2 = true;

				int dHD = dist_H[dest[i]];
				int dGD = dist_G[dest[i]];

				if (dSG == INF || dHD == INF)
					flag1 = false;// S -> G -> H -> D 불가
				if (dSH == INF || dGD == INF)
					flag2 = false;// S -> H -> G -> D 불가

				if (!flag1 && !flag2)
					continue;
				else {
					int d1 = dSG + d + dHD;
					int d2 = dSH + d + dGD;
					int min_dist = Math.min(d1, d2);

					if (min_dist <= dist_S[dest[i]]) {
						sb.append(dest[i]).append(" ");
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dijkstra(int start, int end, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;
			int w = node.w;

			if (end > 0 && visited[end])
				break;
			if (visited[v])
				continue;

			visited[v] = true;

			for (Node next : graph[v]) {
				int next_v = next.v;
				int next_w = next.w;

				if (dist[next_v] > dist[v] + next_w) {
					dist[next_v] = dist[v] + next_w;
					pq.offer(new Node(next_v, dist[next_v]));
				}
			}
		}

		return dist[end];
	}

	static class Node implements Comparable<Node> {
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
			return Integer.compare(this.w, o.w);
		}
	}
}
