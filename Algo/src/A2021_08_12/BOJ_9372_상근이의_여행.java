package A2021_08_12;

import java.util.*;
import java.io.*;

/**
 * Graph
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_9372_상근이의_여행 {
	static int N, M;
	static List<Integer>[] graph;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 0;
			
			graph = new List[N+1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				graph[b].add(a);
			}
			
			bfs(1);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph[cur]) {
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
					ans++;
				}
			}
		}
	}
}
