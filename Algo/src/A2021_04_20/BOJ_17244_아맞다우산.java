package A2021_04_20;

import java.util.*;
import java.io.*;

/**
 * BFS + DFS
 * 48MIN
 * 오래 걸린 이유
 * - 입력 받는거...
 * - dfs 탐색 : 누적 시간 구하는 법 구현
 * - bfs 탐색 : 나가는 문 위치 때문에 isIn 필요했음
 * @author beaverbae
 * 
 */

public class BOJ_17244_아맞다우산 {
	static int R, C, N;
	static char[][] map;
	static Pair[] products;
	static int[][] dist;
	static boolean[] visited;
	static int[][] dirs = {{-1, 0},{1, 0},{0, 1},{0, -1}};
	
	static final char WALL = '#';
	static final char BLANK = '.';
	static final int INF = 987654321;
	
	static int answer = INF;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				
				if (map[r][c] == 'X') {
					N++;
				}
			}
		}
		
		dist = new int[N+2][N+2];
		products = new Pair[N+2];
		visited = new boolean[N+2];
		
		int idx = 1;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'X') {
					products[idx++] = new Pair(r, c); 
				} else if (map[r][c] == 'S') {
					products[0] = new Pair(r, c); 
				} else if (map[r][c] == 'E') {
					products[N+1] = new Pair(r, c); 
				} 
			}
		}
		
//		System.out.println(Arrays.toString(products));
		
		// dist 초기화
		for (int i = 0; i <= N+1; i++) {
			for (int j = 0; j <= N+1; j++) {
				if (i == j) continue;
				dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i <= N+1; i++) {
			for (int j = 0; j <= N+1; j++) {
				if (dist[i][j] != INF) continue;
				
				dist[i][j] = bfs(products[i].r, products[i].c, products[j].r, products[j].c);
			}
		}
		
//		for (int i = 0; i <= N+1; i++) {
//			System.out.println(Arrays.toString(dist[i]));
//		}
		
		dfs(0, 0, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int cnt, int prev_idx, int time) {
		if (cnt == N) {
			int total_time = time + dist[prev_idx][N+1];
			answer = Math.min(answer, total_time);
			return;
		}
		
		for (int idx = 1; idx <= N; idx++) {
			if (visited[idx]) continue;
			visited[idx] = true;
			dfs(cnt+1, idx, time+dist[prev_idx][idx]);
			visited[idx] = false;
		}
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int depth = node.depth;
		
			if (r == er && c == ec) {
				return depth;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isIn(nr, nc) && !isWall(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, depth+1));
				}
			}
		}
		
		return -1;
	
	}

	private static boolean isWall (int nr, int nc) {
		return map[nr][nc] == WALL;
	}
	
	private static boolean isIn (int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
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
	
	static class Node {
		int r, c, depth;

		public Node(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}
	}
}
