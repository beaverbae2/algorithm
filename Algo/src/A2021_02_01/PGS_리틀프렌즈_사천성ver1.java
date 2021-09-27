package A2021_02_01;

import java.util.*;

/**
 * Simulation
 * VERY SLOW
 * @author beaverbae
 * 
 */
public class PGS_리틀프렌즈_사천성ver1 {
	private String answer = "";
	private char[][] map;
	private char[] selected;
	private boolean[] visited = new boolean[26];
	private Pair[][] position = new Pair[26][2];
	private int R, C, N;
	private boolean flag;
	private char[] alphbet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public String solution(int m, int n, String[] board) {
		R = m;
		C = n;
		N = 0;
		flag = false;

		// board -> map
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				char ch = board[i].charAt(j);
				map[i][j] = ch;

				if (ch == '*' || ch == '.')
					continue;

				int idx = ch - 'A';
				if (position[idx][0] == null) {
					position[idx][0] = new Pair(i, j);
				} else {
					position[idx][1] = new Pair(i, j);
					N++;
				}
			}
		}

		selected = new char[N];
		dfs(0);
		if (!flag) {
			answer = "IMPOSSIBLE";
		}
		System.out.println(answer);
		return answer;
	}

	private void dfs(int cnt) {
		if (flag)
			return;

		if (cnt == N) {
			StringBuilder sb = new StringBuilder();
			for (char ch : selected) {
				sb.append(ch);
			}
			answer = sb.toString();
			flag = true;
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			if (visited[i] || position[i][0] == null)
				continue;

			int sr = position[i][0].r;
			int sc = position[i][0].c;
			int er = position[i][1].r;
			int ec = position[i][1].c;

			if (!check(sr, sc, er, ec))
				continue;
			
//			System.out.println(cnt+" : "+alphbet[i]);
			visited[i] = true;
			selected[cnt] = alphbet[i];
			map[sr][sc] = '.';
			map[er][ec] = '.';
			dfs(cnt + 1);
//			visited[i] = false;
//			map[sr][sc] = alphbet[i];
//			map[er][ec] = alphbet[i];
		}
	}

	private boolean check(int sr, int sc, int er, int ec) {

		Queue<Node> q = new LinkedList<>();
		boolean[][][] isSelected = new boolean[R][C][3];
		q.offer(new Node(sr, sc, -1, 0));
		isSelected[sr][sc][0] = true;
		isSelected[sr][sc][1] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int dir = node.dir;
			int cnt = node.cnt;

			if (dir == -1) {// 처음
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (!isInMap(nr, nc) || !isOk(nr, nc, er, ec) || isSelected[nr][nc][d])
						continue;

					if (nr == er && nc == ec) {
						return true;
					}

					isSelected[nr][nc][d] = true;
					q.offer(new Node(nr, nc, d, cnt));
				}
			} else {
				int nr = 0;
				int nc = 0;

				if (cnt == 0) {// 방향 전환 가능
					int[] turn = turn(dir);

					for (int i = 0; i < turn.length; i++) {
						int d = turn[i];
						nr = r + dirs[d][0];
						nc = c + dirs[d][1];

						if (!isInMap(nr, nc) || !isOk(nr, nc, er, ec) || isSelected[nr][nc][d])
							continue;

						if (nr == er && nc == ec) {
							return true;
						}

						isSelected[nr][nc][d] = true;
						q.offer(new Node(nr, nc, d, cnt + 1));
					}
				}

				nr = r + dirs[dir][0];
				nc = c + dirs[dir][1];

				if (!isInMap(nr, nc) || !isOk(nr, nc, er, ec) || isSelected[nr][nc][dir])
					continue;

				if (nr == er && nc == ec) {
					return true;
				}

				isSelected[nr][nc][dir] = true;
				q.offer(new Node(nr, nc, dir, cnt));
			}

		}
		return false;
	}

	private boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private boolean isOk(int nr, int nc, int er, int ec) {
		if (map[nr][nc] == '.' || (nr == er && nc == ec)) {
			return true;
		}

		return false;
	}

	private int[] turn(int dir) {
		if (dir == 0 || dir == 2) {
			return new int[] { 1 };
		} else {
			return new int[] { 0, 2 };
		}
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
		int r, c, dir, cnt;

		public Node(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", dir=" + dir + ", cnt=" + cnt + "]";
		}
	}

	public static void main(String[] args) {
		new PGS_리틀프렌즈_사천성ver1().solution(3, 3, new String[] { "DBA", "C*A", "CDB" });
		new PGS_리틀프렌즈_사천성ver1().solution(2, 4, new String[] { "NRYN", "ARYA" });
		new PGS_리틀프렌즈_사천성ver1().solution(4, 4, new String[] { ".ZI.", "M.**", "MZU.", ".IU." });
		new PGS_리틀프렌즈_사천성ver1().solution(2, 2, new String[] { "AB", "BA" });
		new PGS_리틀프렌즈_사천성ver1().solution(2, 26,
				new String[] { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "AYXWVUTSRQPONMLKJIHGFEDCBZ" });
		new PGS_리틀프렌즈_사천성ver1().solution(3, 3, new String[] { "A.*", "..A", "..." });
	}
}
