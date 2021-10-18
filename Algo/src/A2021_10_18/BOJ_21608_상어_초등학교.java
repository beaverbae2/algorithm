package A2021_10_18;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 33MIN
 * @author beaverbae
 *
 */

public class BOJ_21608_상어_초등학교 {
	static int[][] board;
	static int N;
	static final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static HashSet<Integer>[] favorites;
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		favorites = new HashSet[N*N+1];
		board = new int[N][N];

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq = new PriorityQueue<>();
			
			int n = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			int n4 = Integer.parseInt(st.nextToken());
			
			favorites[n] = new HashSet<>();
			favorites[n].add(n1);
			favorites[n].add(n2);
			favorites[n].add(n3);
			favorites[n].add(n4);
			
			add(n);
		}

		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int cnt = 0;
				int n = board[r][c];
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isIn(nr, nc)) continue;
					
					if (favorites[n].contains(board[nr][nc])) cnt++;
				}
				
				if (cnt == 0) {
					continue;
				} else if (cnt == 1) {
					ans += 1;
				} else if (cnt == 2) {
					ans += 10;
				} else if (cnt == 3) {
					ans += 100;
				} else if (cnt == 4) {
					ans += 1000;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	private static void add(int n) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!isBlank(r, c)) continue;
				
				checkCounts(n, r, c);
			}
		}
		
		Node top = pq.poll();
		board[top.r][top.c] = n;
	}

	private static void checkCounts(int n, int r, int c) {
		int f_cnt = 0;
		int b_cnt = 0;
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if (!isIn(nr, nc)) continue;
			
			if (isBlank(nr, nc)) b_cnt++;
			else if (favorites[n].contains(board[nr][nc])) f_cnt++;
		}
		
		pq.add(new Node(r, c, f_cnt, b_cnt));
	}
	
	private static boolean isBlank(int r, int c) {
		return board[r][c] == 0;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >=0 && nr < N && nc >= 0 && nc < N;
	}

	static class Node implements Comparable<Node> {
		int r, c, f_cnt, b_cnt;
		
		public Node(int r, int c, int f_cnt, int b_cnt) {
			this.r = r;
			this.c = c;
			this.f_cnt = f_cnt;
			this.b_cnt = b_cnt;
		}

		@Override
		public int compareTo(Node o) {
			// 1. 좋아하는 사람 수 많은 걸로
			if (this.f_cnt != o.f_cnt) {
				return o.f_cnt - this.f_cnt;
			}
			
			// 2. 빈칸 수 많은 걸로
			if (this.b_cnt != o.b_cnt) {
				return  o.b_cnt - this.b_cnt;
			}
			
			// 3. 행이 작은 걸로
			if (this.r != o.r) {
				return this.r - o.r;
			}
			
			// 4. 열이 작은 걸로
			return this.c - o.c;
		}
	}
}
