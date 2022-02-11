package A2022_02_11;

import java.util.*;
import java.io.*;

public class BOJ_18809_Gaaaaaaaaaarden {
	static int N, M, G, R;
	static int[][] board;// 0 : 호수, 1 : 베양액X, 2 : 배양액O;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static List<Pair> list;
	static LinkedList<Pair> selectedList;
	static int[][] visited;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		board = new int[N][M];
		visited = new int[N][M];
		list = new ArrayList<>();
		selectedList = new LinkedList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			
				if (board[r][c] == 2) {// 배양액을 뿌릴 수 있는 땅
					list.add(new Pair(r, c));
				}
			}
		}
	
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	
	private static void dfs(int idx, int red, int green) {
		if (red == R && green == G) {
			ans = Math.max(ans, bfs());
			return;
		}
		
		if (idx == list.size()) return;
		
		Pair p = list.get(idx);
		dfs(idx+1, red, green);
		if (red < R) {
			selectedList.add(new Pair(p.r, p.c));
			visited[p.r][p.c] = -1;
			dfs(idx+1, red+1, green);
			selectedList.removeLast();
			visited[p.r][p.c] = 0;
		}
		if (green < G) {
			selectedList.add(new Pair(p.r, p.c));
			visited[p.r][p.c] = 1;
			dfs(idx+1, red, green+1);
			selectedList.removeLast();
			visited[p.r][p.c] = 0;
		}
	}
	
	private static int bfs() {
		int result = 0;
		Queue<Pair> q = new LinkedList<>();
		int[][] qVisited = new int[N][M];
		boolean[][] isFlower = new boolean[N][M];
		
		for (Pair p : selectedList) {
			q.offer(new Pair(p.r, p.c));
			qVisited[p.r][p.c] = visited[p.r][p.c];
		}
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			if (isFlower[p.r][p.c]) continue;// 중요
			
			int nt = qVisited[p.r][p.c];
			if (nt < 0) nt--;
			else nt++;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = p.r + dirs[d][0];
				int nc = p.c + dirs[d][1];
			
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 0 || isFlower[nr][nc]) continue;
				
				if (qVisited[nr][nc] == 0) {// 배양액이 방문하지 않은 곳 
					q.offer(new Pair(nr, nc));
					qVisited[nr][nc] = nt;
				} else if (qVisited[nr][nc] + nt == 0) {
					qVisited[nr][nc] = 0;
					isFlower[nr][nc] = true;
					result++;
				}
			}
		}
		
		return result;
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
