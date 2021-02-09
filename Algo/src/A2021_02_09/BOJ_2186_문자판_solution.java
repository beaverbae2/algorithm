package A2021_02_09;

import java.util.*;
import java.io.*;

/**
 * DFS + DP
 * 
 * @author beaverbae
 * @see https://soboruya.tistory.com/38
 * 
 */

public class BOJ_2186_문자판_solution {
	static int R, C, K;
	static char[][] map;
	static int[][][] dp;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static char[] target;
	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		target = br.readLine().toCharArray();
		N = target.length;

		// dp배열 초기화
		dp = new int[R][C][N];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == target[0]) {
					answer += dfs(i, j, 0);
				}
			}
		}
		System.out.println(answer);
	}

	private static int dfs(int r, int c, int idx) {
		if (idx == N - 1)
			return dp[r][c][idx] = 1;
		if (dp[r][c][idx] != -1)
			return dp[r][c][idx];

		dp[r][c][idx] = 0;// 방문 처리
		for (int k = 1; k <= K; k++) {
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + k * dirs[d][0];
				int nc = c + k * dirs[d][1];

				if (!isInMap(nr, nc))
					continue;
				if (map[nr][nc] == target[idx + 1]) {
					dp[r][c][idx] += dfs(nr, nc, idx + 1);
				}
			}
		}

		return dp[r][c][idx];
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
