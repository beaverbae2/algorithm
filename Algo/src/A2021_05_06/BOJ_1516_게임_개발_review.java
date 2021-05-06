package A2021_05_06;

import java.util.*;
import java.io.*;

public class BOJ_1516_게임_개발_review {
	static int N;
	static int[] W, enterDegree, dist;
	static List<Integer>[] graph;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		W = new int[N+1];
		enterDegree = new int[N+1];
		dist = new int[N+1];
		graph = new List[N+1];
		sb = new StringBuilder();
		
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int end = 1; end <= N; end++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			W[end] = w;
			
			while (true) {
				int start = Integer.parseInt(st.nextToken());
				if (start == -1) break;
				
				graph[start].add(end);
				enterDegree[end]++;
			}
		}
		
		topological_sort();
		for (int i = 1; i < dist.length; i++) {
			sb.append(dist[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void topological_sort() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int v = 1; v <= N; v++) {
			if (enterDegree[v] != 0) continue;
			
			q.offer(v);
			dist[v] = W[v];
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph[cur]) {
				enterDegree[next]--;
				dist[next] = Math.max(dist[next], dist[cur] + W[next]);
				
				if (enterDegree[next] == 0) {
//					dist[next] = dist[cur] + W[next];
					q.offer(next);
				}
			}
		}
	}
}
