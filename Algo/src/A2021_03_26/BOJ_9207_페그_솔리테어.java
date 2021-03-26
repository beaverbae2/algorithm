package A2021_03_26;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 38MIN
 * @author beaverbae
 *
 */

public class BOJ_9207_페그_솔리테어 {
	static char[][] map;
	static final int R = 5, C = 9;
	static int min_cnt, min_move;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			map = new char[R][C];
			int pins = 0;
			
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
				
					if (map[i][j] == 'o') {
						pins++;
					}
				}
			}
			
			if (tc != TC-1) br.readLine();
			
			min_cnt = pins;
			min_move = 0;
			
			dfs(pins, 0);
			
			sb.append(min_cnt).append(" ").append(min_move).append("\n");
		}
		System.out.println(sb.toString());
	
	}
	
	private static void dfs(int cnt, int move) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != 'o') continue;// 핀이 아닌 경우 pass
				
				for (int d = 0; d < dirs.length; d++) {
					if (!isMove(r, c, d)) {
						check_min(cnt, move);
					} else {
						move(r, c, d);
						dfs(cnt-1, move+1);
						remove(r, c, d);
					}
				}
			}
		}
	}
	
	private static void check_min(int cnt, int move) {
		if (min_cnt > cnt) {
			min_cnt = cnt;
			min_move = move;
		} else if (min_cnt == cnt) {
			min_move = Math.min(min_move, move);
		}
	}

	private static void remove(int r, int c, int d) {
		int nr = r + dirs[d][0];
		int nc = c + dirs[d][1];
		int nnr = nr + dirs[d][0];
		int nnc = nc + dirs[d][1];
		
		map[r][c] = 'o';
		map[nr][nc] = 'o';
		map[nnr][nnc] = '.';
	
	}

	private static void move(int r, int c, int d) {
		int nr = r + dirs[d][0];
		int nc = c + dirs[d][1];
		int nnr = nr + dirs[d][0];
		int nnc = nc + dirs[d][1];
		
		map[r][c] = '.';
		map[nr][nc] = '.';
		map[nnr][nnc] = 'o';
	}
	

	private static boolean isMove(int r, int c, int d) {
		int nr = r + dirs[d][0];
		int nc = c + dirs[d][1];
		int nnr = nr + dirs[d][0];
		int nnc = nc + dirs[d][1];
		
		if (!isIn(nr, nc) || !isIn(nnr, nnc)) return false;
		
		if (map[nr][nc] == 'o' && map[nnr][nnc] == '.') return true;
		
		return false;
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
