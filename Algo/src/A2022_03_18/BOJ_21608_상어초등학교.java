package A2022_03_18;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 38MIN
 * @author beaverbae
 * - 현재 학생의번호 파악, 탐색시 이미 학생이 있는 경우는 배제
 */

public class BOJ_21608_상어초등학교 {
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int[][] board;
	static boolean[][] isLike;
	static int N, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		isLike = new boolean[N*N+1][N*N+1];
		
		for (int k = 0; k < N*N; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				isLike[i][Integer.parseInt(st.nextToken())] = true;
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] != 0) continue;
					
					int like = 0, blank = 0;
					for (int d = 0; d < dirs.length; d++) {
						int nr = r + dirs[d][0];
						int nc = c + dirs[d][1];
						
						if (!isIn(nr, nc)) continue;
						
						int j = board[nr][nc];
						if (isLike[i][j]) like++;
						else if (j == 0) blank++;
					}
					
					pq.add(new Node(r, c, like, blank));
				}
			}
			
			Node n = pq.poll();
			board[n.r][n.c] = i;
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int i = board[r][c];
				int cnt = 0;
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isIn(nr, nc)) continue;
					int j = board[nr][nc];
					
					if (isLike[i][j]) cnt++;
				}
				
				ans += (int) Math.pow(10, cnt-1);
			}
		}
		
		System.out.println(ans);
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
	
	static class Node implements Comparable<Node>{
		int r, c , like, blank;

		public Node(int r, int c, int like, int blank) {
			this.r = r;
			this.c = c;
			this.like = like;
			this.blank = blank;
		}
		
		public int compareTo(Node o) {
			if (this.like != o.like) return o.like - this.like;
			if (this.blank != o.blank) return o.blank - this.blank;
			if (this.r != o.r) return this.r - o.r;
			return this.c - o.c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", like=" + like + ", blank=" + blank + "]";
		}
	}
}
