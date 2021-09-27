package A2021_02_16;

import java.util.*;

/**
 * 1H 40MIN
 * Simulation
 * @author beaverbae
 *
 */

public class PGS_블록_게임 {
	int N = 0;// 블록개수 + 1
	Pair[] startPos;// startPos[i] : i번째 블록의 시작점
	int answer = 0;
	final int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	int R, C;
	int[][] board;
	List<Integer>[][] tofillBlockPos; // tofillBlockPos[r][c] : (r, c)에서 속을 채우는 블록들의 번호 
	boolean[][] visited;// 같은 블록 찾을 때 사용
	TreeSet<Integer> rowSet;// 같은 블록 찾았을 떄의 r좌표
	TreeSet<Integer> colSet;// 같은 블록 찾았을 떄의 c좌표
	int blockcnt;// 같은 블록을 찾은 개수
	List<Pair>[] blockPos;//blockPos[i] i번 블록들(4개)의 위치 

	public int solution(int[][] board) {
		this.board = board;
		R = board.length;
		C = board.length;
		
		// 블록 개수 구하기 (N 구하기)
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				N = Math.max(N, board[i][j]);
			}
		}
		N += 1;

		// 각 블록의 시작 좌표 구하기
		startPos = new Pair[N];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c] == 0 || startPos[board[r][c]] != null)
					continue;

				startPos[board[r][c]] = new Pair(r, c);
			}
		}

		// 채워야 하는 블록 파악
		tofillBlockPos = new List[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tofillBlockPos[i][j] = new ArrayList<>();
			}
		}
		
		blockPos = new List[N];
		for (int i = 1; i < blockPos.length; i++) {
			blockPos[i] = new ArrayList<>();
		}

		for (int i = 0; i < startPos.length; i++) {
			if (startPos[i] == null)
				continue;

			visited = new boolean[R][C];
			rowSet = new TreeSet<>();
			colSet = new TreeSet<>();
			blockcnt = 1;

			findSameBlock(i, startPos[i].r, startPos[i].c);
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (tofillBlockPos[i][j].isEmpty())
					continue;
			}
		}

		while (true) {
			boolean isEnd = true; //제거할 수 있는 블록이 없다 -> true
			
			List<Pair>[] list = new List[N];// list[i] : i번 블록의 속 좌표와 1x1 블록이 놓일 수 있는 경우 (r, c) 추가
			for (int i = 1; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}

			for (int c = 0; c < C; c++) {
				for (int r = 0; r < R; r++) {
					if (board[r][c] != 0) {
						int block = board[r][c];// 이 블록 위로는 이 블록 번호의 1x1 블록이 놓여짐

						for (int r2 = r - 1; r2 >= 0; r2--) {
							for (int b : tofillBlockPos[r2][c]) {
								if (b == block) {// 1x1 블록의 번호와 블록의 속 번호와 동일 한 경우
									list[b].add(new Pair(r2, c));

									if (list[b].size() == 2) {// 속 2개 다 채운 경우
										isEnd = false;
										
										// board에서 원래 블록이 빈칸 처리
										for (Pair p : blockPos[b]) {
											board[p.r][p.c] = 0;
										}

										answer++;// 정답 추가
									}
								}
							}
						}
						break;// 종료
					}
				}
			}

			if (isEnd)
				break;
		}

		return answer;
	}
	
	// 재귀 사용
	void findSameBlock(int num, int r, int c) {
		visited[r][c] = true;
		rowSet.add(r);
		colSet.add(c);

		if (blockcnt == 4) {
			for (int row : rowSet) {
				for (int col : colSet) {
					if (visited[row][col]) {
						blockPos[num].add(new Pair(row, col));
					} else {
						tofillBlockPos[row][col].add(num);
					}
				}
			}

			return;
		}

		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];

			if (!isInMap(nr, nc) || board[nr][nc] != num || visited[nr][nc])
				continue;

			blockcnt++;
			findSameBlock(num, nr, nc);
		}
	}

	boolean isInMap(int nr, int nc) {
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

	public static void main(String[] args) {
		new PGS_블록_게임().solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 },
				{ 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } });
	}
}
