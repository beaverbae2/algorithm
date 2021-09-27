package A2021_06_10;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 1H30MIN
 * 어려웠던 이유
 * - 문제 해석 오류(또?) : 사이클일 때 성립 안되는줄 알았음
 * 반례)
 * 4 4
 * 1 2
 * 2 3
 * 3 4 
 * 4 1
 * 
 * - 그래프가 여러개 존재 가능
 * 테케)
 * 7 7
 * 1 2
 * 1 3
 * 2 4
 * 3 4
 * 5 6
 * 6 7
 * 7 5
 * 
 * @author beaverbae
 *
 */

public class BOJ_12893_적의적 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N+1];
		visited = new int[N+1];
		Arrays.fill(visited, -1);
		
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
		
		int answer = 1;
		for (int i = 1; i <= N; i++) {
			if (visited[i] == -1) {
				answer = bfs(i);
			}
			
			if (answer == 0) break;
		}
		
		System.out.println(answer);
	}

	private static int bfs(int start) {
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(new Pair(start, 0));
		visited[start] = 0;
		
		int result = 1;
		while (!q.isEmpty() && result != 0) {
			Pair cur = q.poll();
			
			int next_d = (cur.d+1) % 2;
			for (int next_v : graph[cur.v]) {
				if (visited[next_v] == -1) {
					q.offer(new Pair(next_v, next_d));
					visited[next_v] = next_d;
				} else {
					if (next_d != visited[next_v]) {
						result = 0;
						break;
					}
				}
			}
			
		}
		
		return result;
	}
	
	static class Pair {
		int v, d;

		public Pair(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Pair [v=" + v + ", d=" + d + "]";
		}
	}
}
