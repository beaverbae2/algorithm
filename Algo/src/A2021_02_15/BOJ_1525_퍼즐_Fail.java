package A2021_02_15;

import java.util.*;
import java.io.*;

public class BOJ_1525_퍼즐_Fail {
	static HashMap<Integer, int[][]> map;
	static int answer;
	static int[][] answer_board = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[3][3];
		map = new HashMap<>();
		
		int zero_r = -1, zero_c = -1;
		
		for (int i = 0; i < board.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			
				if (board[i][j] == 0) {
					zero_r = i;
					zero_c = j;
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		map.put(-1, board);
		dfs(0, zero_r, zero_c, board);
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else System.out.println(answer);
	}

	private static void dfs(int cnt, int r, int c, int[][] board) {
		System.out.println("cnt : "+cnt);
		if (isSame(board, answer_board)) {
			answer = Math.min(answer, cnt+1);
			return;
		}
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if (nr<0 || nr>=3 || nc<0 || nc>=3) continue;
			
			int[][] next_board = new int[3][3];
			getNext(board, next_board, r, c, nr, nc);
			map.put(cnt, next_board);
			if (!isSameBefore(cnt)) {
				dfs(cnt+1, nr, nc, next_board);
			}
			map.remove(cnt);
		}
		
	}

	private static void getNext(int[][] board, int[][] next_board, int r, int c, int nr, int nc) {
		for (int i = 0; i < next_board.length; i++) {
			for (int j = 0; j < next_board[i].length; j++) {
				next_board[i][j] = board[i][j];
			}
		}
		
		int temp = next_board[r][c];
		next_board[r][c] = next_board[nr][nc];
		next_board[nr][nc] = temp;
	}

	private static boolean isSameBefore(int cnt) {
		int[][] arr1 = map.get(cnt);
//		if (arr1 !=null) {
			for (int i = 0; i < arr1.length; i++) {
				System.out.println(Arrays.toString(arr1[i]));
			}
			System.out.println();
//		}
		
		for (int n = cnt-1; n >= -1; n--) {
			int[][] arr2 = map.get(n);
			
			for (int i = 0; i < arr2.length; i++) {
				System.out.println(Arrays.toString(arr2[i]));
			}
			System.out.println();
			
			if (isSame(arr1, arr2)) return true;
		}
		
		return false;
	}

	private static boolean isSame(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				if (arr1[i][j] != arr2[i][j]) return false; 
			}
		}
		
		return true;
	}
	
	
}
