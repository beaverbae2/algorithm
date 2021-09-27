package A2021_06_18;

import java.util.*;
import java.io.*;

public class BOJ_15681_트리와_쿼리_ver1 {
	static int N, R, Q;
	static List<Integer>[] graph;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dfs(R, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int cur, int parent) {
		dp[cur] = 1;
		
		for (int child : graph[cur]) {
			if (child == parent) continue;
			
			dfs(child, cur);
			dp[cur] += dp[child];
		}
	}
}
