package A2022_02_25;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 41MIN
 * @author beaverbae
 * - 빈칸을 모두 다 채우는데 걸리는 시간 -> 비활성 바이러스는 대상이 아님
 */

public class BOJ_17142_연구소3 {
	static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int M, R, C, ans, blanks;
	static int[][] board;
	static List<int[]> list;
	static int[] selected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		list = new ArrayList<>();
		selected = new int[M];
		ans = Integer.MAX_VALUE;
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] == 2) {
					list.add(new int[] {r, c});
				} else if (board[r][c] == 0) {
					blanks++;
				}
			}
		}
		
		dfs(0, 0);
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private static void dfs(int cnt, int start) {
		if (cnt == M) {
			ans = Math.min(ans, bfs());
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			selected[cnt] = i;
			dfs(cnt+1, i+1);
		}
	}
	
	private static int bfs() {
		int result = 0;
		int cnt = 0;
		
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		for (int idx : selected) {
			int r = list.get(idx)[0];
			int c = list.get(idx)[1];
			
			q.offer(new Pair(r, c, 0));
			visited[r][c] = true;
		}
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
			int t = p.t;
			
			if (cnt == blanks) break;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc] || board[nr][nc] == 1) continue;
				
				q.offer(new Pair(nr, nc, t+1));
				visited[nr][nc] = true;
				if (board[nr][nc] == 0) {
					cnt++;
					result = t + 1;
				}
			}
		}
		
		if (blanks == cnt) {
			return result;
		}
		return Integer.MAX_VALUE;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C; 
	}
	
	static class Pair {
		int r, c, t;

		public Pair(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + ", t=" + t + "]";
		}
	}
}
