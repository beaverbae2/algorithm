package A2021_10_02;

import java.util.*;
import java.io.*;

/**
 * 10MIN
 * Topological sort
 * @author beaverbae
 *
 */

public class BOJ_2252_줄_세우기 {
	static List<Integer>[] graph;
	static int[] enterDegree;
	static int N, M;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		enterDegree = new int[N+1];
		sb = new StringBuilder();
		graph = new List[N+1];
		for (int v = 1; v <= N; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			enterDegree[b]++;
		}
		
		topological_sort();
		System.out.println(sb);
	}
	
	private static void topological_sort() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int v = 1; v <= N; v++) {
			if (enterDegree[v] != 0) continue;
			q.offer(v);
			sb.append(v).append(" ");
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph[cur]) {
				enterDegree[next]--;
				if (enterDegree[next] != 0) continue;
				q.offer(next);
				sb.append(next).append(" ");
			}
		}
	}
}
