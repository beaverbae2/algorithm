package A2021_01_14;

import java.util.*;
import java.io.*;

public class BOJ_4991_로봇청소기_Fail {
	static char[][] map;
	static int R, C;
	static int[][][] visited;//남아있는 먼저 개수
	static int initDirty;//초기 먼지 개수
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			if (C == 0 && R == 0) break;
			
			map = new char[R][C];
			int sr = -1, sc = -1;//로봇의 좌표
			initDirty = 0;
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
				
					if (map[i][j] == 'o') {
						sr = i;
						sc = j;
						map[i][j] = '.';
					}else if (map[i][j] == '*') {
						initDirty++;
					}
				}
			}
			visited = new int[initDirty+1][R][C];
			final int INF = 987654321;
			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[i].length; j++) {
					Arrays.fill(visited[i][j], INF);
				}
			}
			visited[initDirty][sr][sc] = 0;
			dfs(sr, sc, 0, initDirty);
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (visited[0][i][j] != INF) {
						System.out.println("r : "+i+", c : "+j+", v : "+visited[0][i][j]);
					}
				}
			}
			System.out.println();
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c, int depth, int remain) {
		if(remain == 0) {
			answer = Math.min(answer, depth);
			return;
		}
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if (!isInMap(nr, nc)||map[nr][nc] == 'x') continue;
			if (map[nr][nc] == '*') {
				if (visited[remain-1][nr][nc] > depth+1) {
					visited[remain-1][nr][nc] = depth+1;
					map[nr][nc] = '.'; 
					dfs(nr, nc, depth+1, remain-1);
					map[nr][nc] = '*';
				}
			}else if (map[nr][nc] == '.') {
				if (visited[remain][nr][nc] > depth+1) {
					visited[remain][nr][nc] = depth+1;
					dfs(nr, nc, depth+1, remain);
				}
			}
		}
	}
	
//	private static int bfs(int sr, int sc) {
//		PriorityQueue<Node> pq = new PriorityQueue<>();
//		pq.add(new Node(sr, sc, 0));
//		visited[sr][sc] = 0;
//		
//		while(!pq.isEmpty()) {
//			System.out.println(pq);
//			Node node = pq.poll();
//			int r = node.r;
//			int c = node.c;
//			int depth = node.depth;
//			
//			if (visited[r][c] == initDirty) return depth;
//			
//			//탐색
//			for (int d = 0; d < dirs.length; d++) {
//				int nr = r + dirs[d][0];
//				int nc = c + dirs[d][1];
//				
//				if (!isInMap(nr, nc)||map[nr][nc] == 'x') continue;
//				if (map[nr][nc] == '*') {
//					if (visited[nr][nc] <= visited[r][c]+1) {
//						visited[nr][nc] = visited[r][c]+1;
//						pq.add(new Node(nr, nc, depth+1));
//					}
//				}else if (map[nr][nc] == '.') {
//					if (visited[nr][nc] <= visited[r][c]) {
//						visited[nr][nc] = visited[r][c];
//						pq.add(new Node(nr, nc, depth+1));
//					}
//				}
//			}
//		}
//		
//		return -1;
//	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}

	static class Node implements Comparable<Node>{
		int r, c, depth;

		public Node(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.depth, o.depth);
		}

		
	}
}
