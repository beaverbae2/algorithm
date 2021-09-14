package A2021_09_14;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 76MIN
 * @author beaverbae
 * 행과 열의 이어진 부분 해석, 좌표 헷갈리지 않게 표시, 동시에 이동 발생하는 것 고려, 문제 해석
 *
 */

public class BOJ_20056_마법사_상어와_파이어볼 {
	static int N, M, K;
	static List<Ball>[][] board, next_board;
	static int[][] dirs = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new List[N][N];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				board[x][y] = new ArrayList<>(); 
			}
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			board[x][y].add(new Ball(m, d, s));
		}
		
		while (K-- > 0) {
			move();
			arrange();
		}
		
		int ans = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (Ball b : board[x][y]) {
					ans += b.m;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	private static void arrange() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				board[x][y] = new ArrayList<>();
			}
		}
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (next_board[x][y].size() < 2) {
					for (Ball b : next_board[x][y]) {
						board[x][y].add(new Ball(b.m, b.d, b.s));
					}
					continue;
				}
				
				int m = 0, s = 0;
				boolean isDirOdd = false, isDirEven = false;
				
				for (Ball b : next_board[x][y]) {
					m += b.m;
					s += b.s;
					if (b.d % 2 == 0) isDirEven = true;
					else isDirOdd = true;
				}
				
				m /= 5;
				
				if (m == 0) continue;
				
				s /= next_board[x][y].size();
				if (isDirOdd && !isDirEven || !isDirOdd && isDirEven) {
					// 0, 2, 4, 6
					for (int d = 0; d < dirs.length; d+=2) {
						board[x][y].add(new Ball(m, d, s));
					}
				} else {
					// 1, 3, 5, 7
					for (int d = 1; d < dirs.length; d+=2) {
						board[x][y].add(new Ball(m, d, s));
					}
				}
			}
		}
	}

	
	private static void move() {
		next_board = new List[N][N];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				next_board[x][y] = new ArrayList<>(); 
			}
		}
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (board[x][y].isEmpty()) continue;
				
				// 모든 볼에 대해
				for (Ball b : board[x][y]) {
					int s = b.s % N;
					int nx = x;
					int ny = y;
					
					while (s-- > 0) {
						nx = nx + dirs[b.d][0];
						ny = ny + dirs[b.d][1];
					
						nx = getNext(nx);
						ny = getNext(ny);
					}
					
					next_board[nx][ny].add(new Ball(b.m, b.d, b.s));
				}
			}
		}
	}
	
	private static int getNext(int n) {
		if (n < 0) n += N;
		else if (n >= N) n -= N;
		return n;
	}

	static class Ball {
		int m, d, s;

		public Ball(int m, int d, int s) {
			this.m = m;
			this.d = d;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Ball [m=" + m + ", d=" + d + ", s=" + s + "]";
		}
	}
}
