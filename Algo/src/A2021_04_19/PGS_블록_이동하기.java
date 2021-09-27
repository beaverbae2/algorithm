package A2021_04_19;

import java.util.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class PGS_블록_이동하기 {
	int[][] board;
	int N;
	int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	int[][][] rots1 = {{{-1, 0}, {1,0}, {-1, 1}, {1, 1}}, {{0, -1}, {0, 1}, {1, -1}, {1, 1}}};
	int[][][] rots2 = {{{-1, 1}, {1, 1}, {-1, 0}, {1,0}}, {{1, -1}, {1, 1}, {0, -1}, {0, 1}}};
	boolean[][][] visited;
	
	public int solution(int[][] board) {
		this.board = board;
		this.N = board.length;
		
		int answer = bfs();
		return answer;
	}

	private int bfs() {
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[N][N][2];
		
		q.offer(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.status == 0) {
				if (cur.r == N-1 && cur.c == N-2) {
					return cur.depth;
				}
			} else {
				if (cur.r == N-2 && cur.c == N-1) {
					return cur.depth;
				}
			}
			
			// 이동
			for (int d = 0; d < dirs.length; d++) {
				int r = cur.r + dirs[d][0];
				int c = cur.c + dirs[d][1];
				
				if (isMove(r, c, cur.status)) {
					visited[r][c][cur.status] = true;
					q.offer(new Node(r, c, cur.status, cur.depth+1));
				}
			}
			
			// 회전
			rotate(cur, q);
			
		}
		
		return -1;
	}
	

	private void rotate(Node cur, Queue<Node> q) {
		int next_status = (cur.status+1) % 2;
		
		for (int d = 0; d < rots1[cur.status].length; d++) {
			int r = cur.r;
			int c = cur.c;
			int nr = cur.r + rots1[cur.status][d][0];
			int nc = cur.c + rots1[cur.status][d][1];
			int dr = cur.r + rots2[cur.status][d][0];
			int dc = cur.c + rots2[cur.status][d][1];
			
			if (d >= 2) {
				if (cur.status == 0) c++;
				else r++;
			}
			
			if (isIn(nr, nc) && isIn(dr, dc)) {
				if (board[nr][nc] == 0 && board[dr][dc] == 0) {
					if (d % 2 == 0) {
						if (!visited[nr][nc][next_status]) {
							visited[nr][nc][next_status] = true;
							q.offer(new Node(nr, nc, next_status, cur.depth+1));
						}
					} else {
						if (!visited[r][c][next_status]) {
							visited[r][c][next_status] = true;
							q.offer(new Node(r, c, next_status, cur.depth+1));
						}
					}
				}
			}
		}
	}
	
	private boolean isMove(int r, int c, int status) {
		if (isIn(r, c) && board[r][c] == 0 && !visited[r][c][status]) {
			if (status == 0) {// 수평
				if (isIn(r, c+1) && board[r][c+1] == 0) {
					return true;
				}
			} else {// 수직
				if (isIn(r+1, c) && board[r+1][c] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
	
	static class Node {
		int r, c, status, depth;

		public Node(int r, int c, int status, int depth) {
			this.r = r;
			this.c = c;
			this.status = status;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", status=" + status + ", depth=" + depth + "]";
		}
	}

	public static void main(String[] args) {
		System.out.println(new PGS_블록_이동하기().solution(new int[][] {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}}));
	}
}
