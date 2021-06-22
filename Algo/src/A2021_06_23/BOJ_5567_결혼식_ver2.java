package A2021_06_23;

import java.util.*;
import java.io.*;

/**
 * DFS
 * boolean배열 대신 정수형 배열 사용하여 방문 체크
 * @author beaverbae
 *
 */

public class BOJ_5567_결혼식_ver2 {
	static int N, M, ans;
	static List<Integer>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		dist = new int[N+1];
		Arrays.fill(dist, 3);
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		if (!graph[1].isEmpty()) {
			dist[1] = 0;
			dfs(1, 0);
		}
		
		System.out.println(ans);
	}

	private static void dfs(int v, int cnt) {
		int next_cnt = cnt+1;
		for (int next_v : graph[v]) {
			if (next_cnt <= 2) {
				if (dist[next_v] == 3) ans++;
				
				if (dist[next_v] > next_cnt) {
					dist[next_v] = next_cnt;
				}
				dfs(next_v, cnt+1);
			}
		}
	}
}
