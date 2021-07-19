package A2021_07_19;

import java.io.*;
import java.util.*;

/**
 * BFS, MST
 * 백준 질문 검색 참고
 * 어려웠던 부분
 * - BFS할 때 꼼꼼히 체크하자
 * - 모든 열쇠를 획득할 수 없는 경우
 *     - 크루스칼 알고리즘 적용 후 그래프가 하나 존재해야함
 *     - 하나 존재하는 지 체크 잘못함 
 *     - 그래프가 하나여도 모든 parent[i]의 값이 1이 아님 -> find()를 통해 확인해야함
 *  
 * @author beaverbae
 *
 */

public class BOJ_1944_복제_로봇_solution {
	static int N, M;
	static char[][] board;
	static boolean[][] visited;
	static List<Node> graph;
	static HashMap<Integer, Integer> map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][N];
		graph = new ArrayList<>();
		map = new HashMap<>();
		
		int v = 1;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = str.charAt(j);
				if (board[i][j] == 'S' || board[i][j] == 'K') {
					int n = i * N + j;
					map.put(n, v++);
				}
			}
		}
		
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N-1; j++) {
				if (board[i][j] == 'S' || board[i][j] == 'K') {
					bfs(i, j);
				}
			}
		}
		
		parent = new int[map.size() + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		Collections.sort(graph, (o1, o2) -> o1.w - o2.w);
		
		int ans = 0;
		int cnt = 0;
		for (Node cur : graph) {
			int a = cur.a;
			int b = cur.b;
			int w = cur.w;
			
			if (findParent(a, b)) continue;
			unionParent(a, b);
			
			ans += w;
			cnt++;
			if (cnt == map.size() - 1) break;
		}
		
		for (int i = 1; i < parent.length; i++) {
			if (!findParent(i, 1)) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(ans);
	}
	
	private static int getParent(int v) {
		if (v == parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		return a == b;
	}
	
	private static void bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[N][N];
		
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;

		int s = map.get(sr * N + sc);
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.a;
			int c = cur.b;
			int w = cur.w;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
			
				if (board[nr][nc] == '1' || visited[nr][nc]) continue;
				
				if (board[nr][nc] == 'S' || board[nr][nc] == 'K') {
					int e = map.get(nr * N + nc);
					graph.add(new Node(s, e, w+1));
				}
				
				visited[nr][nc] = true;
				q.offer(new Node(nr, nc, w+1));
			}
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
