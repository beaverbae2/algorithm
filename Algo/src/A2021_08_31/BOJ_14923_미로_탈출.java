package A2021_08_31;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 26MIN
 * @author beaverbae
 * 벽을 한 번 부수는 조건의 방문처리, 탐색을 면밀히 확인해보자
 */

public class BOJ_14923_미로_탈출 {
	static int R, C, sr, sc, er, ec;
	static int[][] board;
	static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		
		board = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][2];
		
		if (board[sr][sc] == 0) {
			q.offer(new Node(sr, sc, 0, false));
			visited[sr][sc][0] = true;
		} else {
			q.offer(new Node(sr, sc, 0, true));
			visited[sr][sc][1] = true;
		}
		
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			boolean isCrashed = cur.isCrashed;
			
			if (r == er && c == ec) {
				return w;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) continue;
				
				if (board[nr][nc] == 0) {
					if (!isCrashed) {
						if (visited[nr][nc][0]) continue;
						
						q.offer(new Node(nr, nc, w+1, isCrashed));
						visited[nr][nc][0] = true;
					} else {
						if (visited[nr][nc][1]) continue;
						
						q.offer(new Node(nr, nc, w+1, isCrashed));
						visited[nr][nc][1] = true;
					}
				} else {
					if (isCrashed || visited[nr][nc][1]) continue;
					
					q.offer(new Node(nr, nc, w+1, !isCrashed));
					visited[nr][nc][1] = true;
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	static class Node {
		int r, c, w;
		boolean isCrashed;
		
		public Node(int r, int c, int w, boolean isCrashed) {
			this.r = r;
			this.c = c;
			this.w = w;
			this.isCrashed = isCrashed;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + ", isCrashed=" + isCrashed + "]";
		}
	}
}
