package A2021_06_16;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 38MIN
 * 놓쳤던 부분
 * - 그람이 있는 경우 시간이 T초과 : 그람 찾고, (현재 시간 + |그람위치-공주위치|)를 리턴, 이 값이 T를 초과하는 경우를 놓침 
 * 
 * @author beaverbae
 *
 */

public class BOJ_17836_공주님을_구해라_ver1 {
	static int N, M, T;
	static int[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static final int INF = 10001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		int answer = Math.min(bfs1(), bfs2());
		if (answer == INF) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
	}
	
	private static int bfs2() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		q.offer(new Node(0, 0, 0));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.t > T) {
				break;
			}
			
			if (map[cur.r][cur.c] == 2) {
				int result = cur.t;
				result += Math.abs(N-1-cur.r) + Math.abs(M-1-cur.c);
				if (result > T) {
					break;
				} else {
					return result;
				}
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = cur.r + dirs[d][0];
				int nc = cur.c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				
				q.offer(new Node(nr, nc, cur.t + 1));
				visited[nr][nc] = true;
			}
		}
		
		return INF;
	}

	private static int bfs1() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		q.offer(new Node(0, 0, 0));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.t > T) {
				break;
			}
			
			if (cur.r == N-1 && cur.c == M-1) {
				return cur.t;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = cur.r + dirs[d][0];
				int nc = cur.c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc]) {
					continue;
				}
				
				if (map[nr][nc] == 0) {
					q.offer(new Node(nr, nc, cur.t + 1));
					visited[nr][nc] = true;
				}
			}
			
		}
		
		return INF;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}

	static class Node {
		int r, c, t;
		
		public Node(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", t=" + t + "]";
		}
	}
}
