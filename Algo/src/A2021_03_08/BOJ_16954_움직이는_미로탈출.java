package A2021_03_08;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 34MIN
 * @author beaverbae
 *
 */

public class BOJ_16954_움직이는_미로탈출 {
	static char[][] map;
	static int N = 8;
	static int wall_cnt;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
	static int t;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				
				if (ch == '#') wall_cnt++;
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		
		q.offer(new Node(N-1, 0, 0));
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int time = node.time;
			
			
			if (t < time) {
				fallWalls();
				t++;
			}
			
			if (map[r][c] == '#') continue; // 현재 위치가 벽이라면
			
			if (r == 0 && c == N-1) return 1;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isInMap(nr, nc) && map[nr][nc] == '.') {
					q.offer(new Node(nr, nc, time+1));
				}
			}
			
			if (wall_cnt != 0) {
				q.offer(new Node(r,c,time+1));
			}
		}
		return 0;
	}

	private static void fallWalls() {
		if (wall_cnt == 0) return; 
		
		for (int i = 0; i < N; i++) {
			if (map[N-1][i] == '#') wall_cnt--;
		}
		
		for (int c = 0; c < N; c++) {
			for (int r = N-2; r >= 0; r--) {
				map[r+1][c] = map[r][c];
			}
		}
		
		for (int c = 0; c < N; c++) {
			map[0][c] = '.';
		}
	}



	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
	
	static class Node {
		int r, c, time;

		public Node(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", time=" + time + "]";
		}
	}
}
