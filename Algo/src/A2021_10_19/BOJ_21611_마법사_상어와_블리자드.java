package A2021_10_19;

import java.util.*;
import java.io.*;

/**
 * 5시간?
 * Simulation
 * @author beaverbae
 * 와진짜 어렵다
 */

public class BOJ_21611_마법사_상어와_블리자드 {
	static int N, M;
	static int[][] board;
	static int sr, sc;// 상어의 좌표
	static int[][] dirs = {{}, {-1,0}, {1,0}, {0,-1}, {0,1}};
	static ArrayList<Pair> positions;
	static int total;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sr = N/2;
		sc = N/2;
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] != 0) total++;
			}
		}
		
		makePositions();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int di = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());
			
			blizzard(di, si);
			move();
			bomb();
			change();
		}
		
		System.out.println(ans);
	}
	
	
	
	private static void change() {
		Queue<Integer> q = new LinkedList<>();
		Pair p = positions.get(1);
		int ball = board[p.r][p.c];
		int cnt = 1;
		total = 0;
		
		if (ball == 0) return;
		
		for (int i = 2; i < positions.size(); i++) {
			p = positions.get(i);
			int r = p.r;
			int c = p.c;
			
			if (ball != board[r][c]) {
				q.offer(cnt);
				q.offer(ball);
				
				ball = board[r][c];
				cnt = 1;
			} else cnt++;
			
			if (board[r][c] == 0) break;
		}
		
		int[][] nextBoard = new int[N][N];
		int i = 1;
		while (!q.isEmpty()) {
			if (i == positions.size()) break;
			p = positions.get(i);
			nextBoard[p.r][p.c] = q.poll();
			i++;
			total++;
		}
		
		board = nextBoard;
		
	}


	private static void bomb() {
		while (true) {
			boolean flag = false;
			Queue<Pair> q = new LinkedList<>();
			Pair p = positions.get(1);
			q.offer(new Pair(p.r, p.c));
			int ball = board[p.r][p.c];
			
			for (int i = 2; i < positions.size(); i++) {
				p = positions.get(i);
				int r1 = p.r;
				int c1 = p.c;
				
				if (ball != board[r1][c1]) {
					int size = q.size();
					while (!q.isEmpty()) {
						Pair p2 = q.poll();
						int r2 = p2.r;
						int c2 = p2.c;
						if (size >= 4) {
							board[r2][c2] = 0;
							total--;
						}
					}
					
					if (size >= 4) {
						ans += (ball * size);
						flag = true;
					}
					
					ball = board[r1][c1];
					q.clear();
				} 
				q.offer(new Pair(r1, c1));
			}
			
			if (!flag) break;
			move();
		}
	}



	private static void move() {
		int[][] nextBoard = new int[N][N];
		int i = 1;
		int j = 1;
		int cnt = 0;
		
		while (true) {
			if (j == positions.size() || cnt == total) break;
			
			Pair pj = positions.get(j);
			int r = pj.r;
			int c = pj.c;
			
			if (board[r][c] != 0) {
				Pair pi = positions.get(i);
				int nr = pi.r;
				int nc = pi.c;
				nextBoard[nr][nc] = board[r][c];
				i++;
				cnt++;
			}
			j++;
		}
		
		board = nextBoard;
	}


	private static void blizzard(int di, int si) {
		for (int i = 1; i <= si; i++) {
			int nr = sr + i * dirs[di][0];
			int nc = sc + i * dirs[di][1];
			
			if (board[nr][nc] != 0) {
				total--;
				board[nr][nc] = 0;
			}
		}
	}

	private static void makePositions() {
		positions = new ArrayList<>();
		int[][] dirs = {{0,-1}, {1,0}, {0,1}, {-1,0}};
		int r = sr;
		int c = sc;
		int n = 1;
		int d = 0;
		int cnt = 0;
		
		while (true) {
			if (c == -1) break;
			
			positions.add(new Pair(r, c));
			
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			cnt++;
			
			if (cnt == n) {
				d++;
				cnt = 0;
				
				if (d == dirs.length) d = 0;
				if (d % 2 == 0) n++;
			}
			
			r = nr;
			c = nc;
		}
		
	}

	
	private static void print() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}

	
	private static void print(int[][] b) {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(b[r]));
		}
		System.out.println();
	}

	private static void iter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
			}
		}
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
}
