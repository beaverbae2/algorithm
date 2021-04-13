package A2021_04_12;

import java.util.*;
import java.io.*;

/**
 * Greedy
 * 78MIN
 * @author beaverbae
 *
 */

public class BOJ_7682_틱택토 {
	static int X, O;
	static char[][] map;
	static final int N = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		while (true) {
			String src = br.readLine();
			if (src.equals("end"))
				break;

			map = new char[N][N];
			O = 0;
			X = 0;
			for (int i = 0; i < src.length(); i++) {
				char ch = src.charAt(i);
				map[i / N][i % N] = ch;

				if (ch == 'O')
					O++;
				else if (ch == 'X')
					X++;
			}

			if (X == O + 1 || X == O) {// 유효성 검사 필요
				if (isValid()) {
					sb.append("valid").append("\n");
				} else {
					sb.append("invalid").append("\n");
				}
			} else {
				sb.append("invalid").append("\n");
			}
		}

		System.out.println(sb.toString());
	}

	/**
	 * 가로 세로 대각선 검사 
	 * @return true : valid, false : invalid
	 */
	private static boolean isValid() {
		// 가로
		int cnt = 0;// 개수
		char ch = 'a';// 격자내의 값
		boolean O_flag = false;// O가 valid 조건에 부합하는지 
		boolean X_flag = false;// X가 valid 조건에 부합하는지

		for (int r = 0; r < N; r++) {
			cnt = 1;
			ch = map[r][0];

			if (ch == '.')
				continue;

			for (int c = 1; c < N; c++) {
				if (map[r][c] == ch) {
					cnt++;
				} else {
					break;
				}
			}

			if (cnt == 3) {
				if (ch == 'O') {
					O_flag = true;
				} else if (ch == 'X') {
					X_flag = true;
				}
			}
		}
		
		// 세로
		for (int c = 0; c < N; c++) {
			cnt = 1;
			ch = map[0][c];

			if (ch == '.')
				continue;

			for (int r = 1; r < N; r++) {
				if (map[r][c] == ch) {
					cnt++;
				} else {
					break;
				}
			}

			if (cnt == 3) {
				if (ch == 'O') {
					O_flag = true;
				} else if (ch == 'X') {
					X_flag = true;
				}
			}
		}

		// 대각선
		ch = map[0][0];
		if (ch == map[1][1] && map[1][1] == map[2][2]) {
			if (ch == 'O') {
				O_flag = true;
			} else if (ch == 'X') {
				X_flag = true;
			}
		}

		ch = map[0][2];
		if (ch == map[1][1] && map[1][1] == map[2][0]) {
			if (ch == 'O') {
				O_flag = true;
			} else if (ch == 'X') {
				X_flag = true;
			}
		}

		if (X_flag && O_flag) // 둘다 부합할 순 없음
			return false;
		else if (O_flag) {// O가 부합하려면 X의 개수와 O의 개수가 동일
			if (X == O)
				return true;
			return false;
		} else if (X_flag){// X가 부합하려면 X의 개수와 (O의 개수+1) 이 동일
			if (X == O + 1)
				return true;
			return false;
		} else {// 둘 다 부합하지 않는 경우 격자를 꽉 채운 경우만 valid
			if (X + O == 9) return true;
			return false;
		}
	}
}
