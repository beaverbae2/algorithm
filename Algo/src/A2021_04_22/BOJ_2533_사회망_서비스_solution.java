package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * TreeDP
 * @author beaverbae
 * @see https://118k.tistory.com/563
 */

public class BOJ_2533_사회망_서비스_solution {
	static List<Integer>[] graph;
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N-1; i++) {// 트리이므로
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		dfs(1); // 시작점 1로
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		dp[cur][0] = 0;// 얼리어답터가 아닌경우
		dp[cur][1] = 1;// 얼리어답터인 경우
	
		for (int next : graph[cur]) {
			if (!visited[next]) {
				dfs(next);
				dp[cur][0] += dp[next][1];// 모든 친구가 얼리어답터
				dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}
}
