package A2021_01_14;

import java.util.*;
import java.io.*;

/**
 * BFS(or Dijkstra) + permutation
 * @author beaverbae
 * @see https://velog.io/@pss407/%EB%B0%B1%EC%A4%804991-%EB%A1%9C%EB%B4%87-%EC%B2%AD%EC%86%8C%EA%B8%B0
 * 
 */

public class BOJ_4991_로봇청소기_Solution {
	static int R, C;
	static char[][] map;
	static int[][] graph;
	static List<Pair> list;//먼지와 로봇의 좌표
	static int V;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int[][] vertexMap;
	static int[] arr;
	static int answer;
	static boolean[] visited;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			if (R == 0 && C == 0) break;
		
			map = new char[R][C];
			vertexMap = new int[R][C];
			
			list = new ArrayList<>();
			V = 1;
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
				
					if (map[i][j] == 'o') {
						list.add(0, new Pair(i, j));
						vertexMap[i][j] = 1;
					}else if (map[i][j] == '*') {
						list.add(new Pair(i, j));
						V++;
						vertexMap[i][j] = V;
					}
				}
			}
			graph = new int[V+1][V+1];
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					graph[i][j] = INF;
				}
			}
			
			for (Pair p : list) {
				dijkstra(p.r,p.c);
			}
			
			arr = new int[V+1];
			visited = new boolean[V+1];
			arr[1] = 1;
			visited[1] = true;
			permutation(2);
			if(answer == Integer.MAX_VALUE) sb.append(-1).append("\n");
			else sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void permutation(int r) {
		if (r == V+1) {
			int sum = 0;
			boolean flag = true;
			for (int v = 1; v < arr.length-1; v++) {
				if(graph[arr[v]][arr[v+1]] == INF) {
					flag = false;
					break;
				}else {
					sum += graph[arr[v]][arr[v+1]];
				}
			}
			if (flag) answer = Math.min(answer, sum);
			return;
		}
		
		for (int v = 1; v <= V; v++) {
			if (visited[v]) continue;
			
			visited[v] = true;
			arr[r] = v;
			permutation(r+1);
			visited[v] = false;
		}
	}

	private static void dijkstra(int sr, int sc) {
		PriorityQueue<Elem> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[R][C];
		int[][] dist = new int[R][C];
		final int INF = 987654321;
		
		pq.add(new Elem(sr, sc, 0));
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[sr][sc] = 0;
		int start = vertexMap[sr][sc];
		
		while(!pq.isEmpty()) {
			Elem e = pq.poll();
			int r = e.r;
			int c = e.c;
			int d = e.d;
			
			if (visited[r][c]) continue;
			visited[r][c] = true;
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];
			
				if (!isInMap(nr, nc)||map[nr][nc] == 'x') continue;
			
				if (dist[nr][nc] > dist[r][c] + 1) {
					dist[nr][nc] = dist[r][c] + 1;
					if (map[nr][nc] == '*'||map[nr][nc] == 'o') {
						int end = vertexMap[nr][nc];
						graph[start][end] = dist[nr][nc];
						graph[end][start] = dist[nr][nc];
						pq.add(new Elem(nr, nc, dist[nr][nc]));
					}else {
						pq.add(new Elem(nr, nc, dist[nr][nc]));
					}
				}
			}
		}
		
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
	
	
	static class Elem implements Comparable<Elem>{
		int r, c, d;

		public Elem(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", d=" + d + "]";
		}

		@Override
		public int compareTo(Elem o) {
			return Integer.compare(this.d, o.d);
		}
	}
}
