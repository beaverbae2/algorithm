package A2021_02_27;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 
 * @author beaverbae
 *
 */

public class BOJ_3197_백조의_호수 {
	static char[][] map, copy_map;
	static int R, C;
	static int sr, sc, er, ec;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<Node> q, next_q;
	static Queue<Node> melt_q, next_melt_q;
	static boolean[][] visited;
	static int[][] melt_visited;
	static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		sr = -1;
		sc = -1;
		er = -1;
		ec = -1;
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'L') {
					if (sr == -1 && sc == -1) {
						sr = i;
						sc = j;
					} else {
						er = i;
						ec = j;
					}
					map[i][j] = '.';
				}
			}
		}
		int answer = 0;
		visited = new boolean[R][C];
		melt_visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(melt_visited[i], INF);
		}

		copy_map = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy_map[i][j] = map[i][j];
			}
		}

		while (true) {
			melt(answer);
			if (bfs(answer))
				break;
			answer++;
		}
		System.out.println(answer);
	}

	private static void melt(int answer) {
		if (next_melt_q == null) {// 처음일 때
			melt_q = new LinkedList<>();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == '.')
						continue;

					int cnt = 0;
					for (int d = 0; d < dirs.length; d++) {
						int nr = r + dirs[d][0];
						int nc = c + dirs[d][1];

						if (isInMap(nr, nc) && map[nr][nc] == '.') {
							cnt++;
						}
					}

					if (cnt > 0) {
						melt_q.offer(new Node(r, c));
						melt_visited[r][c] = answer;
					}
				}
			}

			next_melt_q = melt_q;
		} else {
			melt_q = next_melt_q;
			next_melt_q = new LinkedList<>();

			while (!melt_q.isEmpty()) {
				Node node = melt_q.poll();
				int r = node.r;
				int c = node.c;

				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (isInMap(nr, nc) && melt_visited[nr][nc] > answer && map[nr][nc] == 'X') {
						next_melt_q.offer(new Node(nr, nc));
						melt_visited[nr][nc] = answer;
					}
				}
			}
		}

	}

	private static boolean bfs(int answer) {
		if (next_q == null) {// 처음일 때
			q = new LinkedList<>();
			q.offer(new Node(sr, sc));
			visited[sr][sc] = true;
		} else {
			q = next_q;
		}

		next_q = new LinkedList<>();

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;

			if (r == er && c == ec)
				return true;

			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				if (isInMap(nr, nc) && !visited[nr][nc]) {
					if (melt_visited[nr][nc] == answer) {
						next_q.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					} else if (map[nr][nc] == '.' || melt_visited[nr][nc] < answer) {// melt_visited[nr][nc] < answer :  이동할 수 있는 경우임!!!
						q.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}

		}

		return false;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
	}
}
