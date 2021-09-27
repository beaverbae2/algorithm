package A2021_02_08;

import java.util.*;
import java.io.*;
/**
 * BFS
 * 
 * @author beaverbae
 *
 */
public class BOJ_6087_레이저_통신 {
	static char[][] map;
	static int W, H;
	static final int D = 4;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		
		int sr = -1, sc = -1, er = -1, ec = -1;
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'C') {
					if (sr == -1 && sc == -1) {
						sr = i;
						sc = j;
					}else {
						er = i;
						ec = j;
					}
				}
			}
		}
	
		System.out.println(bfs(sr, sc, er, ec));
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		Queue<Node> q = new LinkedList<>();
		int[][][] visited = new int[D][H][W];
		final int INF = 987654321;
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				Arrays.fill(visited[i][j], INF);
			}
		}
		
		q.offer(new Node(sr, sc, -1, 0));
		for (int d = 0; d < dirs.length; d++) {
			visited[d][sr][sc] = 0;
		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int dir = node.dir;
			int cnt = node.cnt;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isInMap(nr, nc)) {
					if (isTurn(dir, d)) {
						if (map[nr][nc] != '*' && visited[d][nr][nc] > cnt + 1) {
							visited[d][nr][nc] = cnt + 1;
							q.offer(new Node(nr, nc, d, cnt + 1));
						}
					} else {
						if (map[nr][nc] != '*' && visited[d][nr][nc] > cnt) {
							visited[d][nr][nc] = cnt;
							q.offer(new Node(nr, nc, d, cnt));
						}
					}
				}
			}
		}
		
		int answer = INF;
		for (int d = 0; d < dirs.length; d++) {
			answer = Math.min(answer, visited[d][er][ec]);
		}
		
		return answer;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < H && nc >= 0 && nc < W;
	}
	
	private static boolean isTurn(int dir, int next_dir) {
		if (dir == -1) {
			return false;
		} else if (dir == 0) {
			if (next_dir == 2 || next_dir == 3) return true;
		}else if (dir == 1) {
			if (next_dir == 2 || next_dir == 3) return true;
		}else if (dir == 2) {
			if (next_dir == 0 || next_dir == 1) return true;
		}else if (dir == 3) {
			if (next_dir == 0 || next_dir == 1) return true;
		}
		
		return false;
	}
	
	
	static class Node {
		int r, c, dir, cnt;

		public Node(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", dir=" + dir + ", cnt=" + cnt + "]";
		}
	}
}
