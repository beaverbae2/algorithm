package A2022_02_09;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 74MIN
 * @author beaverbae
 * - 다음 칸이 빈칸 일때 뱀의 꼬리가 사라져야하는데 앞을 사라지게 했음
 *
 */

public class BOJ_3190_뱀 {
	static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};// 동남서북
	static int[][] board;// 0: 빈칸, 1: 뱀, 2: 사과
	static HashMap<Integer, Character> rotationMap;
	static int N, K, L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		rotationMap = new HashMap<>();
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			board[r][c] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			rotationMap.put(t, d);
		}
		
		System.out.println(clac());
	}
	
	private static int clac() {
		int t = 1;
		
		Deque<Pair> dq = new ArrayDeque<>();
		dq.addFirst(new Pair(0, 0));
		board[0][0] = 1;
		int d = 0;// 동
		
		while (true) {
			// 다음칸 
			Pair f = dq.peekFirst();
			int nr = f.r + dirs[d][0];
			int nc = f.c + dirs[d][1];
			
			if (isOver(nr, nc)) break;
			
			if (!isApple(nr, nc)) {
				Pair b = dq.pollLast();// 꼬리 삭제
				board[b.r][b.c] = 0;
			} 
			
			dq.addFirst(new Pair(nr, nc));// 머리 한칸 이동
			board[nr][nc] = 1;
			
			d = rotate(d, t);
			t++;
		}
		
		return t;
	}
	
	private static boolean isOver(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1;
	}
	
	private static boolean isApple(int nr, int nc) {
		return board[nr][nc] == 2;
	}
	
	private static int rotate(int d, int t) {
		if (rotationMap.containsKey(t)) { 
			char c = rotationMap.get(t);
			
			if (c == 'D') d++;
			else d--;
		}
		
		if (d < 0) d = 3;
		if (d > 3) d = 0;
		
		return d;
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}

}

