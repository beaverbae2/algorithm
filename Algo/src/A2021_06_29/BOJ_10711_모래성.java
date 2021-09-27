package A2021_06_29;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 79MIN
 * 백준 알고리즘 분류 참고
 * 
 * 어려웠던 부분
 * - 처음에 단순 시뮬로 구현 -> 시간 초과
 * - BFS로 접근 가능한 이유 -> 직전 시간에 부서진 격자가 현재 시간의 격자에 영향을 미침 
 * - 격자의 현재 상태와 다음 상태의 구분이 어려웠음 -> 격자가 부셔졌다고 바로 표시하면 안된다...
 * 
 * @author beave
 *
 */

public class BOJ_10711_모래성 {
	static int R, C;
	static int[][] board;
	static boolean[][] visited;
	static int[][] dirs = {{1,0}, {-1,0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			char[] arr = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				char ch = arr[c];
				
				if (ch == '.') {
					board[r][c] = 0;
				} else {
					board[r][c] = ch - '0';
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int ans = 0;
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[R][C];
		
		// 초기화
		for (int r = 1; r < R-1; r++) {
			for (int c = 1; c < C-1; c++) {
				if (board[r][c] == 0) continue;
				
				int zero_cnt = 0;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (board[nr][nc] == 0) {
						zero_cnt++;
					}
					
					if (zero_cnt == board[r][c]) {
						q.offer(new Node(r, c, 1));
						visited[r][c] = true;
						break;
					}
				}
			}
		}
		
		// 탐색
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			board[r][c] = 0;
			ans = t;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isOk(nr, nc)) {
					q.offer(new Node(nr, nc, t+1));
					visited[nr][nc] = true;
				}
			}
		}
		
		return ans;
	}

	private static boolean isOk(int r, int c) {
		if (visited[r][c] || board[r][c] == 0 || board[r][c] == 9) return false;
		
		int zero_cnt = 0;
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
		
			if (board[nr][nc] == 0) {
				zero_cnt++;
			}
		}
		
		return zero_cnt >= board[r][c];
	}
	
	static class Node {
		int r, c, t;

		public Node(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", t=" + t + "]";
		}
	}
}
