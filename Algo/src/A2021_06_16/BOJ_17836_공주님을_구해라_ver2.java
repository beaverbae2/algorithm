package A2021_06_16;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 놓쳤던 부분
 * - 탐색시 시간이 T이하가 맞는데, T의 최댓값이 될 수 있는 10000이하로 설정함
 * - int형 방문 배열로 하면 메모리 초과
 * 
 * @author beaverbae
 *
 */

public class BOJ_17836_공주님을_구해라_ver2 {
	static int N, M, T;
	static int[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static final int INF = 10001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		int answer = bfs();
		if (answer == INF) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][2];
		
		q.offer(new Node(0, 0, 0, false));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.t > T) {
				break;
			}
			
			if (cur.r == N-1 && cur.c == M-1) {
				return cur.t;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = cur.r + dirs[d][0];
				int nc = cur.c + dirs[d][1];
				
				if (!isIn(nr, nc)) {
					continue;
				}
				
				if (!cur.isGram) {
					if (map[nr][nc] == 1) {
						continue;
					} else if (map[nr][nc] == 0){
						if (visited[nr][nc][0]) {
							continue;
						}
						q.offer(new Node(nr, nc, cur.t+1, false));
						visited[nr][nc][0] = true;
					} else {
						if (visited[nr][nc][1]) {
							continue;
						}
						q.offer(new Node(nr, nc, cur.t+1, true));
						visited[nr][nc][1] = true;
					}
				} else {
					if (visited[nr][nc][1]) {
						continue;
					}
					q.offer(new Node(nr, nc, cur.t+1, true));
					visited[nr][nc][1] = true;
				}
			}
			
		}
		
		return INF;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}

	static class Node {
		int r, c, t;
		boolean isGram;
		public Node(int r, int c, int t, boolean isGram) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.isGram = isGram;
		}
		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + t + ", isGram=" + isGram + "]";
		}
	}
}
