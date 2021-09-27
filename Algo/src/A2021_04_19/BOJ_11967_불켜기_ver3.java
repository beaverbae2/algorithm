package A2021_04_19;

import java.util.*;
import java.io.*;

public class BOJ_11967_불켜기_ver3 {
	
	static int N, M;
	static List<Node>[][] list;
	static boolean[][] isLight;
	static boolean[][] isMove;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		isLight = new boolean[N][N];
		isMove = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			list[x][y].add(new Node(a, b));
		}
		
		System.out.println(bfs(0, 0));
	}
	
	private static int bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		isLight[sr][sc] = true;
		int cnt = 1;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			
			// 이동할 수 있는 위치 추가
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isIn(nr, nc) && !visited[nr][nc] && !isMove[nr][nc]) {
					isMove[nr][nc] = true; 
				}
			}

			// 불 켜기
			for (Node n : list[r][c]) {
				if (!isLight[n.r][n.c]) {
					isLight[n.r][n.c] = true;
					cnt++;
				}
				
				// 불킨 곳으로 이동가능한 경우
				if (isLight[n.r][n.c] && !visited[n.r][n.c] && isMove[n.r][n.c]) {
					visited[n.r][n.c] = true;
					q.offer(new Node(n.r, n.c));
				}
			}
			
			
			// 다음에 이동할 수 있는 위치 추가
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isIn(nr, nc) && isLight[nr][nc] && !visited[nr][nc] && isMove[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;   
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
