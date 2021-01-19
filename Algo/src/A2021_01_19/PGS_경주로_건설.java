package A2021_01_19;

import java.util.*;
/**
 * BFS
 * @author beaverbae
 *
 */

public class PGS_경주로_건설 {
	private int[][] dirs = {{-1, 0},{0, 1},{0, -1},{1, 0}};// 북, 동, 서, 남
	private final int INF = 987654321;
	private int N;
	public int solution(int[][] board) {
        int answer = 0;
        
        N = board.length;
        int[][] cost = new int[N][N];// cost[i][j] : (0, 0) -> (i, j) 까지의 최소 비용 
        for (int i = 0; i < cost.length; i++) {
			Arrays.fill(cost[i], INF);
		}
        
        
        // 초기화
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0,-1));
        cost[0][0] = 0;
        
        // 탐색 진행
        while (!q.isEmpty()) {
        	Node node = q.poll();
        	int r = node.r;
        	int c = node.c;
        	int w = node.w;
        	int dir = node.dir;
        	
        	for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isInMap(nr, nc) || board[nr][nc] == 1) continue;
			
				// 처음
				if (dir < 0) {
					if (cost[nr][nc] >= w + 100) {// 직선
						cost[nr][nc] = w + 100;
						q.offer(new Node(nr, nc, w+100 ,d));
					}
				}else {
					if (dir == d) {// 직선
						if (cost[nr][nc] >= w + 100) {
							cost[nr][nc] = w + 100;
							q.offer(new Node(nr, nc, w+100, d));
						}
					}else if (dir + d != 3) {// 코너
						if (cost[nr][nc] >= w + 600) {
							cost[nr][nc] = w + 600;
							q.offer(new Node(nr, nc, w+600, d));
						}
					}
				}
        	}
        }

        
        answer = cost[N-1][N-1];
        return answer;
    }
	
	public boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
	
	static class Node {
		int r, c , w, dir;

		public Node(int r, int c, int w, int dir) {
			this.r = r;
			this.c = c;
			this.w = w;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + ", dir=" + dir + "]";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new PGS_경주로_건설().solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
		System.out.println(new PGS_경주로_건설().solution(new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
		System.out.println(new PGS_경주로_건설().solution(new int[][] {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}}));
		System.out.println(new PGS_경주로_건설().solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}));
	}
}
