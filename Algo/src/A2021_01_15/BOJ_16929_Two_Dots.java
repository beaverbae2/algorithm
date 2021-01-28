package A2021_01_15;

import java.util.*;
import java.io.*;

/**
 * 22MIN
 * DFS - find a cycle of a graph
 * @author beaverbae
 * 
 */

public class BOJ_16929_Two_Dots {
	static int R, C;
	static boolean isCycle;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (isCycle)
					break;
				else {
					// checkCycle(map[i][j], i, j, i, j, 0);
					if (!visited[i][j]) {
						visited[i][j] = true;
						checkCycle(-1, map[i][j], i, j, i, j);
					}
				}
			}
			if (isCycle)
				break;
		}
		System.out.println("No");
	}

	private static void checkCycle(char ch, int sr, int sc, int r, int c, int cnt) {
		if (cnt >= 4 && sr == r && sc == c) {
			isCycle = true;
			return;
		}

		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];

			if (!isInMap(nr, nc) || visited[nr][nc] || map[nr][nc] != ch)
				continue;

			visited[nr][nc] = true;
			checkCycle(ch, sr, sc, nr, nc, cnt + 1);
			visited[nr][nc] = false;
		}
	}

	// This is JaeHyun's Code
	private static void checkCycle(int dir, char ch, int sr, int sc, int r, int c) {
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];

			if (!isInMap(nr, nc) || map[nr][nc] != ch)
				continue;
			if (dir != -1 && (dir + d) % 4 == 1)
				continue; // 두 개인 경우 거름

			if (visited[nr][nc]) {
				isCycle = true;
				System.out.println("Yes");
				System.exit(0);
			}

			visited[nr][nc] = true;
			checkCycle(d, ch, sr, sc, nr, nc);
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
