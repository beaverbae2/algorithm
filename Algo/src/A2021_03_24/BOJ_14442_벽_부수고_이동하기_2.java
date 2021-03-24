package A2021_03_24;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 26MIN
 * 오래 걸린 이유 
 * 벽or빈칸에서 각각 이동하는 경우의 수 나누기
 * k+1 일 떄 visited[nr][nc][k] 를 방문체크 했음....
 * @author beaverbae
 *
 */

public class BOJ_14442_벽_부수고_이동하기_2 {
	static int[][] map;
	static int R, C, K;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][K+1];
		
		q.offer(new Node(0, 0, 0, 1));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int k = node.k;
			int depth = node.depth;
			
			if (r == R-1 && c == C-1) {
				return depth;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) continue;
				
				if (map[nr][nc] == 0) {
					if (visited[nr][nc][k]) continue;
					
					visited[nr][nc][k] = true;
					q.offer(new Node(nr, nc, k, depth+1));
				} else {
					if (k == K || visited[nr][nc][k+1]) continue;
					
					visited[nr][nc][k+1] = true;
					q.offer(new Node(nr, nc, k+1, depth+1));
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
	
	static class Node {
		int r, c, k, depth;

		public Node(int r, int c, int k, int depth) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", k=" + k + ", depth=" + depth + "]";
		}
	}
}	
