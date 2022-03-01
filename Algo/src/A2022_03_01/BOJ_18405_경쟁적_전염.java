package A2022_03_01;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 27MIN
 * @author beaverbae
 *
 */

public class BOJ_18405_경쟁적_전염 {
	static int N, K, S, X, Y;
	static int[][] board;
	static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		pq = new PriorityQueue<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				
				if (board[r][c] != 0) {
					pq.add(new Node(board[r][c], r, c, 0));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int id = node.id;
			int r = node.r;
			int c = node.c;
			int t = node.t;
			
			if (t == S) break;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] != 0) continue;
				
				pq.add(new Node(id, nr, nc, t+1));
				board[nr][nc] = id;
			}
		}
		
		return board[X][Y];
	}
	
	static class Node implements Comparable<Node>{
		int id, r, c, t;

		public Node(int id, int r, int c, int t) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Node [id=" + id + ", r=" + r + ", c=" + c + ", t=" + t + "]";
		}
		
		@Override
		public int compareTo(Node o) {
			if (this.t != o.t) return this.t - o.t;
			return this.id - o.id;
		}
	}
}
