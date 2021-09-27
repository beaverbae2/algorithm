package A2021_03_23;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class BOJ_14466_소가_길을_건너간_이유_ver2 {
	static HashSet<String> set;
	static Pair[] cows;
	static boolean[][] isCow;
	static int N, K, R;
	static boolean flag;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static HashSet<String> paths;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		set = new HashSet<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			
			int p1 = N*r1 + c1;
			int p2 = N*r2 + c2;
			
			set.add(p1+" "+p2);
			set.add(p2+" "+p1);
		}
		
		cows = new Pair[K];
		isCow = new boolean[N][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cows[i] = new Pair(r, c);
			isCow[r][c] = true;
		}
		
		int answer = 0;
		paths = new HashSet<>();
		for (int i = 0; i < K; i++) {
			Pair cow1 = cows[i];
			
			int sr = cow1.r;
			int sc = cow1.c;
			bfs(sr, sc);
		}
		
		answer = K*(K-1)/2 - paths.size()/2;
		System.out.println(answer);
	}
	
	private static void bfs(int sr, int sc) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		
		int spos = N*sr + sc;
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
			int pos = N*r + c;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				int npos = N*nr + nc;
				
				if (!isInMap(nr, nc) || visited[nr][nc] || set.contains(pos+" "+npos)) continue;
				
				visited[nr][nc] = true;
				if (isCow[nr][nc]) {
					paths.add(spos+" "+npos);
					paths.add(npos+" "+spos);
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
