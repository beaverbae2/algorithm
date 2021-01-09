package A2021_01_09;

import java.util.*;
import java.io.*;

public class BOJ_16197_두_동전 {
	static int N, M;
	static char[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static HashMap<String, Boolean> visited;
	static int sr1 = -1, sc1 = -1, sr2, sc2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'o') {
					if(sr1 == -1 && sc1 == -1) {
						sr1 = i;
						sc1 = j;
					}else {
						sr2 = i;
						sc2 = j;
					}
				}
			}
			
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		visited = new HashMap<>();
		q.offer(new Node(sr1, sc1, sr2, sc2, 0));
		visited.put(getKey(sr1, sc1, sr2, sc2), true);
	
		while(!q.isEmpty()) {
			Node node = q.poll();
			int r1 = node.r1;
			int c1 = node.c1;
			int r2 = node.r2;
			int c2 = node.c2;
			int depth = node.depth;
			
			if (depth >= 10) return -1;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr1 = r1+dirs[d][0];
				int nc1 = c1+dirs[d][1];
				int nr2 = r2+dirs[d][0];
				int nc2 = c2+dirs[d][1];
				
				if (isInMap(nr1, nc1) && isInMap(nr2, nc2)) {
					if (map[nr1][nc1] == '#') {//벽 인경우
						nr1 = r1;
						nc1 = c1;
					}
					if (map[nr2][nc2] == '#') {//벽 인경우
						nr2 = r2;
						nc2 = c2;
					}
					
					String key = getKey(nr1, nc1, nr2, nc2);
					if (visited.get(key) == null) {
						visited.put(key, true);
						q.offer(new Node(nr1, nc1, nr2, nc2, depth+1));
					}
				}else if(isInMap(nr1, nc1) || isInMap(nr2, nc2)) {
					return depth+1;
				}
			}
		}
		return -1;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}
	
	private static String getKey(int r1, int c1, int r2, int c2) {
		return r1+" "+c1+" "+r2+" "+c2;
	}
	
	static class Node {
		int r1, c1, r2, c2, depth;

		public Node(int r1, int c1, int r2, int c2, int depth) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r1=" + r1 + ", c1=" + c1 + ", r2=" + r2 + ", c2=" + c2 + ", depth=" + depth + "]";
		}
	}
}
