package A2022_03_06;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 40MIN 
 * @author beaverbae
 *
 */

public class BOJ_2638_치즈 {
	static int R, C, cnt;
	static int[][] board;
	static boolean[][] isOuterAir;
	static boolean[][] visited;
	final static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] == 1) cnt++;
			}
		}
	
		System.out.println(exec());
	}
	
	private static int exec() {
		int time = 0;
		
		while(cnt > 0) {
			calcOuterAir();
			meltCheese();
			time++;
		}
		
		return time;
	}
	
	private static void calcOuterAir() {
		isOuterAir = new boolean[R][C];
		visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (visited[r][c] || board[r][c] == 1) continue;
				
				findOuterAir(r, c);
			}
		}
	}
	
	private static void meltCheese() {
		boolean[][] isMelt = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] != 1) continue;
				
				int outerAirCnt = 0;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				
					if (isOuterAir[nr][nc]) outerAirCnt++;
				}
				
				if (outerAirCnt >= 2) isMelt[r][c] = true;
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!isMelt[r][c]) continue;
				
				board[r][c] = 0;
				cnt--;
			}
		}
	}
	
	private static void findOuterAir(int sr, int sc) {
		boolean isAllOuterAirs = false;
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> airs = new LinkedList<>();
		q.offer(new int[] {sr, sc});
		airs.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					isAllOuterAirs = true;
					continue;
				}
				
				if (board[nr][nc] == 1 || visited[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				airs.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
		if (isAllOuterAirs) {
			while (!airs.isEmpty()) {
				int[] pos = airs.poll();
				int r = pos[0];
				int c = pos[1];
				isOuterAir[r][c] = true;
			}
		}
	}
}
