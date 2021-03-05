package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 71MIN
 * @author beaverbae
 *
 */

public class BOJ_16988_바둑_easy {
	static int[][] map;
	static int R, C;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[][] visited2;
	static boolean[][] visited0;
	static ArrayList<Pair> blanks;
	static int[] selected_blank;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		visited2 = new boolean[R][C];
		visited0 = new boolean[R][C];
		blanks = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 2 && !visited2[i][j]) {
					bfs(i,j);
				}
			}
		}
		
		selected_blank = new int[2];
		combination(0,0);
		System.out.println(answer);
	}
	
	
	private static void combination(int start, int cnt) {
		if (cnt == 2) {
			lay();
			return;
		}
		
		for (int i = start; i < blanks.size(); i++) {
			selected_blank[cnt] = i;
			combination(i+1, cnt+1);
		}
	}


	private static void lay() {
		for (int i = 0; i < selected_blank.length; i++) {
			int idx = selected_blank[i];
			Pair p = blanks.get(idx);
			map[p.r][p.c] = 1;
		}
		
		int cnt = 0;
		visited2 = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 2 && !visited2[i][j]) {
					cnt += bfs2(i,j);
				}
			}
		}
		
		answer = Math.max(answer, cnt);
		
		for (int i = 0; i < selected_blank.length; i++) {
			int idx = selected_blank[i];
			Pair p = blanks.get(idx);
			map[p.r][p.c] = 0;
		}
	}


	private static int bfs2(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(new Pair(i, j));
		visited2[i][j] = true;
		
		int cnt = 1;
		boolean blank_flag = false;
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isInMap(nr, nc)) {
					if (map[nr][nc] == 0) {
						blank_flag = true;
					} else if (map[nr][nc] == 2) {
						if (!visited2[nr][nc]) {
							visited2[nr][nc] = true;
							q.offer(new Pair(nr, nc));
							cnt++;
						}
					}
				}
			}
		}
		
		if (!blank_flag) {
			return cnt;
		}
		
		return 0;
	}


	private static void bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(new Pair(i, j));
		visited2[i][j] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isInMap(nr, nc)) {
					if (map[nr][nc] == 0) {
						if (!visited0[nr][nc]) {
							visited0[nr][nc] = true;
							blanks.add(new Pair(nr, nc));
						}
					} else if (map[nr][nc] == 2) {
						if (!visited2[nr][nc]) {
							visited2[nr][nc] = true;
							q.offer(new Pair(nr, nc));
						}
					}
				}
			}
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
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
