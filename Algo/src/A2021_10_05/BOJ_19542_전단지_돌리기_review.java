package A2021_10_05;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * 방문 체크 필수
 */

public class BOJ_19542_전단지_돌리기_review {
	static int N, S, D, ans;
	static List<Integer>[] graph;
	static int[] depth;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		graph = new List[N+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		for (int v = 1; v <= N; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		visited[S] = true;
		getDepth(S);
		
		visited = new boolean[N+1];
		visited[S] = true;
		getAns(S);
		System.out.println(ans * 2);
	}
	
	private static int getDepth(int v) {
		int d = 0;
		for (int next : graph[v]) {
			if (visited[next]) continue;
			
			visited[next] = true;
			int temp = getDepth(next) + 1;
			d = Math.max(d, temp);
		}
		
		return depth[v] = d;
	}
	
	private static void getAns(int v) {
		for (int next : graph[v]) {
			if (visited[next] || depth[next] < D) continue;
			ans++;
			visited[next] = true;
			getAns(next);
		}
	}
}
