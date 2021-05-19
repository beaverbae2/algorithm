package A2021_05_19;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 9MIN
 * @author beaverbae
 *
 */

public class BOJ_16174_점프왕_쩰리_Large {
	static int[][] map;
	static int N;
	static int[][] dirs = {{0,1}, {1,0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map =  new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}

	private static String bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Node(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			if (r == N-1 && c == N-1) return "HaruHaru";
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0] * map[r][c];
				int nc = c + dirs[d][1] * map[r][c];
				
				if (!isIn(nr, nc) || visited[nr][nc]) continue;
				
				q.offer(new Node(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		return "Hing";
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
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
