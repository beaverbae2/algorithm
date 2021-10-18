package A2021_10_18;

/**
 * Simulation
 * 1H52MIN
 * @author beaverbae
 * 문제 잘 읽자
 */

import java.util.*;
import java.io.*;

public class BOJ_21609_상어_중학교 {
	static int N, M, ans;
	static int[][] board, nextBoard;
	static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static PriorityQueue<Node> pq;
	
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
		
		while (true) {
			if (!findGroups()) break;
			
			Node cur = pq.poll();
			removeMaxGroup(cur.r, cur.c);
			ans += (cur.cnt * cur.cnt);
			
			gravity();
			rotate();
			gravity();
			
		}
		
		System.out.println(ans);
	}

	private static void gravity() {
		nextBoard = new int[N][N];
		
		for (int c = 0; c < N; c++) {
			int nr = N-1;
			for (int r = N-1; r >= 0; r--) {
				if (isVacant(r, c)) continue;
				if (isBlack(r, c)) nr = r-1;
				else {
					int temp = board[nr][c];
					board[nr][c] = board[r][c];
					board[r][c] = temp;
					nr--;
				}
			}
		}
	}
	
	private static void rotate() {
		nextBoard = new int[N][N];
		
		int c1 = N;
		for (int r2 = 0; r2 < N; r2++) {
			int r1 = 0;
			c1--;
			for (int c2 = 0; c2 < N; c2++) {
				nextBoard[r2][c2] = board[r1][c1];
				r1++;
			}
		}
		
		board = nextBoard;
	}

	private static void printBoard() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
	
	private static void removeMaxGroup(int sr, int sc) {
		int color = board[sr][sc];
		
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			board[r][c] = M+1;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc]) continue;
				
				if (isRainbow(nr, nc) || board[nr][nc] == color) {
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static boolean findGroups() {
		pq = new PriorityQueue<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!isNormal(r, c)) continue;
				
				makeGroup(r, c);
			}
		}
		
		return pq.size() > 0;
	}
	
	private static void makeGroup(int sr, int sc) {
		int color = board[sr][sc];
		int cnt = 1;
		int r_cnt = 0;
		
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Pair> pq1 = new PriorityQueue<>();
		q.offer(new Pair(sr, sc));
		pq1.add(new Pair(sr, sc));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || visited[nr][nc] || isBlack(nr, nc)) continue;
				
				if (isRainbow(nr, nc) || board[nr][nc] == color) {
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
					cnt++;
					
					if (!isRainbow(nr, nc)) {
						pq1.add(new Pair(nr, nc));
					} else r_cnt++;
				}
			}
		}
		
		if (cnt == 1) return;
		Pair p = pq1.poll();
		pq.add(new Node(p.r, p.c, cnt, r_cnt));
	}
	

	private static boolean isNormal(int r, int c) {
		return board[r][c] > 0 && board[r][c] < M+1;
	}
	
	
	private static boolean isBlack(int r, int c) {
		return board[r][c] == -1;
	}
	
	private static boolean isRainbow(int r, int c) {
		return board[r][c] == 0;
	}
	
	private static boolean isVacant(int r, int c) {
		return board[r][c] == M+1;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
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
	
	static class Node implements Comparable<Node> {
		int r, c, cnt, r_cnt;

		
		public Node(int r, int c, int cnt, int r_cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.r_cnt = r_cnt;
		}

		@Override
		public int compareTo(Node o) {
			if (this.cnt != o.cnt) return o.cnt - this.cnt;
			if (this.r_cnt != o.r_cnt) return o.r_cnt - this.r_cnt;
			
			if (this.r != o.r) return o.r - this.r;
			
			return o.c - this.c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}
	}
}
