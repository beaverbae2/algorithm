package A2022_03_23;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 25MIN
 * @author beaverbae
 *
 */

public class BOJ_15686_치킨배달 {
	static int N, M;
	static int[][] board;
	static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static List<Pair> list;
	static LinkedList<Integer> selected;
	static int ans = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		list = new ArrayList<>();
		selected = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) list.add(new Pair(i, j));
			}
		}
		
		dfs(0, 0);
		
		System.out.println(ans);
	}
	
	private static void dfs(int start, int cnt) {
		if (cnt == M) {
			ans = Math.min(ans, bfs());
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			selected.add(i);
			dfs(i+1, cnt+1);
			selected.removeLast();
		}
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int total = 0;
		
		for (int i : selected) {
			Pair p = list.get(i);
			q.offer(new Node(p.r, p.c, 0));
			visited[p.r][p.c] = true;
		}
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			int r = n.r;
			int c = n.c;
			int d = n.d;
			
			if (board[r][c] == 1) total += d;
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				q.offer(new Node(nr, nc, d+1));
				visited[nr][nc] = true;
			}
		}
		
		return total;
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
		int r, c, d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
	}
}
