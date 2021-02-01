package A2021_02_01;

import java.util.*;

/**
 * Simulation
 * VERY SLOW
 * @author beaverbae
 * 
 */
public class PGS_리틀프렌즈_사천성ver2 {
	private String answer = "";
	private char[][] map;
	private char[] selected;
	private boolean[] visited = new boolean[26];
	private Pair[][] position = new Pair[26][2];
	private int R, C, N;
	private boolean flag;
	private char[] alphbet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public String solution(int m, int n, String[] board) {
		R = m;
		C = n;
		N = 0;
		flag = false;

		// board -> map
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				char ch = board[i].charAt(j);
				map[i][j] = ch;

				if (ch == '*' || ch == '.')
					continue;

				int idx = ch - 'A';
				if (position[idx][0] == null) {
					position[idx][0] = new Pair(i, j);
				} else {
					position[idx][1] = new Pair(i, j);
					N++;
				}
			}
		}

		selected = new char[N];
		dfs(0);
		if (!flag) {
			answer = "IMPOSSIBLE";
		}
		System.out.println(answer);
		return answer;
	}

	private void dfs(int cnt) {
		if (flag)
			return;

		if (cnt == N) {
			StringBuilder sb = new StringBuilder();
			for (char ch : selected) {
				sb.append(ch);
			}
			answer = sb.toString();
			flag = true;
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			if (visited[i] || position[i][0] == null)
				continue;

			int sr = position[i][0].r;
			int sc = position[i][0].c;
			int er = position[i][1].r;
			int ec = position[i][1].c;

			if (!check2(sr, sc, er, ec))
				continue;
			
			visited[i] = true;
			selected[cnt] = alphbet[i];
			map[sr][sc] = '.';
			map[er][ec] = '.';
			dfs(cnt + 1);
//			visited[i] = false;
//			map[sr][sc] = alphbet[i];
//			map[er][ec] = alphbet[i];
		}
	}

	// more faster than check
	private boolean check2(int sr, int sc, int er, int ec) {
		boolean result = false;
		boolean temp1 = false;
		boolean temp2 = false;
		if (sr == er) {
			result = true;
			for (int c = sc; c <= ec; c++) {
				if (!isOk2(sr, c, sr, sc, er, ec)) {
					result = false;
					break;
				}
			}
			return result;
		} else if (sc == ec) {
			result = true;
			for (int r = sr; r <= er; r++) {
				if (!isOk2(r, sc, sr, sc, er, ec)) {
					result = false;
					break;
				}
			}
			return result;
		} else if (sc < ec) {
			temp1 = true;
			temp2 = true;
			// 1
			for (int c = sc; c <= ec; c++) {
				if (!isOk2(sr, c, sr, sc, er, ec)) {
					temp1 = false;
					break;
				}
			}
			if (temp1) {
				for (int r = sr; r <= er; r++) {
					if (!isOk2(r, ec, sr, sc, er, ec)) {
						temp1 = false;
						break;
					}
				}
			}

			// 2
			if (!temp1) {
				for (int r = sr; r <= er; r++) {
					if (!isOk2(r, sc, sr, sc, er, ec)) {
						temp2 = false;
						break;
					}
				}
				if (temp2) {
					for (int c = sc; c <= ec; c++) {
						if (!isOk2(er, c, sr, sc, er, ec)) {
							temp2 = false;
							break;
						}
					}
				}
			}
			return temp1 | temp2;

		} else if (sc > ec) {
			temp1 = true;
			temp2 = true;

			// 1
			for (int c = sc; c >= ec; c--) {
				if (!isOk2(sr, c, sr, sc, er, ec)) {
					temp1 = false;
					break;
				}
			}
			if (temp1) {
				for (int r = sr; r <= er; r++) {
					if (!isOk2(r, ec, sr, sc, er, ec)) {
						temp1 = false;
						break;
					}
				}
			}

			// 2
			if (!temp1) {
				for (int r = sr; r <= er; r++) {
					if (!isOk2(r, sc, sr, sc, er, ec)) {
						temp2 = false;
						break;
					}
				}
				if (temp2) {
					for (int c = sc; c >= ec; c--) {
						if (!isOk2(er, c, sr, sc, er, ec)) {
							temp2 = false;
							break;
						}
					}
				}
			}

			return temp1 | temp2;
		}
		return false;
	}

	private boolean isOk2(int nr, int nc, int sr, int sc, int er, int ec) {
		if (map[nr][nc] == '.' || (nr == er && nc == ec) || (nr == sr && nc == sc)) {
			return true;
		}

		return false;
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
		new PGS_리틀프렌즈_사천성ver2().solution(3, 3, new String[] { "DBA", "C*A", "CDB" });
		new PGS_리틀프렌즈_사천성ver2().solution(2, 4, new String[] { "NRYN", "ARYA" });
		new PGS_리틀프렌즈_사천성ver2().solution(4, 4, new String[] { ".ZI.", "M.**", "MZU.", ".IU." });
		new PGS_리틀프렌즈_사천성ver2().solution(2, 2, new String[] { "AB", "BA" });
		new PGS_리틀프렌즈_사천성ver2().solution(2, 26,
				new String[] { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "AYXWVUTSRQPONMLKJIHGFEDCBZ" });
		new PGS_리틀프렌즈_사천성ver2().solution(3, 3, new String[] { "A.*", "..A", "..." });
	}
}
