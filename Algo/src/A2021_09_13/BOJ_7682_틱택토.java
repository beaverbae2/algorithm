package A2021_09_13;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 90MIN
 * @author beaverbae
 * 해결에 사용한 반례
 * XXXXXOOOO
 * end
 * -> 중간에 끝나야함
 */

public class BOJ_7682_틱택토 {
	static char[][] board;
	static final int N = 3;
	static int O, X;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			String str = br.readLine();
			if (str.equals("end")) break;
			
			O = 0;
			X = 0;
			board = new char[N][N];
			for (int i = 0; i < str.length(); i++) {
				int x = i / N;
				int y = i % N;
				board[x][y] = str.charAt(i);
				if (board[x][y] == 'O') O++;
				else if (board[x][y] == 'X') X++;
			}
			
			if (check()) {
				sb.append("valid");
			} else {
				sb.append("invalid");
			} 
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean check() {
		boolean Xwin = isWin('X');
		boolean Owin = isWin('O');
		
		if (Xwin) {
			if (Owin) return false;
			else if (X == O+1) return true;
		} else {
			if (!Owin) return X == 5 && O == 4;
			else if (X == O) return true;
		}
		
		return false;
	}
	
	private static boolean isWin(char ch) {
		// 가로
		for (int x = 0; x < N; x++) {
			int cnt = 0;
			for (int y = 0; y < N; y++) {
				if (board[x][y] == ch) cnt++;
			}
			if (cnt == 3) return true;
		}	
		// 세로
		for (int y = 0; y < N; y++) {
			int cnt = 0;
			for (int x = 0; x < N; x++) {
				if (board[x][y] == ch) cnt++;
			}
			if (cnt == 3) return true;
		}
		//대각선
		if (board[0][0] == ch && board[1][1] == ch && board[2][2] == ch || board[2][0] == ch && board[1][1] == ch && board[0][2] == ch) return true;
		
		return false;
	}
}
