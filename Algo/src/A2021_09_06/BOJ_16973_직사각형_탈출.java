package A2021_09_06;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 42MIN
 * @author beaverbae
 * 최적화가 필요함
 */

public class BOJ_16973_직사각형_탈출 {
	static int N, M, R, C;
	static int sr, sc, er, ec;
	static int[][] board;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	static boolean[][] isGo;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
	
		makeIsGo();
		System.out.println(bfs());
	}
	
	private static void makeIsGo() {
		isGo = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				isGo[r][c] = checkIsGo(r, c);
			}
		}
	}
	
	private static boolean checkIsGo(int r, int c) {
		for (int nc = c; nc < c + C; nc++) {
			if (!isIn(r, nc) || !isIn(r+R-1, nc)) return false;
		}
		
		for (int nr = r; nr < r + R; nr++) {
			if (!isIn(nr, c) || !isIn(nr, c+C-1)) return false;
		}
		
		
		return true;
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			
			if (r == er && c == ec) return w;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || !isGo[nr][nc] || visited[nr][nc]) continue;
				
				q.offer(new Node(nr, nc, w+1));
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >=0 && nc < M && board[nr][nc] == 0;
	}

	static class Node {
		int r, c, w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + "]";
		}
	}
}
