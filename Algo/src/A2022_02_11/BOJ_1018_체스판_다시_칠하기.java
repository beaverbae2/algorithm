package A2022_02_11;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 30MIN
 * @author beaverbae
 * - 문제 해석 잘못함
 * - 인덱싱 꼼꼼하게 할것
 *
 */

public class BOJ_1018_체스판_다시_칠하기 {
	static int R, C;
	static final int N = 8;
	static char[][] board;
	static char[][] arr1 = {{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'}};
	static char[][] arr2 = {{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'},
							{'W','B','W','B','W','B','W','B'},
							{'B','W','B','W','B','W','B','W'}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			String s = br.readLine();
			for (int c = 0; c < C; c++) {
				board[r][c] = s.charAt(c);
			}
		}
		
		System.out.println(execute());
	}
	
	private static int execute() {
		int result = R * C;
		
		for (int i = 0; i <= R - N; i++) {
			for (int j = 0; j <= C - N; j++) {
				int temp1 = 0;
				int temp2 = 0;
				
 				for (int r = i; r < i + N; r++) {
					for (int c = j; c < j + N; c++) {
						if (board[r][c] != arr1[r-i][c-j]) temp1++;
						if (board[r][c] != arr2[r-i][c-j]) temp2++;
					}
				}
				
 				result = Math.min(result, Math.min(temp1, temp2));
			}
		}
		
		return result;
	}
}
