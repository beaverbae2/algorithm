package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * Topological_sort
 * 20MIN
 * @author beaverbae
 * 위상정렬 : 방향그래프에서 정점들 간의 선행 순서가 있는 경우 활용
 */

public class BOJ_1005_ACM_Craft {
	static int N, K, W;
	static List<Integer>[] graph;
	static int[] time, dist, enterDegree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (tc-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			graph = new List[N+1];
			time = new int[N+1];
			dist = new int[N+1];
			enterDegree = new int[N+1];
			for (int v = 1; v <= N; v++) {
				graph[v] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			
				graph[a].add(b);
				enterDegree[b]++;
			}
			
			W = Integer.parseInt(br.readLine());
			sb.append(topological_sort()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int topological_sort() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int v = 1; v <= N; v++) {
			if (enterDegree[v] != 0) continue;
			q.offer(v);
			dist[v] = time[v];
			if (v == W) return dist[v];
		}
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			if (v == W) return dist[v];
			
			for (int nv : graph[v]) {
				enterDegree[nv]--;
				dist[nv] = Math.max(dist[nv], dist[v] + time[nv]);
				if (enterDegree[nv] == 0) {
					q.offer(nv);
				}
			}
		}
		
		return -1;
	}
}
