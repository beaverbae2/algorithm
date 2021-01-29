package A2021_01_29;

import java.util.*;

/**
 * Simulation
 * @author beaverbae
 *
 */

public class PGS_자물쇠와_열쇠 {
	public boolean solution(int[][] key, int[][] lock) {
		int hole = 0;// 자물쇠의 홈 개수
		int N = lock.length;
		int M = key.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == 0) {
					hole++;
				}
			}
		}

		int K = 2 * M + N - 2;
		int[][] map = new int[K][K];
		for (int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], -1);// 자물쇠 영역 밖
		}

		// map의 중앙에 자물쇠 놓기
		for (int i = M - 1; i < K - M + 1; i++) {
			for (int j = M - 1; j < K - M + 1; j++) {
				map[i][j] = lock[i - M + 1][j - M + 1];
			}
		}

		for (int k = 0; k < 4; k++) {
			int[][] temp_key = new int[M][M];
			deepcopy(key, temp_key, k, M);

			for (int i = 0; i < K - M + 1; i++) {
				to: for (int j = 0; j < K - M + 1; j++) {
					int temp_hole = 0;// 맞는 홈의 개수
					for (int r = i; r < i + M; r++) {
						for (int c = j; c < j + M; c++) {
							if (map[r][c] == -1)
								continue;
							else if (temp_key[r - i][c - j] == 1) {
								if (map[r][c] == 0)
									temp_hole++;
								else
									continue to;
							}
						}
					}
					if (temp_hole == hole) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void deepcopy(int[][] map, int[][] temp_map, int k, int N) {
		if (k == 0) {// 그대로
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp_map[i][j] = map[i][j];
				}
			}

		} else if (k == 1) {// 오른쪽 90도
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp_map[i][j] = map[N - j - 1][i];
				}
			}
		} else if (k == 2) {// 180도
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp_map[i][j] = map[N - i - 1][N - j - 1];
				}
			}
		} else if (k == 3) {// 왼쪽 90도
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					temp_map[i][j] = map[j][N - i - 1];
				}
			}
		}

	}

	public static void main(String[] args) {
		new PGS_자물쇠와_열쇠().solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
				new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } });
	}
}
