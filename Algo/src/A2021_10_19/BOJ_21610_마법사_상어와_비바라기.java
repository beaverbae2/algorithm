package A2021_10_19;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 59MIN
 * @author beaverbae
 *
 */

public class BOJ_21610_마법사_상어와_비바라기 {
	static int N, M;
	static int[][] board, nextBoard;
	static final int[][] dirs1 = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static final int[][] dirs2 = {{1,1},{1,-1},{-1,1},{-1,-1}};
	static Queue<Node> q;
	static boolean[][] isCloudBefore;
	
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
		
		q = new LinkedList<>();
		q.offer(new Node(N-1, 0));
		q.offer(new Node(N-1, 1));
		q.offer(new Node(N-2, 0));
		q.offer(new Node(N-2, 1));
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			moveAndCopy(d, s);
			add(nextBoard, board);
			findClouds();
		}
	
		System.out.println(getAns());
	}
	
	private static void add(int[][] src, int[][] dest) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				dest[r][c] += src[r][c];
			}
		}
	}

	private static void printBoard() {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(board[r]));
		}
		System.out.println();
	}
	
	private static void findClouds() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] < 2 || isCloudBefore[r][c]) continue;
				
				q.offer(new Node(r, c));
				board[r][c] -= 2;
			}
		}
	}


	private static void moveAndCopy(int d, int s) {
		// 이동
		nextBoard = new int[N][N];
		isCloudBefore = new boolean[N][N];
		
		int n = q.size();
		while (n-- > 0) {
			Node cur = q.poll(); // 구름 사라짐
			int r = cur.r;
			int c = cur.c;
			
			// 구름 이동
			for (int i = 0; i < s; i++) {
				r += dirs1[d][0];
				c += dirs1[d][1];
				
				if (r >= N) r = 0;
				else if (r < 0) r = N-1;
				
				if (c >= N) c = 0;
				else if (c < 0) c= N-1;
			}
			
			// 물의 양 1 증가
			nextBoard[r][c] += 1;
			
			q.offer(new Node(r, c));
		}
		add(nextBoard, board);
		
		// 물 복사
		nextBoard = new int[N][N];
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = 0;
			isCloudBefore[r][c] = true;
			
			for (int i = 0; i < dirs2.length; i++) {
				int nr = r + dirs2[i][0];
				int nc = c + dirs2[i][1];
				
				if (!isIn(nr, nc) || board[nr][nc] == 0) continue;
				
				cnt++;
			}
			
			nextBoard[r][c] += cnt;
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static int getAns() {
		int result = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result += board[r][c];
			}
		}
		
		return result;
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
}
