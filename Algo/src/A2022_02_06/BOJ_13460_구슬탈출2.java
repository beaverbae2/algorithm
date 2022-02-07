package A2022_02_06;

import java.util.*;
import java.io.*;

public class BOJ_13460_구슬탈출2 {
	static int R, C, ans;
	static final int INF = 11;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = INF;
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R][C];
		for (int r = 0; r < R; r++) {
			board[r] = br.readLine().toCharArray();
		}
		
		dfs(board, 0);
		
		if (ans == INF) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private static void dfs(char[][] board, int cnt) {
		if (cnt == INF - 1) return;
		
		for (int dir = 0; dir < 4; dir++) {
			char[][] next_board = new char[R][C];
			deepCopy(board, next_board);
			boolean[] arr = check(next_board, dir);
			
			if (arr[0] || arr[1]) {
				if (!arr[1]) ans = Math.min(ans, cnt+1);
				continue;
			}
			
			dfs(next_board, cnt+1);
		}
	}
	
	private static void deepCopy(char[][] board, char[][] next_board) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				next_board[r][c] = board[r][c];
			}
		}
	}

	private static boolean[] check(char[][] next_board, int dir) {
		boolean redGoal = false;
		boolean blueGoal = false;
		boolean redMoved = false;
		boolean blueMoved = false;
		
		if (dir == 0) {// 동
			for (int c = C-2; c > 0; c--) {
				for (int r = 1; r < R-1; r++) {
					if (redMoved && blueMoved) break;
					
					if (!redMoved && next_board[r][c] == 'R') {
						redGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						redMoved = true;
					}
					
					if (!blueMoved && next_board[r][c] == 'B') {
						blueGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						blueMoved = true;
					}
				}
				
				if (redMoved && blueMoved) break;
			}
		} else if (dir == 1) {// 서
			for (int c = 1; c < C-1; c++) {
				for (int r = 1; r < R-1; r++) {
					if (redMoved && blueMoved) break;
					
					if (!redMoved && next_board[r][c] == 'R') {
						redGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						redMoved = true;
					}
					
					if (!blueMoved && next_board[r][c] == 'B') {
						blueGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						blueMoved = true;
					}
				}
				
				if (redMoved && blueMoved) break;
			}
		} else if (dir == 2) {// 남
			for (int r = R-2; r > 0; r--) {
				for (int c = 1; c < C-1; c++) {
					if (redMoved && blueMoved) break;
					
					if (!redMoved && next_board[r][c] == 'R') {
						redGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						redMoved = true;
					}
					
					if (!blueMoved && next_board[r][c] == 'B') {
						blueGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						blueMoved = true;
					}
				}
				
				if (redMoved && blueMoved) break;
			}
		} else {// 북
			for (int r = 1; r < R-1; r++) {
				for (int c = 1; c < C-1; c++) {
					if (redMoved && blueMoved) break;
					
					if (!redMoved && next_board[r][c] == 'R') {
						redGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						redMoved = true;
					}
					
					if (!blueMoved && next_board[r][c] == 'B') {
						blueGoal = isGoal(next_board, r, c, dir, next_board[r][c]);
						blueMoved = true;
					}
				}
				
				if (redMoved && blueMoved) break;
			}
		}
		
		return new boolean[] {redGoal, blueGoal};
	}

	private static boolean isGoal(char[][] next_board, int r, int c, int dir, char ch) {
		boolean result = false;
		int nr = r;
		int nc = c;
		
		if (dir == 0) {// 동
			for (nc = c; nc < C-1; nc++) {
				if (next_board[nr][nc] == 'O') {
					result = true;
					break;
				} else if (isWall(next_board[r][c], next_board[nr][nc])) {
					break;
				}
			}
			nc--;
		} else if (dir == 1) {// 서 
			for (nc = c; nc > 0; nc--) {
				if (next_board[nr][nc] == 'O') {
					result = true;
					break;
				} else if (isWall(next_board[r][c], next_board[nr][nc])) {
					break;
				}
			}
			nc++;
		} else if (dir == 2) {// 남
			for (nr = r; nr < R-1; nr++) {
				if (next_board[nr][nc] == 'O') {
					result = true;
					break;
				} else if (isWall(next_board[r][c], next_board[nr][nc])) {
					break;
				}
			}
			nr--;
		} else {// 북
			for (nr = r; nr > 0; nr--) {
				if (next_board[nr][nc] == 'O') {
					result = true;
					break;
				} else if (isWall(next_board[r][c], next_board[nr][nc])) {
					break;
				}
			}
			nr++;
		}
		
		if (!result) swap(next_board, r, c, nr, nc);
		else next_board[r][c] = '.';
		return result;
	}
	
	private static void swap(char[][] next_board, int r, int c, int nr, int nc) {
		char temp = next_board[r][c];
		next_board[r][c] = next_board[nr][nc];
		next_board[nr][nc] = temp;
	}
	
	private static boolean isWall(char c1, char c2) {
		if (c2 == '#') return true;
		else {
			if (c1 == 'B' && c2 == 'R') return true;
			else if (c1 == 'R' && c2 == 'B') return true;
			return false;
		}
	}

	private static void print(char[][] board) {
		for (int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
}
