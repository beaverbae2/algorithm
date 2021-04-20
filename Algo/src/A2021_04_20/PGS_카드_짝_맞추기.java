package A2021_04_20;

import java.util.*;
/**
 * DFS + BFS
 * 오래 걸린 이유 
 * - DFS 인자값 넘길때 실수
 * - 짝 찾은 후에 빈칸 처리
 * - [중요] ctrl + 방향키 이동 : 가장 가까운 타일을 찾았으면 탐색 종료 해야함 
 * @author beaverbae
 * 
 */
public class PGS_카드_짝_맞추기 {
	int[][] board;
	Pair[][] cards;
	boolean[] visited;
	int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	
	
	int answer = Integer.MAX_VALUE;
	int N;
	
	final int R = 4, C = 4;
	
	
	public int solution(int[][] board, int r, int c) {
		this.board = board;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != 0) N++;
			}
		}
		
		cards = new Pair[N/2+1][2];
		visited = new boolean[N/2+1];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != 0) {
					if (cards[board[i][j]][0] == null) {
						cards[board[i][j]][0] = new Pair(i, j);
					} else if (cards[board[i][j]][1] == null){
						cards[board[i][j]][1] = new Pair(i, j);
					}
				}
			}
		}
		
		dfs(r, c, 0, 0);
		return answer;
	}
	
	
	private void dfs(int r, int c, int removed, int cnt) {
		if (removed == N) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for (int i = 1; i < cards.length; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			
			int r1 = cards[i][0].r;
			int c1 = cards[i][0].c;
			int r2 = cards[i][1].r;
			int c2 = cards[i][1].c;
			
			int d = bfs(r, c, r1, c1);
			d += bfs(r1, c1, r2, c2);
			board[r1][c1] = 0;
			board[r2][c2] = 0; 
			dfs(r2, c2, removed+2, cnt+d);
			board[r1][c1] = i;
			board[r2][c2] = i; 
			
			
			d = bfs(r, c, r2, c2);
			d += bfs(r2, c2, r1, c1);
			board[r1][c1] = 0;
			board[r2][c2] = 0; 
			dfs(r1, c1, removed+2, cnt+d);
			board[r1][c1] = i;
			board[r2][c2] = i; 
			
			
			visited[i] = false;
		}
		
	}


	private int bfs(int sr, int sc, int er, int ec) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		q.offer(new Node(sr, sc, 0));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			
			if (r == er && c == ec) {
				return w + 1;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, w+1));
				}
				
				while (true) {
					if (isIn(nr, nc)) {
						if (board[nr][nc] != 0) {// 중요: 가장 가까운 타일을 찾았으면 종료
							if (!visited[nr][nc]) {
								visited[nr][nc] = true;
								q.offer(new Node(nr, nc, w+1));
							}
							break;
						} 
						nr += dirs[d][0];
						nc += dirs[d][1];
						
						// 가장 가까운 타일이 방문 처리된 경우 다다음이나 그 이상의 타일로 가버림...
//						if (!visited[nr][nc] && board[nr][nc] != 0) {
//							visited[nr][nc] = true;
//							q.offer(new Node(nr, nc, w+1));
//							break;
//						} else {
//							nr += dirs[d][0];
//							nc += dirs[d][1];
//						}
					} else {
						nr = nr - dirs[d][0];
						nc = nc - dirs[d][1];
						
						if (!visited[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new Node(nr, nc, w+1));
						}
						break;
					}
				}
			}
		}
		
		
		return -1;
	}


	private boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
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
		int r, c, w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + "]";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new PGS_카드_짝_맞추기().solution(new int[][] {{1,0,0,3},
																	   {2,0,0,0},
																	   {0,0,0,2},
																	   {3,0,1,0}}, 1, 0));
		System.out.println(new PGS_카드_짝_맞추기().solution(new int[][] {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 0, 1));
//		new A2021_02_05.PGS_카드_짝_맞추기()
	}
}
