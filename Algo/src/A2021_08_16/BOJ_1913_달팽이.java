package A2021_08_16;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 27MIN
 * 규칙 찾기!!
 * @author beaverbae
 *
 */

public class BOJ_1913_달팽이 {
	static int N, target;
	static int R, C, num;
	static int[][] board;
	static StringBuilder sb1, sb2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		target = Integer.parseInt(br.readLine());
		num = N * N;
		board = new int[N][N];
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
		
		int k = N-1;
		while (k >= 0) {
			clac(k);
			k -= 2;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb1.append(board[i][j]).append(" ");
			}
			if (i == N-1) continue;
			sb1.append("\n");
		}
		
		System.out.println(sb1);
		System.out.println(sb2);
	}

	private static void clac(int k) {
		if (k == 0) {
			board[R][C] = num;
			isTarget();
			return;
		}
		
		down(k);
		right(k);
		up(k);
		left(k);
		R++;
		C++;
	}
	
	private static void left(int k) {
		for (int i = 0; i < k; i++) {
			board[R][C] = num;
			isTarget();
			C--;
			num--;
		}
	}

	private static void up(int k) {
		for (int i = 0; i < k; i++) {
			board[R][C] = num;
			isTarget();
			R--;
			num--;
		}
	}

	private static void right(int k) {
		for (int i = 0; i < k; i++) {
			board[R][C] = num;
			isTarget();
			C++;
			num--;
		}
	}

	private static void down(int k) {
		for (int i = 0; i < k; i++) {
			board[R][C] = num;
			isTarget();
			R++;
			num--;
		}
	}

	private static void isTarget() {
		if (num == target) {
			sb2.append((R+1)).append(" ").append((C+1));
		}
	}
}
