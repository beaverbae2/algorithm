package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * Tree DP
 * 29MIN
 * 어려웠던 부분
 * - 재방문 방지
 * @author beaverbae
 *
 */

public class BOJ_2533_사회망_서비스_SNS {
	static int N;
	static List<Integer>[] graph;
	static int[][] dp;// dp[i][0] : i번 노드가 얼리X 일떄 필요한 최소 얼리 개수, dp[i][1] : i번 노드가 얼리O 일떄 ... 
	static boolean[] visited;
	static int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dp = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], INF);
		}
		visited = new boolean[N+1];
		
		visited[1] = true;
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dfs(int v) {
		dp[v][0] = 0;
		dp[v][1] = 1;
		
		for (int next_v : graph[v]) {
			if (!visited[next_v]) {// 놓쳤던 부분 -> 재방문 했었음
				visited[next_v] = true;
				dfs(next_v);
				dp[v][0] += dp[next_v][1];
				dp[v][1] += Math.min(dp[next_v][0], dp[next_v][1]);
			}
		}
	}
}
