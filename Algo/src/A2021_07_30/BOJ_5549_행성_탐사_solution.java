package A2021_07_30;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 
 * 효율적으로 나누는 것이 어려웠다..
 * @author beaverbae
 * @see https://m.blog.naver.com/fhskf94kr/221490728035
 *
 */

public class BOJ_5549_행성_탐사_solution {
	static int R, C, K;
	static Node[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new Node[R+1][C+1];
		for (int i = 0; i <= R; i++) {
			board[i][0] = new Node(0, 0, 0);
		}
		
		for (int i = 0; i <= C; i++) {
			board[0][i] = new Node(0, 0, 0);
		}
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				char ch = str.charAt(j-1);

				int jungle = board[i-1][j].jungle + board[i][j-1].jungle - board[i-1][j-1].jungle;
				int ocean = board[i-1][j].ocean + board[i][j-1].ocean - board[i-1][j-1].ocean;
				int ice = board[i-1][j].ice + board[i][j-1].ice - board[i-1][j-1].ice;
				
				if (ch == 'J') jungle++;
				else if (ch == 'O') ocean++;
				else ice++;
				
				board[i][j] = new Node(ice, jungle, ocean);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
		
			int jungle = board[r2][c2].jungle - board[r2][c1-1].jungle - board[r1-1][c2].jungle + board[r1-1][c1-1].jungle;
			int ocean = board[r2][c2].ocean - board[r2][c1-1].ocean - board[r1-1][c2].ocean + board[r1-1][c1-1].ocean;
			int ice = board[r2][c2].ice - board[r2][c1-1].ice - board[r1-1][c2].ice + board[r1-1][c1-1].ice;
		
			sb.append(jungle).append(" ").append(ocean).append(" ").append(ice).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static class Node {
		int ice, jungle, ocean;

		public Node(int ice, int jungle, int ocean) {
			this.ice = ice;
			this.jungle = jungle;
			this.ocean = ocean;
		}

		@Override
		public String toString() {
			return "[ice=" + ice + ", jungle=" + jungle + ", ocean=" + ocean + "]";
		}
	}
}
