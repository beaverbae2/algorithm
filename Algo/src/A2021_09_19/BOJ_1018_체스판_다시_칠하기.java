package A2021_09_19;

import java.util.*;
import java.io.*;

/**
 * Brute force
 * 16MIN
 * @author beaverbae
 * 실수한 부분
 * - 인덱싱 -> 배열 범위 초과
 * 
 */

public class BOJ_1018_체스판_다시_칠하기 {
	static int N, M, ans;
	static char[][] board;
	static char[][][] ansBoard = {{{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'}},
								 {{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'}}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		for (int sr = 0; sr < N; sr++) {
			for (int sc = 0; sc < M; sc++) {
				int er = sr + 8;
				int ec = sc + 8;
			
				if (er > N || ec > M) continue;// 범위 초과
				
				ans = Math.min(getChangeCount(sr, sc), ans);
			}
		}
		
		System.out.println(ans);
	}

	private static int getChangeCount(int sr, int sc) {
		int result = ans;
		for (int k = 0; k <= 1; k++) {
			int temp = 0;
			for (int i = sr; i < sr + 8; i++) {
				for (int j = sc; j < sc + 8; j++) {
					if (ansBoard[k][i-sr][j-sc] != board[i][j]) temp++;
				}
			}
			result = Math.min(temp, result);
		}
		
		return result;
	}
}
