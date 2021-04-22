package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * TreeDP
 * 14MIN
 * @author beaverbae
 *
 */

public class BOJ_15681_트리와_쿼리 {
	static int N, R, Q;
	static List<Integer>[] graph;
	static int[] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		dp = new int[N+1];
		visited = new boolean[N+1];
		dfs(R);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(dp[q]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		dp[cur] = 1;
		
		for (int next : graph[cur]) {
			if (visited[next]) continue;
			dfs(next);
			dp[cur] += dp[next];
		}
	}
}
