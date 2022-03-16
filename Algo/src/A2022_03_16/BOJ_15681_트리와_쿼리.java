package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree DP
 * 15MIN
 * @author beaverbae
 * - 방문배열 필요
 */

public class BOJ_15681_트리와_쿼리 {
	static int N, R, Q;
	static List<Integer>[] tree;
	static int[] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
	
		tree = new List[N+1];
		dp = new int[N+1];
		visited = new boolean[N+1];
		for (int v = 1; v <= N; v++) {
			tree[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dfs(R);
		
		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int dfs(int v) {
		if (dp[v] != 0) return dp[v];
		
		dp[v] = 1;
		visited[v] = true;
		
		for (int nv : tree[v]) {
			if (visited[nv]) continue;
			
			dp[v] += dfs(nv);
		}
		
		return dp[v];
	}
}
