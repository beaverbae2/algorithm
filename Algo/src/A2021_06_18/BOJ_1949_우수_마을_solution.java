package A2021_06_18;

import java.io.*;
import java.util.*;

/**
 * TreeDP
 * 어려웠던 이유
 * - 트리 디피 인줄 몰랐다...
 * - 구현을 아예 못했다
 * - 트리에서 노드를 포함 시키거나 포함시키지않는 경우가 있을떄 트리디피 사용
 * 
 * @author beaverbae
 * @see https://gre-eny.tistory.com/18
 *
 */

public class BOJ_1949_우수_마을_solution {
	static int N;
	static int[] arr;
	static List<Integer>[] graph;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		graph = new List[N+1];
		dp = new int[N+1][2];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dfs(1, 0);
		
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	private static void dfs(int cur, int parent) {
		for (int child : graph[cur]) {
			if (child == parent) continue;// 다시 부모로 돌아가지 않게
			
			dfs(child, cur);// 인접 노드 탐색
			dp[cur][0] += Math.max(dp[child][0], dp[child][1]);// 인접 노드가 우수마을일 수도 아닐수도 있음
			dp[cur][1] += dp[child][0]; // 인접 노드가 모두 우수마을X
		}
		
		dp[cur][1] += arr[cur];// 현재 마을의 인구 더하기
	}
}
