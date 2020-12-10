package A2020_12_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS_인접행렬 {
	static boolean[][] graph;
	static int N,M;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start_v = Integer.parseInt(st.nextToken());
		graph = new boolean[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//양방향
			graph[a][b] = true;
			graph[b][a] = true;
		}
		
		
		//dfs
		visited = new boolean[N+1];
		dfs(start_v);
		sb.append("\n");
		
		visited = new boolean[N+1];
		bfs(start_v);
		
		System.out.println(sb);
	}

	private static void dfs(int v) {
		visited[v] = true;
		sb.append(v).append(' ');
		
		//인접한 노드 확인
		for (int next_v = 1; next_v <= N; next_v++) {
			if(!visited[next_v]&&graph[v][next_v]) {//방문x&&연결된 경우
				dfs(next_v);
			}
		}
	}
	
	private static void bfs(int start_v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start_v);
		visited[start_v] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(' ');
		
			for (int next_v = 1; next_v <= N; next_v++) {
				if(!visited[next_v]&&graph[v][next_v]) {
					q.offer(next_v);
					visited[next_v] = true;
				}
			}
		}
	}
}
