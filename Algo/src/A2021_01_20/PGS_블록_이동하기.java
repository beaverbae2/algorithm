package A2021_01_20;

import java.util.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class PGS_블록_이동하기 {
	private int N;
	
	public int solution(int[][] board) {
		N = board.length;
        int answer = bfs(board);
        return answer;
    }
	
	private int bfs(int[][] board) {
		Queue<Node> q = new LinkedList<>();
    	HashSet<String> visited = new HashSet<>();
    	int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    	int[][] dirs2 = new int[][] {{-1,0},{-1,1},{0,0},{0,1}};
    	int[][] dirs3 = new int[][] {{0,-1},{0,0},{1,-1},{1,0}};
    	int[][] dirs4 = new int[][] {{-1,0},{-1,-1},{1,0},{1,-1}};// 대각선
    	int[][] dirs5 = new int[][] {{0,-1},{0,1},{-1,-1},{-1,1}};// 대각선 
    	
    	
    	q.offer(new Node(0, 1, 0, false));
    	visited.add("0"+" "+"0"+" "+"0"+" "+"1");
    	
    	while(!q.isEmpty()) {
    		Node node = q.poll();
    		int r1 = -1;
    		int c1 = -1;
    		int r2 = node.r;
    		int c2 = node.c;
    		int r3 = r2;
			int c3 = c2;
    		int depth = node.depth;
    		boolean isVertical = node.isVertical;

    		if (isVertical) {// 수직
    			r1 = r2-1;
    			c1 = c2;

    			for (int d = 0; d < dirs.length; d++) {
					int nr1 = r1 + dirs3[d][0];
					int nc1 = c1 + dirs3[d][1];
					
					int nr2 = r2 + dirs2[d][0];
					int nc2 = c2 + dirs2[d][1];
					
					int nr3 = r3 + dirs5[d][0];
					int nc3 = c3 + dirs5[d][1];
					
					if (!isInMap(nr3, nc3)) continue;
					if (board[nr3][nc3] == 1) continue;
					if (!isInMap(nr1, nc1, nr2, nc2)) continue;
					if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;
					if (visited.contains(nr1+" "+nc1+" "+nr2+" "+nc2)) continue;
					
					if (nr2 == N-1 && nc2 == N-1) {
						return depth+1;
					}
					
					visited.add(nr1+" "+nc1+" "+nr2+" "+nc2);
					q.offer(new Node(nr2, nc2, depth+1, !isVertical));
	    		
    			}
    		}else {// 수평
    			r1 = r2;
    			c1 = c2-1;
    			
    			for (int d = 0; d < dirs.length; d++) {
					int nr1 = r1 + dirs2[d][0];
					int nc1 = c1 + dirs2[d][1];
					
					int nr2 = r2 + dirs3[d][0];
					int nc2 = c2 + dirs3[d][1];
					
					int nr3 = r3 + dirs4[d][0];
					int nc3 = c3 + dirs4[d][1];
					
					if (!isInMap(nr3, nc3)) continue;
					if (board[nr3][nc3] == 1) continue;
					if (!isInMap(nr1, nc1, nr2, nc2)) continue;
					if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;
					if (visited.contains(nr1+" "+nc1+" "+nr2+" "+nc2)) continue;
	    		
					if (nr2 == N-1 && nc2 == N-1) {
						return depth+1;
					}
					
					visited.add(nr1+" "+nc1+" "+nr2+" "+nc2);
					q.offer(new Node(nr2, nc2, depth+1, !isVertical));
	    		
    			}
    		}
    		
    		// 사방
    		for (int d = 0; d < dirs.length; d++) {
				int nr1 = r1 + dirs[d][0];
				int nc1 = c1 + dirs[d][1];
				int nr2 = r2 + dirs[d][0];
				int nc2 = c2 + dirs[d][1];
			
				if (!isInMap(nr1, nc1, nr2, nc2)) continue;
				if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;
				if (visited.contains(nr1+" "+nc1+" "+nr2+" "+nc2)) continue;
    		
				if (nr2 == N-1 && nc2 == N-1) {
					return depth+1;
				}
				
				visited.add(nr1+" "+nc1+" "+nr2+" "+nc2);
				q.offer(new Node(nr2, nc2, depth+1, isVertical));
    		}
    	}
		
		return 0; // 실제로는 도달X
	}

	public boolean isInMap(int nr1, int nc1, int nr2, int nc2) {
		return nr1>=0 && nr1<N && nc1>=0 && nc1<N && nr2>=0 && nr2<N && nc2>=0 && nc2<N;
	}
	
	public boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
	
	static class Node {
		int r, c, depth;
		boolean isVertical;
		
		public Node(int r, int c, int depth, boolean isVertical) {
			this.r = r;
			this.c = c;
			this.depth = depth;
			this.isVertical = isVertical;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + ", isVertical=" + isVertical + "]";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new PGS_블록_이동하기().solution(new int[][] {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}}));
	}
}
