package A2021_10_20;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 53MIN 
 * @author beaverbae
 *
 */

public class BOJ_20058_마법사_상어와_파이어스톰 {
	static int N, Q, totalAmount, biggestCnt;
	static int[][] board;
	static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		N = (int) Math.pow(2, N);
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		while (Q-- > 0) {
			int L = Integer.parseInt(st.nextToken());
			L = (int) Math.pow(2, L);
			int n = (N*N) / (L*L);
			int cnt = 0;
			
			int sr = 0;
			int sc = 0;
			int er = sr + L;
			int ec = sc + L;
			
			while (true) {
				if (cnt == n) break;
				
				
				if (sc == N) {
					sr += L;
					sc = 0;
					er += L;
					ec = L;
				}
				
				rotate(sr, sc, er, ec, L);
				sc += L;
				ec += L;
				cnt++;
			}
			
			decrease();
		}
		
		getAnswer();
		System.out.println(totalAmount);
		System.out.println(biggestCnt);
	}
	
	
	
	
	private static void getAnswer() {
		boolean[][] visited = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				totalAmount += board[r][c];
				if (board[r][c] == 0 || visited[r][c]) continue;
			
				biggestCnt = Math.max(biggestCnt, bfs(r, c, visited));
			}
		}
	}


	private static int bfs(int sr, int sc, boolean[][] visited) {
		int cnt = 1;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || board[nr][nc] == 0 || visited[nr][nc]) continue;
				
				q.offer(new Node(nr, nc));
				visited[nr][nc] = true;
				cnt++;
			}
		}
		
		return cnt;
	}




	private static void decrease() {
		int[][] nextBoard = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] == 0) continue;
				
				int iceCnt = 0;
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isIn(nr, nc) || board[nr][nc] == 0) continue;
					iceCnt++;
				}
				
				if (iceCnt < 3) nextBoard[r][c]--;
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				nextBoard[r][c] += board[r][c];
			}
		}
		
		board = nextBoard;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}


	private static void rotate(int sr, int sc, int er, int ec, int L) {
		int[][] temp = new int[L][L];
		int r2 = 0;
		int c2 = 0;
		
		for (int c1 = sc; c1 < ec; c1++) {
			
			for (int r1 = er - 1; r1 >= sr; r1--) {
				temp[r2][c2] = board[r1][c1];
				c2++;
				if (c2 == L) {
					r2++;
					c2 = 0;
				}
			}
			
		}

		r2 = 0;
		c2 = 0;
		for (int r1 = sr; r1 < er; r1++) {
			for (int c1 = sc; c1 < ec; c1++) {
				board[r1][c1] = temp[r2][c2];
				c2++;
				if (c2 == L) {
					r2++;
					c2 = 0;
				}
			}
			
		}
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

	private static void print() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
}
