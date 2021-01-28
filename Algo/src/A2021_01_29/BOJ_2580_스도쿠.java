package A2021_01_29;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 
 * @author beaverbae
 *
 */

public class BOJ_2580_스도쿠 {
	static int[][] map;
	static final int R = 9, C = 9;
	static ArrayList<Pair> list;
	static int N;
	static int[][] dirs = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 },
			{ -1, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[R][C];
		list = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0) {
					list.add(new Pair(i, j));
				}
			}
		}
		N = list.size();
		dfs(0);
	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}

		boolean[] visited = new boolean[10];
		Pair p = list.get(cnt);
		int row = p.r;
		int col = p.c;

		// 가로
		for (int c = 0; c < C; c++) {
			int v = map[row][c];
			if (visited[v])
				continue;

			visited[v] = true;
		}

		// 세로
		for (int r = 0; r < R; r++) {
			int v = map[r][col];
			if (visited[v])
				continue;

			visited[v] = true;
		}

		// 3X3
		int[] center = getCenter(row, col);
		int center_r = center[0];
		int center_c = center[1];

		for (int d = 0; d < dirs.length; d++) {
			int nr = center_r + dirs[d][0];
			int nc = center_c + dirs[d][1];

			int v = map[nr][nc];
			if (visited[v])
				continue;

			visited[v] = true;
		}

		for (int i = 1; i < visited.length; i++) {
			if (visited[i])
				continue;

			map[row][col] = i;
			dfs(cnt + 1);
			map[row][col] = 0;
		}
	}

	private static int[] getCenter(int r, int c) {
		if (r >= 0 && r < 3) {
			if (c >= 0 && c < 3) {
				return new int[] { 1, 1 };
			} else if (c >= 3 && c < 6) {
				return new int[] { 1, 4 };
			} else if (c >= 6 && c < 9) {
				return new int[] { 1, 7 };
			}
		} else if (r >= 3 && r < 6) {
			if (c >= 0 && c < 3) {
				return new int[] { 4, 1 };
			} else if (c >= 3 && c < 6) {
				return new int[] { 4, 4 };
			} else if (c >= 6 && c < 9) {
				return new int[] { 4, 7 };
			}
		} else if (r >= 6 && r < 9) {
			if (c >= 0 && c < 3) {
				return new int[] { 7, 1 };
			} else if (c >= 3 && c < 6) {
				return new int[] { 7, 4 };
			} else if (c >= 6 && c < 9) {
				return new int[] { 7, 7 };
			}
		}

		return null;
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
}
