package A2021_09_13;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * 재방문이 문제였다. 재귀 사용시 함수 호출 전과 호출 후 작업 변화에 집중
 */

public class BOJ_19542_전단지_돌리기 {
	static int N, S, D;
	static List<Integer>[] graph;
	static int[] level, dist;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
	
		graph = new List[N+1];
		level = new int[N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
			level[i] = dist[i] = -1;
		}
		
		// 트리의 간선 수 = 노드 수 - 1
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		calcLevel(S);
		System.out.println(calcDist(S) * 2);
	}

	
	private static int calcDist(int v) {
		if (dist[v] != -1) return dist[v];
		dist[v] = 0;
		
		int sum = 0;
		for (int next_v : graph[v]) {
			if (dist[next_v] == -1 && level[next_v] >= D) sum += 1 + calcDist(next_v);
		}
		dist[v] = sum;
		return dist[v];
	}
	
	private static int calcLevel(int v) {
		if (level[v] != -1) return -1;
		level[v] = 0;
		
		for (int next_v : graph[v]) {
			int temp = 1 + calcLevel(next_v);
			level[v] = Math.max(temp, level[v]);
		}
		
		return level[v];
	}
	
}
