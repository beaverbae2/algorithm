package A2021_05_06;

import java.util.*;
import java.io.*;

/**
 * Bellmanford
 * 어려웠던 부분
 * - int로 하면 overflow 발생 가능
 * 
 * @author beaverbae
 * @see https://steady-coding.tistory.com/92
 */

public class BOJ_11657_타임머신_solution {
	static List<Node> graph;
	static long[] dist;
	static final int INF = 500 * 100000;
	static int N, M;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			graph.add(new Node(a, b, w));
		}
		
		if (!bellmanford(1)) {
			for (int i = 2; i < dist.length; i++) {
				if (dist[i] == INF) {
					sb.append(-1).append("\n");
				} else {
					sb.append(dist[i]).append("\n");
				}
			}
		} else {
			sb.append(-1);
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean bellmanford(int start) {
		dist[start] = 0;
		
		int cnt = 0;
		while (cnt < N-1) {
			for (Node cur : graph) {
				if (dist[cur.start] == INF) continue;
				
				if (dist[cur.end] > dist[cur.start] + cur.w) {
					dist[cur.end] = dist[cur.start] + cur.w;
				}
			}
			cnt++;
		}
		
		boolean update = false;
		for (Node cur : graph) {
			if (dist[cur.start] == INF) continue;
			
			if (dist[cur.end] > dist[cur.start] + cur.w) {
				dist[cur.end] = dist[cur.start] + cur.w;
				update = true;
				break;
			}
		}
		
		return update;
	}

	static class Node {
		int start, end, w;

		public Node(int start, int end, int w) {
			this.start = start;
			this.end = end;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", w=" + w + "]";
		}
	}
}
