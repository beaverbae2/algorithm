package A2021_02_24;

import java.util.*;

/**
 * Simulation
 * 78MIN
 * @author beaverbae
 *
 */

public class PGS_블록_게임_review {
	int[][] board;
	int N;
	boolean[][] filled;
	boolean[][] visited;
	List<Integer>[][] toFillBlock;
	List<Pair>[] blockPos;
	TreeSet<Integer> rowSet;
	TreeSet<Integer> colSet;
	int sameBlockCnt;
	int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	int max_block;

	public int solution(int[][] board) {
		this.board = board;
		N = board.length;
		visited = new boolean[N][N];
		toFillBlock = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				toFillBlock[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max_block = Math.max(max_block, board[i][j]);
			}
		}

		blockPos = new List[max_block + 1];
		for (int i = 1; i < blockPos.length; i++) {
			blockPos[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] || board[i][j] == 0)
					continue;

				filled = new boolean[N][N];
				rowSet = new TreeSet<>();
				colSet = new TreeSet<>();

				visited[i][j] = true;
				filled[i][j] = true;
				rowSet.add(i);
				colSet.add(j);
				sameBlockCnt = 1;
				dfs(i, j, board[i][j]);
			}
		}

		int answer = getRemovedBlock();
		return answer;
	}

	int getRemovedBlock() {
		int total_removed = 0;

		while (true) {
			int removed = 0;
			int[] block_filled_cnt = new int[max_block + 1];
			int[][] next_board = new int[N][N];

			deepCopy(board, next_board);

			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					if (board[r][c] != 0) {
						for (int row = r - 1; row >= 0; row--) {
							if (toFillBlock[row][c] == null)
								continue;

							for (int block_id : toFillBlock[row][c]) {
								if (blockPos[block_id] == null)
									continue;

								block_filled_cnt[block_id]++;

								if (block_filled_cnt[block_id] == 2) {
									for (Pair p : blockPos[block_id]) {
										next_board[p.r][p.c] = 0;
									}
									blockPos[block_id] = null;
									removed++;
								}
							}
						}
						break;
					}
				}
			}

			if (removed == 0)
				break;
			total_removed += removed;
			board = next_board;
		}

		return total_removed;
	}

	void dfs(int r, int c, int block_id) {
		if (sameBlockCnt == 4) {
			for (int row : rowSet) {
				for (int col : colSet) {
					if (filled[row][col]) {
						blockPos[block_id].add(new Pair(row, col));
					} else {
						toFillBlock[row][col].add(block_id);
					}
				}
			}
			return;
		}

		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];

			if (!isInMap(nr, nc) || filled[nr][nc])
				continue;

			if (board[nr][nc] == block_id) {
				filled[nr][nc] = true;
				visited[nr][nc] = true;
				sameBlockCnt++;
				rowSet.add(nr);
				colSet.add(nc);
				dfs(nr, nc, block_id);
			}
		}
	}

	void deepCopy(int[][] src, int[][] dest) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}

	boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
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

	public static void main(String[] args) {
		System.out.println(new PGS_블록_게임_review().solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 }, { 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 },
				{ 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 }, { 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } }));
	}
}
