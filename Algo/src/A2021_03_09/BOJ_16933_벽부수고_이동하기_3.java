package A2021_03_09;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 35MIN
 * @author beaverbae
 *
 */

public class BOJ_16933_벽부수고_이동하기_3 {
	static int R, C, K;
	static boolean[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str.charAt(j);
				if (ch == '0') {
					map[i][j] = true;
				} 
			}
		}
		
		System.out.println(bfs());
		
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][K+1];
		
		q.offer(new Node(0, 0, 1, K));
		visited[0][0][K] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int depth = node.depth;
			int k = node.k;
			
			if (r == R-1 && c == C-1) return depth;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				// 낮 밤 공통 : 빈칸인 경우 이동 가능
				if (isInMap(nr, nc) && map[nr][nc] && !visited[nr][nc][k]) {
					visited[nr][nc][k] = true;
					q.offer(new Node(nr, nc, depth+1, k));
				}
				
				// 밤인 경우 가만히 있을 수 있다
				if (depth % 2 == 0) {
					q.offer(new Node(r, c, depth+1, k));
				} else {// 낮인 경우 벽을 부술 수 있다
					if (k == 0) continue;
					else if (isInMap(nr, nc) && !map[nr][nc] && !visited[nr][nc][k-1]) {
						visited[nr][nc][k-1] = true;
						q.offer(new Node(nr, nc, depth+1, k-1));
					}
				}
			}
			
		}
		
		return -1;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
	
	static class Node {
		int r, c, depth, k;

		public Node(int r, int c, int depth, int k) {
			this.r = r;
			this.c = c;
			this.depth = depth;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + ", k=" + k + "]";
		}
	}
}
