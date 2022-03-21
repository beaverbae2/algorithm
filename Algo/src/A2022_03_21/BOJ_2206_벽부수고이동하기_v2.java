package A2022_03_21;

import java.util.*;
import java.io.*;

/**
 * Wrong code
 * BFS
 * @author beaverbae
 * - 방문여부도 벽을 부쉈냐 안부쉈냐로 나눠야함
 *  
반례 -> 2차원 int 배열로 방문처리 시
4 5
01000
00011
11110
11100
 */

public class BOJ_2206_벽부수고이동하기_v2 {
	static boolean[][] board;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			char[] arr = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				if (arr[c] == '0') board[r][c] = true;
			}
		}
		
		System.out.println(bfs(0, 0));
	}
	
	private static int bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		int[][] dist = new int[N][M];
		final int INF = Integer.MAX_VALUE-1;
		for (int r = 0; r < N; r++) Arrays.fill(dist[r], INF);
		q.offer(new Node(sr, sc, 1, false));
		dist[sr][sc] = 1;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			boolean crashed = cur.crashed;
			
			if (r == N-1 && c == M-1) return w;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || dist[nr][nc] < w+1) continue;
				
				if (!crashed) {
					if (board[nr][nc]) q.offer(new Node(nr, nc, w+1, crashed));
					else q.offer(new Node(nr, nc, w+1, !crashed));
					dist[nr][nc] = w+1;
				} else if (board[nr][nc]) {
					q.offer(new Node(nr, nc, w+1, crashed));
					dist[nr][nc] = w+1;
				}
			}
		}
		
		return -1;
	}
	
	static class Node {
		int r, c, w;
		boolean crashed;
		
		public Node(int r, int c, int w, boolean crashed) {
			this.r = r;
			this.c = c;
			this.w = w;
			this.crashed = crashed;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + ", crashed=" + crashed + "]";
		}
	}
}
