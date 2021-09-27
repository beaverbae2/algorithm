package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 31MIN
 * 오래 걸린 이유
 * - 물이 먼저 이동하고 고슴도치가 이동하는 흐름
 * - 초 단위 별로 이동을 나눠서 진행
 * @author beaverbae
 *
 */

public class BOJ_3055_탈출 {
	static int R, C;
	static char[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static ArrayList<Node> init_water;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		init_water = new ArrayList<>();
		int sr = 0, sc = 0, er = 0, ec = 0;
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			
				if (map[i][j] == 'S') {
					map[i][j] = '.';
					sr = i;
					sc = j;
				} else if (map[i][j] == 'D') {
					er = i;
					ec = j;
				} else if (map[i][j] == '*') {
					init_water.add(new Node(i, j, 0));
				}
			}
		}
		
		int result = bfs(sr, sc, er, ec);
		if (result == -1) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	
	private static int bfs(int sr, int sc, int er, int ec) {
		int result = -1;
		Queue<Node> water = new LinkedList<>();
		boolean[][] isWater = new boolean[R][C];
		
		for (Node w : init_water) {
			water.offer(new Node(w.r, w.c, w.w));
			isWater[w.r][w.c] = true;
		}
		init_water.clear();
		
		Queue<Node> hedgehog = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		hedgehog.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;
		
		int time = 0;
		boolean isPassed = false;
		while (true) {
			while (!water.isEmpty()) {
				int w = water.peek().w;
				if (w > time) break;
				
				Node cur = water.poll();
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = cur.r + dirs[d][0];
					int nc = cur.c + dirs[d][1];
				
					if (isIn(nr, nc) && !isWater[nr][nc] && map[nr][nc] == '.') {
						isWater[nr][nc] = true;
						water.offer(new Node(nr, nc, w+1));
					}
				}
			}
			
			while (!hedgehog.isEmpty()) {
				int w = hedgehog.peek().w;
				if (w > time) break;
				
				Node cur = hedgehog.poll();
				
				if (cur.r == er && cur.c == ec) {
					isPassed = true;
					result = w;
					break;
				}
			
				for (int d = 0; d < dirs.length; d++) {
					int nr = cur.r + dirs[d][0];
					int nc = cur.c + dirs[d][1];
				
					if (isIn(nr, nc) && !isWater[nr][nc] && !visited[nr][nc]) {
						if (map[nr][nc] == '.' || map[nr][nc] == 'D') {
							visited[nr][nc] = true;
							hedgehog.offer(new Node(nr, nc, w+1));
						}
					}
				}
			}
			if (isPassed) break;
			else if (hedgehog.isEmpty()) break;
			
			time++;
		}
		
		return result;
	}


	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	static class Node {
		int r, c, w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + "]";
		}
	}
}
