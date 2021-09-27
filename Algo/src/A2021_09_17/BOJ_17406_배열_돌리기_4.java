package A2021_09_17;

import java.util.*;
import java.io.*;

/**
 * 78MIN
 * Simulation
 * @author beaverbae
 * 어려웠던 점
 * - board의 현재상태를 저장하는 배열 prev_board가 dfs메소드 내의 지역변수로 선언되어야함
 * - rotate메소드는 매 단계마다 진행
 */

public class BOJ_17406_배열_돌리기_4 {
	static int ans = Integer.MAX_VALUE;
	static int R, C, K;// 1부터 시작 -1빼줘야함
	static int[][] board, next_board;
	static int[][] commands;
	static int[] orders;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		board = new int[R][C];
		commands = new int[K][3];
		orders = new int[K];
		visited = new boolean[K];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			commands[k][0] = Integer.parseInt(st.nextToken())-1;
			commands[k][1] = Integer.parseInt(st.nextToken())-1;
			commands[k][2] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(ans);
	}
	
	private static void dfs(int cnt) {
		if (cnt == K) {
			ans = Math.min(ans, getMinRowSum());
			return;
		}
		
		int[][] prev_board = new int[R][C];// 새롭게 정의를 해야되기 때문에 지역변수로 할당해야함
		deepcopy(board, prev_board);
		
		for (int i = 0; i < K; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			rotate(i);
			dfs(cnt+1);
			deepcopy(prev_board, board);
			visited[i] = false;
		}
	}

	private static int getMinRowSum() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			int temp = 0;
			for (int j = 0; j < C; j++) {
				temp += board[i][j];
			}
			result = Math.min(result, temp);
		}
		
		return result;
	}

	private static void rotate(int i) {
		next_board = new int[R][C];
		deepcopy(board, next_board);
		int r = commands[i][0];
		int c = commands[i][1];
		int s = commands[i][2];
		
		for (int a = s; a > 0; a--) {
			right(r, c, a);
			down(r, c, a);
			left(r, c, a);
			up(r, c, a);
		}
		
		deepcopy(next_board, board);
	}
	
	private static void deepcopy(int[][] src, int[][] dest) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}

	private static void right(int r, int c, int a) {
		for (int i = c-a; i < c+a; i++) {
			next_board[r-a][i+1] = board[r-a][i]; 
		}
	}
	
	private static void down(int r, int c, int a) {
		for (int i = r-a; i < r+a; i++) {
			next_board[i+1][c+a] = board[i][c+a]; 
		}
	}
	
	private static void left(int r, int c, int a) {
		for (int i = c+a; i > c-a; i--) {
			next_board[r+a][i-1] = board[r+a][i]; 
		}
	}
	
	private static void up(int r, int c, int a) {
		for (int i = r+a; i > r-a; i--) {
			next_board[i-1][c-a] = board[i][c-a]; 
		}
	}
	
}
