package A2021_02_04;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 
 * @author beaverbae
 *
 */
public class BOJ_16946_벽_부수고_이동하기_4 {
	static boolean[][] map;
	static List<Pair> walls;
	static List<Pair> blanks;

	static int R, C;
	static int[][] answer;
	static Node[][] check;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new boolean[R][C];
		answer = new int[R][C];
		visited = new boolean[R][C];
		walls = new LinkedList<>();
		blanks = new LinkedList<>();

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				char ch = str.charAt(c);

				if (ch == '0') {
					map[r][c] = true;
					blanks.add(new Pair(r, c));
				}
			}
		}

		check = new Node[R][C];

		int idx = 0;
		for (Pair p : blanks) {
			int r = p.r;
			int c = p.c;

			if (!map[r][c] || check[r][c] != null)
				continue;

			getBlankCounts(idx++, r, c);
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!map[r][c]) {
					HashSet<Integer> set = new HashSet<>();
					int moveCnt = 1;// 자기 자신 포함
					for (int d = 0; d < dirs.length; d++) {
						int nr = r + dirs[d][0];
						int nc = c + dirs[d][1];

						if (!isInMap(nr, nc) || !map[nr][nc]) {
							continue;
						}

						if (set.contains(check[nr][nc].idx)) {
							continue;
						}

						set.add(check[nr][nc].idx);
						moveCnt += check[nr][nc].cnt;
					}
					answer[r][c] = moveCnt % 10;
				}
				sb.append(answer[r][c]);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void getBlankCounts(int idx, int sr, int sc) {
		Queue<Pair> q = new LinkedList<>();

		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		List<Pair> paths = new ArrayList<>();
		paths.add(new Pair(sr, sc));
		int cnt = 1;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;

			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				// 범위 밖 or 벽 or 방문한 경우 pass
				if (!isInMap(nr, nc) || !map[nr][nc] || visited[nr][nc]) {
					continue;
				}

				q.offer(new Pair(nr, nc));
				visited[nr][nc] = true;
				paths.add(new Pair(nr, nc));
				cnt++;
			}
		}

		for (int i = 0; i < paths.size(); i++) {
			Pair p = paths.get(i);
			int r = p.r;
			int c = p.c;

			check[r][c] = new Node(idx, cnt);
		}
	}

	private static boolean isInMap(int nr, int nc) {
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
		int idx, cnt;

		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "" + idx;
		}
	}
}
