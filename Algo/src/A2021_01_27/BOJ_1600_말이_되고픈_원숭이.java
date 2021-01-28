package A2021_01_27;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 
 * @author beaverbae
 *
 */

public class BOJ_1600_말이_되고픈_원숭이 {
	static int[][] map;
	static boolean[][][] visited;
	static int[][] dirs1 = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };
	static int[][] dirs2 = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int R, C, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[K + 1][R][C];

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = bfs();
		System.out.println(answer);
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0, K));
		visited[K][0][0] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int d = node.d;
			int k = node.k;

			if (r == R - 1 && c == C - 1) {
				return d;
			}

			if (k > 0) {
				for (int i = 0; i < dirs1.length; i++) {
					int nr = r + dirs1[i][0];
					int nc = c + dirs1[i][1];

					if (!isInMap(nr, nc) || visited[k - 1][nr][nc] || map[nr][nc] == 1) {
						continue;
					}

					q.offer(new Node(nr, nc, d + 1, k - 1));
					visited[k - 1][nr][nc] = true;
				}
			}

			for (int i = 0; i < dirs2.length; i++) {
				int nr = r + dirs2[i][0];
				int nc = c + dirs2[i][1];

				if (!isInMap(nr, nc) || visited[k][nr][nc] || map[nr][nc] == 1) {
					continue;
				}

				q.offer(new Node(nr, nc, d + 1, k));
				visited[k][nr][nc] = true;
			}
		}

		return -1;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Node {
		int r, c, d, k;

		public Node(int r, int c, int d, int k) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + ", k=" + k + "]";
		}
	}
}
