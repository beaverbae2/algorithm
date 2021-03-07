package A2021_03_08;

import java.util.*;
import java.io.*;

/**
 * MST
 * 85MIN
 * @author beaverbae
 *
 */

public class BOJ_1944_복제_로봇 {
	static List<Node> graph; 
	static char[][] map;
	static int N, M;
	static Pair[] keys;
	static int[][] keys_idx;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<Node>();
		map = new char[N][N];
		keys = new Pair[M+2];
		keys_idx = new int[N][N];
		
		int idx = 1;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char ch = str.charAt(j);
				
				map[i][j] = ch;
				
				if (ch == 'S' || ch == 'K') {
					keys[idx] = new Pair(i, j);
					keys_idx[i][j] = idx;
					idx++;
				}
			}
		}
		
		for (Pair p : keys) {
			if (p == null) continue;
			if (!bfs(p.r, p.c)) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		Collections.sort(graph, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		
		parent = new int[M+2];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		int cnt = 0;
		int answer = 0;
		
		for (Node node : graph) {
			int a = node.a;
			int b = node.b;
			int w = node.w;
			
			if (findParent(a, b)) continue;
			unionParent(a, b);
			
			answer += w;
			cnt++;
			
			if (cnt == M) break;
		}
		
		System.out.println(answer);
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		return false;
	}
	
	private static boolean bfs(int sr, int sc) {
		int cnt = 0;
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.a;
			int c = node.b;
			int w = node.w;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (map[nr][nc] != '1' && !visited[nr][nc]) {
					if (keys_idx[nr][nc] != 0) {
						graph.add(new Node(keys_idx[sr][sc], keys_idx[nr][nc], w+1));
						cnt++;
					}
					q.offer(new Node(nr, nc, w+1));
					visited[nr][nc] = true;
				}
			}
			
		}
		
		if (cnt == M) return true;
		return false;
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
		int a, b, w;

		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
	}
}
