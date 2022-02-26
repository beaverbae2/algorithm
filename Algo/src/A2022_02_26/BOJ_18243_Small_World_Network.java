package A2022_02_26;

import java.util.*;
import java.io.*;

/**
 * 28MIN
 * BFS
 * @author beaverbae
 * - 모든 정점에 대해서 BFS를 돌아야함
 */

public class BOJ_18243_Small_World_Network {
	static int N, K;
	static List<Integer>[] graph;
	static boolean[] visited;
	static final String SMALL_WORLD = "Small World!";
	static final String BIG_WORLD = "Big World!";
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String ans = SMALL_WORLD;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		for (int v = 1; v <= N; v++) {
			graph[v] = new ArrayList<>();
		}
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int v = 1; v <= N; v++) {
			if (!bfs(v)) {
				ans = BIG_WORLD;
				break;
			}
		}
	
		System.out.println(ans);
	}
	
	private static boolean bfs(int s) {
		int d = 0, cnt = 1;
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N+1];
		
		q.offer(new int[] {s, 0});
		visited[s] = true;
		
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int v = arr[0];
			d = arr[1];
			
			if (d > 6) break;
		
			for (int nv : graph[v]) {
				if (visited[nv]) continue;
				
				q.offer(new int[] {nv, d+1});
				visited[nv] = true;
				cnt++;
			}
		}
		
		if (cnt == N && d < 7) return true;
		return false;
	}
}
