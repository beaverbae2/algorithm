package A2021_02_05;

import java.util.*;

/**
 * 
 * @author beaverbae
 *
 */
public class PGS_카드_짝_맞추기 {
	private int N;
	private int min_dist;
	private int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public int solution(int[][] board, int r, int c) {
		N = 0;
		min_dist = Integer.MAX_VALUE;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				N = Math.max(N, board[i][j]);
			}
		}
		
		dfs(r, c, 0, 0, board);
		
		System.out.println(min_dist);
		return min_dist;
	}

	public void dfs(int r, int c, int cnt, int dist, int[][] board) {
		if (cnt == N) {
			min_dist = Math.min(dist, min_dist);
			return;
		}

		Pair[] cards = new Pair[N + 1];
		getPairs(cards, board);

		for (int i = 1; i < cards.length; i++) {
			if (cards[i] == null)
				continue;
			int r1 = cards[i].r1;
			int c1 = cards[i].c1;
			int r2 = cards[i].r2;
			int c2 = cards[i].c2;

			int temp_dist = getDistance(r, c, r1, c1, board);

			temp_dist += getDistance(r1, c1, r2, c2, board);
			board[r1][c1] = 0;
			board[r2][c2] = 0;
			dfs(r2, c2, cnt + 1, dist + temp_dist, board);
			board[r1][c1] = i;
			board[r2][c2] = i;

			temp_dist = getDistance(r, c, r2, c2, board);
			temp_dist += getDistance(r2, c2, r1, c1, board);
			board[r1][c1] = 0;
			board[r2][c2] = 0;
			dfs(r1, c1, cnt + 1, dist + temp_dist, board);
			board[r1][c1] = i;
			board[r2][c2] = i;
		}
	}

	public int getDistance(int sr, int sc, int dr, int dc, int[][] board) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[board.length][board.length];
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int depth = node.depth;

			if (r == dr && c == dc) {
				return depth + 1;// 엔터누르는거
			}
			// 컨트롤 방향키
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				while (true) {
					if (!isInMap(nr, nc)) {
						int dir = turnDir(d);
						nr = nr + dirs[dir][0];
						nc = nc + dirs[dir][1];

						if (!visited[nr][nc] && board[nr][nc] == 0) {
							q.offer(new Node(nr, nc, depth + 1));
							visited[nr][nc] = true;
						}

						break;
					}

					else if (board[nr][nc] != 0) {
						if (!visited[nr][nc]) {
							q.offer(new Node(nr, nc, depth + 1));
							visited[nr][nc] = true;
						}
						break;
					}

					nr = nr + dirs[d][0];
					nc = nc + dirs[d][1];
				}

				// 그냥 방향키
				nr = r + dirs[d][0];
				nc = c + dirs[d][1];
				
				if (isInMap(nr, nc) && !visited[nr][nc]) {
					q.offer(new Node(nr, nc, depth + 1));
					visited[nr][nc] = true;
				}
			}
		}

		return -1;
	}
	
	public int turnDir(int dir) {
		if (dir == 0)
			dir = 1;
		else if (dir == 1)
			dir = 0;
		else if (dir == 2)
			dir = 3;
		else if (dir == 3)
			dir = 2;
		return dir;
	}

	public boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < 4 && nc >= 0 && nc < 4;
	}

	public void getPairs(Pair[] cards, int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0)
					continue;

				int idx = board[i][j];
				if (cards[idx] == null) {
					cards[idx] = new Pair(i, j, 0, 0);
				} else {
					cards[idx].r2 = i;
					cards[idx].c2 = j;
				}
			}
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

	static class Pair {
		int r1, c1, r2, c2;

		public Pair(int r1, int c1, int r2, int c2) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}

		@Override
		public String toString() {
			return "Node [r1=" + r1 + ", c1=" + c1 + ", r2=" + r2 + ", c2=" + c2 + "]";
		}
	}

	public static void main(String[] args) {
		new PGS_카드_짝_맞추기().solution(new int[][] { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } }, 1,
				0);
//		new PGS_카드_짝_맞추기().solution(new int[][] { { 3, 0, 0, 2 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, { 2, 0, 0, 3 } }, 0,
//				1);
	}
}
