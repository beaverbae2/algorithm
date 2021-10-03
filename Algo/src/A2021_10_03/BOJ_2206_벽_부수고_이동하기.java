package A2021_10_03;

import java.util.*;
import java.io.*;

/**
 * 18MIN
 * BFS
 * @author beaverbae
 * 벽을 꺠자 얺고 이동한 경우와 벽을 한 번 깨고 이동한 경우로 나눠서 생각
 * 방문 배열 : "이전에 방문"했는지 확인 -> 이미 방문 했다면 이전에 방문 한 것이 최단 거리
 */

public class BOJ_2206_벽_부수고_이동하기 {
	static int R, C;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				board[r][c] = str.charAt(c) - '0';
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][2];
		final int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		
		q.offer(new Node(0, 0, 1, false));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			boolean isCrashed = cur.isCrashed;
			
			if (r == R-1 && c == C-1) return w;// 종료 조건
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) continue;
				
				if (!isCrashed) {
					if (board[nr][nc] == 0 && !visited[nr][nc][0]) {
						q.offer(new Node(nr, nc, w+1, false));
						visited[nr][nc][0] = true;
					}
					
					if (board[nr][nc] == 1 && !visited[nr][nc][1]) {
						q.offer(new Node(nr, nc, w+1, true));
						visited[nr][nc][1] = true;
					}
				} else if (board[nr][nc] == 0 && !visited[nr][nc][1]){
					q.offer(new Node(nr, nc, w+1, true));
					visited[nr][nc][1] = true;
				}
			}
		}
		
		return -1;// 불가능한 경우
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
