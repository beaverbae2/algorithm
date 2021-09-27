package A2021_03_23;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class BOJ_14466_소가_길을_건너간_이유_ver4 {
	static Pair[] cows;
	static List<Integer>[] roads;
	static int[][] cow_num;
	static int N, K, R;
	static boolean flag;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[][] isAnswer;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		roads = new List[N*N];
		for (int i = 0; i < roads.length; i++) {
			roads[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			
			int p1 = N*r1 + c1;
			int p2 = N*r2 + c2;
			
			roads[p1].add(p2);
			roads[p2].add(p1);
		}
		
		cows = new Pair[K];
		cow_num = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(cow_num[i], -1);
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cows[i] = new Pair(r, c);
			cow_num[r][c] = i;
		}
		
		isAnswer = new boolean[K+1][K+1];
		for (int i = 0; i < K; i++) {
			Pair cow = cows[i];
			
			int sr = cow.r;
			int sc = cow.c;
			bfs(sr, sc);
		}
		
		answer = K*(K-1)/2 - answer/2;
		System.out.println(answer);
	}
	
	private static void bfs(int sr, int sc) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		
		int scow = cow_num[sr][sc];
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
			int pos = N*r + c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				int npos = N*nr + nc;
				boolean isRoad = false;
				
				if (!isInMap(nr, nc) || visited[nr][nc]) continue;
				for (int i = 0; i < roads[pos].size(); i++) {
					int next_pos = roads[pos].get(i);
					if (npos == next_pos) {
						isRoad = true;
						break;
					}
				}
				
				if (isRoad) continue;
				
				visited[nr][nc] = true;
				int dcow = cow_num[nr][nc];
				if (dcow != -1 && !isAnswer[scow][dcow]) {
					isAnswer[scow][dcow] = true;
					isAnswer[dcow][scow] = true;
					answer+=2;
				}
				q.offer(new Pair(nr, nc));
			}
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
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
}
