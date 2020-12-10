package A2020_12_09;

import java.util.*;

/*
 * 다익스트라 알고리즘으로는 안되나?
 */
public class PGS_경주로건설 {
	public static int solution(int[][] board) {
        int answer = bfs(board);
        return answer;
    }
	
	private static int bfs(int[][] board) {
		int[][] dirs = {{0,0},{1,0},{0,1},{-1,0},{0,-1}};
		int N = board.length;
		int[][] visited = new int[N][N];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		//초기화
		Queue<Elem> q = new LinkedList<>();
		q.offer(new Elem(0, 0, 0, 0));
		visited[0][0] = 0;
		
		while(!q.isEmpty()) {
			Elem e = q.poll();
			int r = e.r;
			int c = e.c;
			int cost = e.cost;
			int direction = e.direction;
			
			for (int d = 1; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if(nr<0||nr>=N||nc<0||nc>=N||board[nr][nc]==1) continue;
				
				if(direction==0||(direction%2)==(d%2)) {//직선 100
					if(visited[nr][nc]>=cost+100) {
						visited[nr][nc] = cost+100;
						q.offer(new Elem(nr, nc, cost+100, d));
					}
				}else if((direction%2)!=(d%2)) {//코너 600
					if(visited[nr][nc]>=cost+600) {
						visited[nr][nc] = cost+600;
						q.offer(new Elem(nr, nc, cost+600, d));
					}
				}
			}
			
		}
		
		
		return visited[N-1][N-1];
	}
	
	static class Elem{
		int r,c,cost,direction;

		public Elem(int r, int c, int cost, int direction) {
			this.r = r;
			this.c = c;
			this.cost = cost;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", cost=" + cost + ", direction=" + direction + "]";
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
		System.out.println(solution(new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
		System.out.println(solution(new int[][] {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}}));
		System.out.println(solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}));
	}
}
