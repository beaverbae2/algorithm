package A2022_04_17;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 126MIN
 * @author beaverbae
 * - 빠르고 정확하게 푸는 연습 필요
 * 
 */

public class BOJ_23289_온풍기안녕 {
	static int R, C, K, W;
	static int[][] board;
	static List<Pair> checkpoints;
	static List<Node> firepoints;
	static boolean[][][][] isWall;
	static final int[][] dirs = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static final int[][][] dirs2 = {{}, {{-1, 1}, {0, 1}, {1, 1}}, 
										{{-1, -1}, {0, -1}, {1, -1}}, 
										{{-1, -1}, {-1, 0}, {-1, 1}}, 
										{{1, -1}, {1, 0}, {1, 1}}};
	static final int[][][] dirs3 = {{}, {{3, 1}, {1}, {4, 1}}, 
										{{3, 2}, {2}, {4, 2}}, 
										{{2, 3}, {3}, {1, 3}}, 
										{{2, 4}, {4}, {1, 4}}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		board = new int[R][C];
		checkpoints = new ArrayList<>();
		firepoints = new ArrayList<>();
		isWall = new boolean[R][C][R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int d = Integer.parseInt(st.nextToken());
				
				if (d > 4) {
					checkpoints.add(new Pair(r, c));
				} else if (d > 0) {
					firepoints.add(new Node(r, c, d, -1));
				}
			}
		}
		
		W = Integer.parseInt(br.readLine());
		while (W-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
		
			if (t == 0) {
				isWall[r][c][r-1][c] = true;
				isWall[r-1][c][r][c] = true;
			} else {
				isWall[r][c][r][c+1] = true;
				isWall[r][c+1][r][c] = true;
			}
		}
		
		System.out.println(exec());
	}
	
	private static int exec() {
		int ans = 0;
		
		while (ans <= 100) {
			for (Node n : firepoints) {
				int r = n.r + dirs[n.d][0];
				int c = n.c + dirs[n.d][1];
				setFire(r, c, n.d);
			}
			arranage();
			outSideTempDown();
			ans++;
			if (check()) break;
		}
		
		return ans;
	}
	
	private static void printBoard() {
		for (int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}

	private static void setFire(int sr, int sc, int sd) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		q.offer(new Node(sr, sc, sd, 5));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node p = q.poll();
			
			if (p.t == 0) break;
			
			board[p.r][p.c] += p.t;
			for (int i = 0; i < dirs3[p.d].length; i++) {
				int cnt = 0;
				int r, c, nr, nc;
				r = nr = p.r;
				c = nc = p.c;
				
				for (int j = 0; j < dirs3[p.d][i].length; j++) {
					int d = dirs3[p.d][i][j];
					nr = r + dirs[d][0];
					nc = c + dirs[d][1];
					
					if (!isIn(nr, nc) || isWall[r][c][nr][nc] || visited[nr][nc]) break;
					
					r = nr;
					c = nc;
					cnt++;
				}
				
				if (cnt != dirs3[p.d][i].length) continue;
				
				nr = p.r + dirs2[p.d][i][0];
				nc = p.c + dirs2[p.d][i][1];
				
				q.offer(new Node(nr, nc, p.d, p.t-1));
				visited[nr][nc] = true;
			}
			
		}
	}

	private static void arranage() {
		int[][] changeBoard = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				visited[r][c] = true;
				
				for (int d = 1; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
				
					if (!isIn(nr, nc) || visited[nr][nc] || isWall[r][c][nr][nc]) continue;
					
					int add = (board[nr][nc] - board[r][c]) / 4;
					
					changeBoard[r][c] += add;
					changeBoard[nr][nc] -= add;
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				board[r][c] += changeBoard[r][c];
			}
		}
	}

	private static void outSideTempDown() {
		for (int r = 0; r < R; r++) {
			if (board[r][0] > 0) board[r][0]--;
			if (board[r][C-1] > 0) board[r][C-1]--;
		}
		
		for (int c = 1; c < C-1; c++) {
			if (board[0][c] > 0) board[0][c]--;
			if (board[R-1][c] > 0) board[R-1][c]--;
		}
	}

	private static boolean check() {
		int cnt = 0;
		for (Pair p : checkpoints) {
			if (board[p.r][p.c] >= K) cnt++;
		}
		
		return cnt == checkpoints.size();
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Node {
		int r, c, d, t;
		
		public Node(int r, int c, int d, int t) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.t = t;
		}



		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + ", t=" + t + "]";
		}
	}
}
