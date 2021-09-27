package A2021_09_14;

import java.util.*;
import java.io.*;

/**
 * DP
 * 50MIN
 * @author beaverbae
 * 어려웠던 부분 dp배열 선언, dfs탐색
 */

public class BOJ_17069_파이프_옮기기2 {
	static int N;
	static boolean[][] board;
	static long[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new boolean[N][N];
		dp = new long[N][N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1)
					continue;
				board[i][j] = true;
			}
		}

		dfs(0, 0, 0);
		System.out.println(dp[0][0][0]);
	}

	private static long dfs(int x, int y, int status) {
		if (dp[x][y][status] != -1)
			return dp[x][y][status];
		dp[x][y][status] = 0;// 방문 표시

		// 가로
		if (status == 0) {
			if (x == N - 1 && y == N - 2)
				return dp[x][y][status] = 1L;

			if (moveHorizen(x, y + 1)) {
				dp[x][y][status] += dfs(x, y + 1, 0);
			}
			if (moveDiagonal(x, y + 1)) {
				dp[x][y][status] += dfs(x, y + 1, 2);
			}

		}
		// 세로
		else if (status == 1) {
			if (x == N - 2 && y == N - 1)
				return dp[x][y][status] = 1L;

			if (moveVertical(x + 1, y)) {
				dp[x][y][status] += dfs(x + 1, y, 1);
			}
			if (moveDiagonal(x + 1, y)) {
				dp[x][y][status] += dfs(x + 1, y, 2);
			}

		}
		
		// 대각
		else if (status == 2) {
			if (x == N - 2 && y == N - 2)
				return dp[x][y][status] = 1L;

			if (moveHorizen(x + 1, y + 1)) {
				dp[x][y][status] += dfs(x + 1, y + 1, 0);
			}
			if (moveVertical(x + 1, y + 1)) {
				dp[x][y][status] += dfs(x + 1, y + 1, 1);
			}
			if (moveDiagonal(x + 1, y + 1)) {
				dp[x][y][status] += dfs(x + 1, y + 1, 2);
			}

		}
		return dp[x][y][status];
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static boolean moveDiagonal(int x, int y) {
		return isIn(x + 1, y) && board[x + 1][y] && isIn(x, y + 1) && board[x][y + 1] && isIn(x + 1, y + 1)
				&& board[x + 1][y + 1];
	}

	private static boolean moveVertical(int x, int y) {
		return isIn(x + 1, y) && board[x + 1][y];
	}

	private static boolean moveHorizen(int x, int y) {
		return isIn(x, y + 1) && board[x][y + 1];
	}
}
