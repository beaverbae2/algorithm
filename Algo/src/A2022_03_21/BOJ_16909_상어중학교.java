package A2022_03_21;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * @author beaverbae
 * - 무지개돌은 다른 돌그룹에 포함될 수 있으므로. 방문처리를 잘 해야함
 */

public class BOJ_16909_상어중학교 {
	static int[][] board;
	static boolean[][] visited;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(exec());
	}
	
	private static int exec() {
		int total = 0;
		
		while (true) {
			visited = new boolean[N][N];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c] || board[r][c] <= 0) continue;
					
					Node n = bfs(r, c);
					if (n == null) continue;
					pq.offer(n);
					visited = new boolean[N][N];// 무지개 돌은 모든 그룹에서 방문 가능하므로...
				}
			}
			
			if (pq.isEmpty()) break;
			
			Node n = pq.poll();
			
			total += n.cnt * n.cnt;
			visited = new boolean[N][N];
			
			dfs(n.sr, n.sc, board[n.sr][n.sc]);
			gravity();
			rotate();
			gravity();
		}
		
		return total;
	}
	
	private static Node bfs(int R, int C) {
		Queue<Pair> q = new LinkedList<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int cnt = 1, rcnt = 0;
		int color = board[R][C];
		
		q.offer(new Pair(R, C));
		pq.offer(new Pair(R, C));
		visited[R][C] = true;
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc] || board[nr][nc] != color && board[nr][nc] != 0) continue;
				
				cnt++;
				if (board[nr][nc] == 0) rcnt++;
				
				q.offer(new Pair(nr, nc));
				if (board[nr][nc] != 0) pq.offer(new Pair(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		if (cnt < 2) return null;
		Pair p = pq.poll();
		return new Node(cnt, rcnt, p.r, p.c);
	}
	
	private static void dfs(int r, int c, int color) {
		visited[r][c] = true;
		board[r][c] = -2;
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if (!isIn(nr, nc) || visited[nr][nc] || board[nr][nc] != color && board[nr][nc] != 0) continue;
			
			dfs(nr, nc, color);
		}
	}
	
	private static void gravity() {
		int[][] nextBoard = new int[N][N];
		for (int r = 0; r < N; r++) {
			Arrays.fill(nextBoard[r], -2);
		}
		
		for (int c = 0; c < N; c++) {
			int R = N-1;
			for (int r = N-1; r >= 0; r--) {
				if (board[r][c] >= 0) {
					nextBoard[R][c] = board[r][c];
					R--;
				} else if (board[r][c] == -1) {
					nextBoard[r][c] = board[r][c];
					R = r-1;
				}
			}
		}
		
		deepcopy(nextBoard, board);
	}
	
	private static void rotate() {
		int[][] nextBoard = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				nextBoard[N-1-c][r] = board[r][c];
			}
		}
		
		deepcopy(nextBoard, board);
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
	
	private static void deepcopy(int[][] src, int[][] dest) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				dest[r][c] = src[r][c];
			}
		}
	}
	
	private static void print() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
	
	static class Pair implements Comparable<Pair>{
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}

		@Override
		public int compareTo(Pair o) {
			if (this.r != o.r) return this.r - o.r;
			return this.c - o.c;
		}
	}
	
	static class Node implements Comparable<Node>{
		int cnt, rcnt, sr, sc;

		public Node(int cnt, int rcnt, int sr, int sc) {
			this.cnt = cnt;
			this.rcnt = rcnt;
			this.sr = sr;
			this.sc = sc;
		}

		@Override
		public String toString() {
			return "Node [cnt=" + cnt + ", rcnt=" + rcnt + ", sr=" + sr + ", sc=" + sc + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.cnt != o.cnt) return o.cnt - this.cnt;
			if (this.rcnt != o.rcnt) return o.rcnt - this.rcnt;
			if (this.sr != o.sr) return o.sr - this.sr;
			else return o.sc - this.sc;
		}
	}
}
