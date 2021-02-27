package A2021_02_27;

import java.util.*;
import java.io.*;

public class BOJ_3197_백조의_호수_시간초과 {
	static char[][] map;
	static int R, C;
	static int sr, sc, er, ec;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		sr = -1; sc = -1; er = -1; ec = -1;
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			
				if (map[i][j] == 'L') {
					if (sr == -1 && sc == -1) {
						sr = i;
						sc = j;
					} else {
						er = i;
						ec = j;
					}
					map[i][j] = '.';
				}
			}
		}
		int answer = 0;
		while (true) {
			if (bfs()) break;
			melt();
			answer++;
		}
		System.out.println(answer);
	}
	
	private static void melt() {
		char[][] next_map = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				next_map[i][j] = map[i][j];
			}
		}
		
		boolean[][] melting = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '.') continue;
				
				int cnt = 0;// 주변의 물 개수
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (isInMap(nr, nc) && map[nr][nc] == '.') {
						cnt++;
					}
				}
				
				if (cnt > 0) {
					melting[r][c] = true;
					next_map[r][c] = '.';
				}
			}
		}
		
		
		map = next_map;
	}

	private static boolean bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isInMap(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
					
					if (nr == er && nc == ec) return true;
				}
			}
			
		}
		
		
		return false;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
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
