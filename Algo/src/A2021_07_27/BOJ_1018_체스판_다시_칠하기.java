package A2021_07_27;

import java.util.*;
import java.io.*;

/**
 * Brute force
 * 20MIN
 * @author beaverbae
 *
 */


public class BOJ_1018_체스판_다시_칠하기 {
	static int R, C;
	static char[][] board;
	static int ans = 65;
	static char[][][] ans_board = {{{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}},
								   {{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
									{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
									{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}}};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				board[r][c] = str.charAt(c);
			}
		}
		
		for (int r = 0; r <= R - 8; r++) {
			for (int c = 0; c <= C - 8; c++) {
				calcMinChange(r, c);
			}
		}
		
		System.out.println(ans);
	}


	private static void calcMinChange(int sr, int sc) {
		for (int i = 0; i < ans_board.length; i++) {
			int temp = 0;
			
			for (int r = sr; r < sr + 8; r++) {
				for (int c = sc; c < sc + 8; c++) {
					if (board[r][c] != ans_board[i][r-sr][c-sc]) temp++;
				}
			}
			ans = Math.min(ans, temp);
		}
	}
}
