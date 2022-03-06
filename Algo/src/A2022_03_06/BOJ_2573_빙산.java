package A2022_03_06;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 35MIN
 * @author beaverbae
 *
 */

public class BOJ_2573_빙산 {
	static int R, C, remain;
	static int[][] board;
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
				if (board[r][c] > 0) remain++;
			}
		}
		
		System.out.println(exec());
	}
	
	private static int exec() {
		int time = 0;
		
		while (!isSeperated()) {
			meltIce();
			if (remain == 0) break;
			time++;
		}
		
		if (remain == 0) return 0;
		return time;
	}
	
	private static boolean isSeperated() {
		boolean[][] visited = new boolean[R][C];
		int cnt = 0;
		
		for (int r = 1; r < R-1; r++) {
			for (int c = 1; c < C-1; c++) {
				if (board[r][c] == 0 || visited[r][c]) continue;
				
				cnt++;
				if (cnt > 1) return true; 

				bfs(r, c, visited);
			}
		}
		
		return false;
	}
	
	private static void bfs(int sr, int sc, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (board[nr][nc] == 0 || visited[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
	
	
	private static void meltIce() {
		int[][] meltBoard  = new int[R][C];
		
		for (int r = 1; r < R-1; r++) {
			for (int c = 1; c < C-1; c++) {
				if (board[r][c] == 0) continue;
				
				int cnt = 0;
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (board[nr][nc] != 0) continue;
					
					cnt++;
				}
				
				meltBoard[r][c] = cnt;
			}
		}
		
		for (int r = 1; r < R-1; r++) {
			for (int c = 1; c < C-1; c++) {
				if (board[r][c] == 0) continue;
				
				board[r][c] -= meltBoard[r][c];
				if (board[r][c] <= 0) {
					remain--;
					board[r][c] = 0;
				}
			}
		}
	}
	
	private static void print() {
		for (int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
}
