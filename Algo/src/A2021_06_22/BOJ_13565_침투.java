package A2021_06_22;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 16MIN
 * 문제 똑바로 보자....
 * 
 * @author beaverbae
 * 
 *
 */

public class BOJ_13565_침투 {
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int R, C;
	static boolean[][] map, visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new boolean[R][C];
		visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				char ch = str.charAt(c);
				if (ch == '0') {
					map[r][c] = true;
				}
			}
		}
		
		boolean ans = false;
		for (int c = 0; c < C; c++) {
			ans = bfs(0, c);
			
			if (ans) break;
		}
		
		if (ans) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
	}
	
	private static boolean bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			if (r == R-1) return true;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || !map[nr][nc] || visited[nr][nc]) continue;
				
				q.offer(new Node(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		return false;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
	}
}
