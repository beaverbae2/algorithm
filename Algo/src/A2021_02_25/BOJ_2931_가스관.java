package A2021_02_25;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */

public class BOJ_2931_가스관 {
	static int[][] dirs = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char[][] map;
	static int R, C;
	static Pair M, Z;
	static Pair target;
	static boolean[] isFromTargetDirs;
	static boolean[] isToTargetDirs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'M') {
					M = new Pair(i, j);
				} else if (map[i][j] == 'Z') {
					Z = new Pair(i, j);
				}
			}
		}

		isFromTargetDirs = new boolean[5];
		isToTargetDirs = new boolean[5];
		bfs(M.r, M.c, Z.r, Z.c);
		bfs(Z.r, Z.c, M.r, M.c);

		char block = getBlock();
		System.out.println((target.r + 1) + " " + (target.c + 1) + " " + block);
	}

	private static char getBlock() {
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = 1; i < dirs.length; i++) {
			if (isToTargetDirs[i] && isFromTargetDirs[i]) {
				temp.add(i);
			}
		}

		if (temp.size() == 2) {
			int first = temp.get(0);
			int second = temp.get(1);

			if (second - first != 1) {
				return '+';
			}
		}

		if (isToTargetDirs[1]) {
			if (isToTargetDirs[2]) {
				return '|';
			} else if (isToTargetDirs[3]) {
				return '1';
			} else if (isToTargetDirs[4]) {
				return '4';
			}
		} else if (isToTargetDirs[2]) {
			if (isToTargetDirs[3]) {
				return '2';
			} else if (isToTargetDirs[4]) {
				return '3';
			}
		} else if (isToTargetDirs[3]) {
			if (isToTargetDirs[4]) {
				return '-';
			}
		}

		return 0;
	}

	private static void bfs(int sr, int sc, int er, int ec) {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][5];

		q.offer(new Node(sr, sc, 0));
		visited[sr][sc][0] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();

			int r = node.r;
			int c = node.c;
			int dir = node.dir;

			if (dir == 0) {
				for (int d = 1; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					visited[r][c][d] = true;

					if (isInMap(nr, nc) && map[nr][nc] != '.') {
						int next_dir = turnDir(nr, nc, d, map[nr][nc]);
						if (next_dir == -1 || visited[nr][nc][next_dir])
							continue;

						visited[nr][nc][next_dir] = true;
						q.offer(new Node(nr, nc, next_dir));
					}
				}
			} else {
				int nr = r + dirs[dir][0];
				int nc = c + dirs[dir][1];

				if (isInMap(nr, nc)) {
					if (map[nr][nc] == '.') {
						if (target == null) {
							target = new Pair(nr, nc);
						}
						isToTargetDirs[dir] = true;

						int next_r = nr + dirs[dir][0];
						int next_c = nc + dirs[dir][1];

						if (isInMap(next_r, next_c)) {
							if (turnDir(next_r, next_c, dir, map[next_r][next_c]) != -1) {
								isFromTargetDirs[dir] = true;
							}
						}
					} else {
						int next_dir = turnDir(nr, nc, dir, map[nr][nc]);
						if (next_dir == -1 || visited[nr][nc][next_dir])
							continue;

						visited[nr][nc][next_dir] = true;
						q.offer(new Node(nr, nc, next_dir));
					}
				}
			}
		}
	}

	static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static int turnDir(int nr, int nc, int dir, char ch) {
		if (ch == '|') {
			if (dir == 1 || dir == 2) {
				return dir;
			}
		} else if (ch == '-') {
			if (dir == 3 || dir == 4) {
				return dir;
			}
		} else if (ch == '+') {
			return dir;
		} else if (ch == '1') {
			if (dir == 1) {
				return 4;
			} else if (dir == 3) {
				return 2;
			}
		} else if (ch == '2') {
			if (dir == 2) {
				return 4;
			} else if (dir == 3) {
				return 1;
			}
		} else if (ch == '3') {
			if (dir == 2) {
				return 3;
			} else if (dir == 4) {
				return 1;
			}
		} else if (ch == '4') {
			if (dir == 1) {
				return 3;
			} else if (dir == 4) {
				return 2;
			}
		}

		return -1;
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
		int r, c, dir;

		public Node(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
	}
}
