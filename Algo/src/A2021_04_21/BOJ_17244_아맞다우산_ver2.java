package A2021_04_21;

import java.util.*;
import java.io.*;

/**
 * Bitmasking
 * @author beave
 *
 */

public class BOJ_17244_아맞다우산_ver2 {
	static int R, C;
	static char[][] map;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static int key_idx;
	static char max_key;
	
	public static void main(String[] args) throws Exception {
		int sr = 0, sc = 0, er = 0, ec = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			
				if (map[r][c] == 'S') {
					sr = r;
					sc = c;
				} else if (map[r][c] == 'E') {
					er = r;
					ec = c;
				} else if (map[r][c] == 'X') {
					map[r][c] = (char) ('0'+key_idx++); 
				}
			}
		}
		
		max_key = (char) ('0'+key_idx);
		System.out.println(bfs(sr, sc, er, ec));
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		int result = 0;
		
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][1<<key_idx];
		q.offer(new Node(sr, sc, 0, 0));
		visited[sr][sc][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			int k = cur.k;
			
			if (r == er && c == ec && visited[r][c][(1<<key_idx)-1]) {
				result = w;
				break;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isIn(nr, nc)) {
					int ch = map[nr][nc];
					
					if (ch == '#') continue;
					
					if (ch>='0' && ch < max_key) {
						int next_k = k | (1 << (ch-'0'));
						
						if (!visited[nr][nc][next_k]) {
							visited[nr][nc][next_k] = true;
							q.offer(new Node(nr, nc, w+1, next_k));
						}
					} else { 
						if (!visited[nr][nc][k]) {
							visited[nr][nc][k] = true;
							q.offer(new Node(nr, nc, w+1, k));
						}
					}
				}
			}
		}
		
		return result;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	static class Node {
		int r, c, w, k;

		public Node(int r, int c, int w, int k) {
			this.r = r;
			this.c = c;
			this.w = w;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + ", k=" + k + "]";
		}
	}
}
