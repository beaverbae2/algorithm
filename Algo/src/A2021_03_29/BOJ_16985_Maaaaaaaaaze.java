package A2021_03_29;

import java.util.*;
import java.io.*;

/**
 * Brute Force (DFS, BFS, indexing)
 * 1H45MIN
 * 오래 걸린 이유 : 3차원, 배열 돌리기, 잔실수
 * @author beaverbae
 * 
 */

public class BOJ_16985_Maaaaaaaaaze {
	static int[][][] origin_maze;
	static int[][][] maze;
	static int[][][] real_maze;
	static int N = 5;
	static boolean[] visited;
	static int[] order;
	static int[] rotate;
	
	//////////	수정 부분	//////////
	static int[][] start = {{0,0,0}};
	static int[][] end = {{4,4,4}};
	//////////////////////////////
	static int[][] dirs = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		origin_maze = new int[N][N][N];
		maze = new int[N][N][N];
		real_maze = new int[N][N][N];
		
		for (int r = 0; r < 5*N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				origin_maze[r/N][r%N][c] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		visited = new boolean[N];
		order = new int[N];
		rotate = new int[N];
		
		getOrder(0);
		
		
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	// 2차원 미로의 순서 결정
	private static void getOrder(int idx) {
		if (idx == N) {
			setMaze();
			getRotate(0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			order[idx] = i;
			getOrder(idx+1);
			visited[i] = false;
		}
	}
	
	// 순서대로 미로 구성
	private static void setMaze() {
		int idx = 0;
		for (int k : order) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					maze[idx][r][c] = origin_maze[k][r][c];
				}
			}
			idx++;
		}
	}
	
	// 각 미로마다 회전 얼만큼하는지 정하기
	private static void getRotate(int idx) {
		if (idx == N) {
			for (int i = 0; i < N; i++) {
				rotate(i, rotate[i]);
			}
			
			for (int i = 0; i < start.length; i++) {
				answer = Math.min(answer, bfs(i));
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			rotate[idx] = i;
			getRotate(idx+1);
		}
	}
	
	// 부여받은 대로 배열 회전
	private static void rotate(int k, int i) {
		if (i == 0) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					real_maze[k][r][c] = maze[k][r][c];
				}
			}
		}
		else if(i==1) {// 시계 90도
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					real_maze[k][c][N-1-r] = maze[k][r][c];
				}
			}
			
		} else if(i==2) {// 시계 180도
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					real_maze[k][N-1-r][N-1-c] = maze[k][r][c];
				}
			}
		} else if(i==3) {// 시계 270도
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					real_maze[k][N-1-c][r] = maze[k][r][c];
				}
			}
		}
	}
	
	// 탐색
	private static int bfs(int idx) {
		int sk = start[idx][0];
		int sr = start[idx][1];
		int sc = start[idx][2];
		
		int ek = end[idx][0];
		int er = end[idx][1];
		int ec = end[idx][2];
		
		// 출발지와 도착지가 막힌 경우
		if (real_maze[sk][sr][sc] == 0 || real_maze[ek][er][ec] == 0) {
			return Integer.MAX_VALUE;
		}
		
		// 초기화
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][N][N];
		
		q.offer(new Node(sk, sr, sc, 0));
		visited[sk][sr][sc] = true;
		
		
		// 탐색
		while(!q.isEmpty()) {
			Node node = q.poll();
			int k = node.k;
			int r = node.r;
			int c = node.c;
			int depth = node.depth;
			
			// 미로 탈출 성공
			if (k == ek && r == er && c == ec) {
				return depth;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nk = k + dirs[d][0];
				int nr = r + dirs[d][1];
				int nc = c + dirs[d][2];
				
				// 탐색 불가 : 범위초과, 이미 방문, 하얀칸
				if (!isIn(nk, nr, nc) || visited[nk][nr][nc] || real_maze[nk][nr][nc] == 0) continue;
			
				q.offer(new Node(nk, nr, nc, depth+1));
				visited[nk][nr][nc] = true;
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	private static boolean isIn(int nk, int nr, int nc) {
		return nk>=0 && nk<N && nr>=0 && nr<N && nc>=0 && nc<N;
	}
	
	static class Node {
		int k, r, c, depth;

		public Node(int k, int r, int c, int depth) {
			this.k = k;
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [k=" + k + ", r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}
	}
}
