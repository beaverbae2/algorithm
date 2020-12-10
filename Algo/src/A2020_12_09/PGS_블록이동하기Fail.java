package A2020_12_09;

import java.util.HashMap;
import java.util.PriorityQueue;

public class PGS_블록이동하기Fail {
	static int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};//오른쪽, 아래
	
	public static int solution(int[][] board) {
    	int answer = bfs(board);
        return answer;
    }
	
	private static int bfs(int[][] board) {
		PriorityQueue<Elem> pq = new PriorityQueue<>();
		pq.add(new Elem(0, 0, 0, 1, 0 , false));
		
		int N = board.length;
//		int[][] visited1 = new int[N][N];
//		int[][] visited2 = new int[N][N];
//		for (int i = 0; i < N; i++) {
//			Arrays.fill(visited1[i], Integer.MAX_VALUE);
//			Arrays.fill(visited2[i], Integer.MAX_VALUE);
//		}
//		visited1[0][0] = 0;
//		visited2[0][1] = 0;
		HashMap<String, Integer> visited = new HashMap<>();
		
		while(!pq.isEmpty()) {
			Elem e = pq.poll();
			int r1 = e.r1;
			int r2 = e.r2;
			int c1 = e.c1;
			int c2 = e.c2;
			int depth = e.depth;
			boolean isVertical = e.isVertical;
			
			System.out.println(e);
			System.out.println(pq);
			System.out.println();
			
			if((r1==N-1&&c1==N-1)||(r2==N-1&&c2==N-1)) {
				return depth;
			}
			
			//이동
			for (int d = 0; d < dirs.length; d++) {
				int nr1 = r1+dirs[d][0];
				int nc1 = c1+dirs[d][1];
				int nr2 = r2+dirs[d][0];
				int nc2 = c2+dirs[d][1];
			
				if(isInMap(nr1,nc1,nr2,nc2,N)) {
					if(board[nr1][nc1]==0&&board[nr2][nc2]==0) {
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
			}
			
			if(!isVertical) {
				
				if(isInMap(r1-1, c1+1, N)&&isInMap(r1-1, c1, N)) {
					if(board[r1-1][c1+1]==0 && board[r1-1][c1]==0) {
						int nr1 = r1-1;
						int nc1 = c1+1;
						int nr2 = r2;
						int nc2 = c2;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				if(isInMap(r1+1, c1+1, N)&&isInMap(r1+1, c1, N)) {
					if(board[r1+1][c1+1]==0 && board[r1+1][c1]==0) {
						int nr1 = r1+1;
						int nc1 = c1+1;
						int nr2 = r2;
						int nc2 = c2;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				if(isInMap(r2-1, c2-1, N)&&isInMap(r2-1, c2, N)) {
					if(board[r2-1][c2-1]==0 && board[r2-1][c2]==0) {
						int nr1 = r2-1;
						int nc1 = c2-1;
						int nr2 = r1;
						int nc2 = c1;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				if(isInMap(r2+1, c2-1, N)&&isInMap(r2+1, c2, N)) {
					if(board[r2+1][c2-1]==0 && board[r2+1][c2]==0) {
						int nr1 = r1;
						int nc1 = c1;
						int nr2 = r2+1;
						int nc2 = c2-1;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				
			}else {
				if(isInMap(r1+1, c1-1, N)&&isInMap(r1, c1-1, N)) {
					if(board[r1+1][c1-1]==0 && board[r1][c1-1]==0) {
						int nr1 = r1+1;
						int nc1 = c1-1;
						int nr2 = r2;
						int nc2 = c2;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				if(isInMap(r1+1, c1+1, N)&&isInMap(r1, c1+1, N)) {
					if(board[r1+1][c1+1]==0 && board[r1][c1+1]==0) {
						int nr1 = r2;
						int nc1 = c2;
						int nr2 = r1+1;
						int nc2 = c1+1;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				if(isInMap(r2-1, c2-1, N)&&isInMap(r2, c2-1, N)) {
					if(board[r2-1][c2-1]==0 && board[r2][c2-1]==0) {
						int nr1 = r2-1;
						int nc1 = c2-1;
						int nr2 = r1;
						int nc2 = c1;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
				
				if(isInMap(r2-1, c2+1, N)&&isInMap(r2, c2+1, N)) {
					if(board[r2-1][c2+1]==0 && board[r2][c2+1]==0) {
						int nr1 = r1;
						int nc1 = c1;
						int nr2 = r2-1;
						int nc2 = c2+1;
						
						String str = "r1"+Integer.toString(nr1)+"c1"+Integer.toString(nc1)+"r2"+Integer.toString(nr2)+"c2"+Integer.toString(nc2);
						if(visited.get(str) == null) {
							pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
							visited.put(str, depth+1);
						}else {
							int prev_depth = visited.get(str);
							if(prev_depth>depth+1) {
								pq.add(new Elem(nr1, nc1, nr2, nc2, depth+1, !isVertical));
								visited.put(str, depth+1);
							}
						}
					}
				}
			}
			
			 

		}
		return 0;//실제로는 진행X
	}
	
	private static boolean isInMap(int nr, int nc, int N) {
		return nr>=0&&nr<N&&nc>=0&&nc<N;
	}

	private static boolean isInMap(int nr1, int nc1, int nr2, int nc2, int N) {
		return nr1>=0&&nr1<N&&nr2>=0&&nr2<N&&nc1>=0&&nc1<N&&nc2>=0&&nc2<N;
	}

	static class Elem implements Comparable<Elem>{
		int r1,c1,r2,c2,depth;
		boolean isVertical;

		

		public Elem(int r1, int c1, int r2, int c2, int depth, boolean isVertical) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.depth = depth;
			this.isVertical = isVertical;
		}

		@Override
		public String toString() {
			return "Elem [r1=" + r1 + ", c1=" + c1 + ", r2=" + r2 + ", c2=" + c2 + ", depth=" + depth + ", isVertical="
					+ isVertical + "]";
		}

		@Override
		public int compareTo(Elem o) {
			return Integer.compare(this.depth, o.depth);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}}));
	}
	
}
