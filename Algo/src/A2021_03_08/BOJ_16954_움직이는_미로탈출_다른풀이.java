package A2021_03_08;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * @author 김재현
 *
 */

public class BOJ_16954_움직이는_미로탈출_다른풀이 {
	static int map[][] = new int[8][8];
	static int wall = 0;
	static boolean flag;
	static int dirs[][] = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
			{ -1, -1 } };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 8; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '#') {
					wall++; // 벽 갯수 정해놓기
				}
			}
		}
		BFS();
		System.out.println(0);
	}

	private static void BFS() {
		Queue<miro> q = new LinkedList<>();
		q.add(new miro(7, 0));

		while (!q.isEmpty()) {
			int size = q.size();
			visited = new boolean[8][8];

			for (int i = 0; i < size; i++) {
				miro temp = q.poll();

				if (map[temp.r][temp.c] == '#') {
					continue;
				}
				if (temp.r == 0) {
					System.out.println(1);
					System.exit(0);
				}

				if (wall == 0 && q.size() > 0) {
					System.out.println(1);
					System.exit(0);
				}

				for (int d = 0; d < dirs.length; d++) {
					int dr = temp.r + dirs[d][0];
					int dc = temp.c + dirs[d][1];

					if (isOK(dr, dc)) {
						if (!visited[dr][dc] && map[dr][dc] != '#') {
							q.add(new miro(dr, dc));
							visited[dr][dc] = true;
						}
					}
				}
			}
			down();
		}
		return;
	}

	private static void down() {

		for (int i = 0; i <= 7; i++) { // 맨 아랫층 제거
			if (map[7][i] == '#')
				wall--;
		}

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) {
					map[i][j] = '.';
				} else {
					map[i][j] = map[i - 1][j];
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 8 && dc >= 0 && dc < 8;
	}

	static class miro {
		int r;
		int c;

		public miro(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
