package A2020_09_23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길Fail {
	static int[][] map;
	static int M,N,answer,V;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static List<Integer>[] graph;
	static int[] path_count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		V = M*N;
		graph = new List[V];
		path_count = new int[V];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int v1 = 0; v1 < graph.length; v1++) {
			int r = v1/N;
			int c = v1%N;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if(!isInMap(nr, nc)) continue;
				
				if(map[r][c]>map[nr][nc]) {
					int v2 = nr*N+nc;
					graph[v1].add(v2);
				}
			}
		}
		
		//dfs(0);
		//System.out.println(answer);
	}
//	private static void bfs() {
//		Queue<Integer> q = new LinkedList<>();
//		q.offer(0);
//		
//		while(!q.isEmpty()) {
//			int v = q.poll();
//			
//			if(v == V-1) answer++;
//			
//			for(int next_v : graph[v]) {
//				q.offer(next_v);
//			}
//		}
//	}
	
	private static void dfs(int v) {
		if(v==V-1) {
			answer++;
			return;
		}
		
		for(int next_v : graph[v]) {
			System.out.println("v: "+v+" next_v : "+next_v);
			dfs(next_v);
		}
	}


	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<M && nc>=0 && nc<N;
	}
}
