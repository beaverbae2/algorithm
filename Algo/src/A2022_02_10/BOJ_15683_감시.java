package A2022_02_10;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 64MIN
 * @author beaverbae
 * 
 * 
 */

public class BOJ_15683_감시 {
	static int R, C;
	static int[][] board;
	static List<Pair> cctvs;
	static int ans;
	static int[][][][] dirs = {
							{},
							{{{0,1}}, {{-1,0}}, {{0,-1}}, {{1,0}}},
							{{{0, -1}, {0, 1}}, {{-1, 0}, {1, 0}}},
							{{{-1, 0}, {0, 1}},{{0, 1}, {1, 0}},{{1, 0}, {0, -1}},{{0, -1}, {-1, 0}}},
							{{{0, -1}, {-1, 0}, {0, 1}}, {{-1, 0}, {0, 1}, {1, 0}}, {{0, 1}, {1, 0}, {0, -1}}, {{1, 0}, {0, -1}, {-1, 0}}},
							{{{0, -1}, {-1, 0}, {0, 1}, {1, 0}}}
							};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] visited = new int[R][C];
		board = new int[R][C];
		ans = R * C;
		cctvs = new ArrayList<>();
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] >= 1 && board[r][c] <= 5) cctvs.add(new Pair(r, c));
			}
		}
		
		dfs(visited, 0);
		System.out.println(ans);
	}
	
	private static void dfs(int[][] visited, int idx) {
		if (idx == cctvs.size()) {
			ans = Math.min(ans, count(visited));
			return;
		}
		
		Pair cur = cctvs.get(idx);
		int type = board[cur.r][cur.c];
		for (int d = 0; d < dirs[type].length; d++) {
			Queue<Pair> q = new LinkedList<>();
			for (int i = 0; i < dirs[type][d].length; i++) {
				int cnt = 1;
				while (true) {
					int nr = cur.r + cnt * dirs[type][d][i][0];
					int nc = cur.c + cnt * dirs[type][d][i][1];
					
					if (isWall(nr, nc)) break;
					
					if (board[nr][nc] == 0) {
						visited[nr][nc]++;
						q.offer(new Pair(nr, nc));
					}
					cnt++;
				}
			}
			dfs(visited, idx + 1);
			
			while (!q.isEmpty()) {
				Pair p = q.poll();
				visited[p.r][p.c]--;
			}
		}
	}
	
	private static int count(int[][] visited) {
		int result = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (visited[r][c] == 0 && board[r][c] == 0) result++;
			}
		}
		
		return result;
	}

	private static boolean isWall(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C || board[nr][nc] == 6;
	}
	
	private static void print(int[][] board) {
		for (int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
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
