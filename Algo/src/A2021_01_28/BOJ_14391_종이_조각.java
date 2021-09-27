package A2021_01_28;

import java.util.*;
import java.io.*;

/**
 * Brute-force
 * 
 * @author beaverbae
 * @see https://herong.tistory.com/entry/BOJ-14391-%EC%A2%85%EC%9D%B4-%EC%A1%B0%EA%B0%81-Java
 */

public class BOJ_14391_종이_조각 {
	static int R, C;
	static int[][] map;
	static int[][] board;
	static int[][] dirs = { { 1, 0 }, { 0, 1 } };
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		board = new int[R][C];
		answer = 0;

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		pick(0);
		System.out.println(answer);
	}

	private static void pick(int r) {
		if (r == R * C) {
			answer = Math.max(answer, getSum());
			return;
		}

		int i = r / C;
		int j = r % C;

		board[i][j] = 1;// 가로
		pick(r + 1);

		board[i][j] = 2;// 세로
		pick(r + 1);
	}

	private static int getSum() {
		int result = 0;

		// 가로
		for (int r = 0; r < R; r++) {
			int temp = 0;
			for (int c = 0; c < C; c++) {
				if (board[r][c] == 1) {
					temp = temp * 10 + map[r][c];
				}else {
					result += temp;
					temp = 0;
				}
			}
			result += temp;
		}

		// 세로
		for (int c = 0; c < C; c++) {
			int temp = 0;
			for (int r = 0; r < R; r++) {
				if (board[r][c] == 2) {
					temp = temp * 10 + map[r][c];
				}else {
					result += temp;
					temp = 0;
				}
			}
			result += temp;
		}

		return result;
	}
}