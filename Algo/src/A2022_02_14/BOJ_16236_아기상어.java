package A2022_02_14;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 50MIN
 * @author beaverbae
 * - pq로 해야되네...
 */

public class BOJ_16236_아기상어 {
	static int N, sR, sC, scale, cnt, time;
	static int[][] board;
	static final int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			
				if (board[r][c] == 9) {
					sR = r;
					sC = c;
					scale = 2;
					cnt = 0;
					board[r][c] = 0;
				}
			}
		}
		
		execute();
		System.out.println(time);
	}
	
	private static void execute() {
		while (bfs());
	}
	
	private static boolean bfs() {
		PriorityQueue<Shark> q = new PriorityQueue<>(); 
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Shark(sR, sC, 0));
		visited[sR][sC] = true;
		
		while (!q.isEmpty()) {
			Shark s = q.poll();
			int r = s.r;
			int c = s.c;
			int t = s.t;
			
			if (isSmallFish(r, c)) {
				eatFish(r, c);
				time += t;
				return true;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc] || isBiggerFish(nr, nc)) continue;
				
				q.offer(new Shark(nr, nc, t+1));
				visited[nr][nc] = true;
			}
		}
		
		return false;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	private static boolean isSmallFish(int r, int c) {
		return board[r][c] > 0 && board[r][c] < scale;
	}
	
	private static boolean isBiggerFish(int r, int c) {
		return board[r][c] > scale;
	}
	
	private static void eatFish(int r, int c) {
		sR = r;
		sC = c;
		board[r][c] = 0;
		cnt++;
		
		if (cnt == scale) {
			scale++;
			cnt = 0;
		}
	}
	
	static class Shark implements Comparable<Shark>{
		int r, c, t;

		public Shark(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", t=" + t + "]";
		}

		@Override
		public int compareTo(Shark o) {
		    if (t != o.t) return t - o.t;
			else if (r != o.r) return r - o.r;
			return c - o.c;
		}
	}
}