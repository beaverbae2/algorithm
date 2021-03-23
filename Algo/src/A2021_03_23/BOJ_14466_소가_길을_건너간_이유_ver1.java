package A2021_03_23;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 53MIN
 * @author beaverbae
 *
 */

public class BOJ_14466_소가_길을_건너간_이유_ver1 {
	static HashSet<String> set;
	static boolean[][] visited;
	static Pair[] cows;
	static int N, K, R;
	static int dr, dc;
	static boolean flag;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
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
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cows[i] = new Pair(r, c);
		}
		
		int answer = 0;
		
		// KC2
		for (int i = 0; i < K; i++) {
			Pair cow1 = cows[i];
			
			for (int j = i+1; j < K; j++) {
				Pair cow2 = cows[j];
				
				visited = new boolean[N][N];
				int sr = cow1.r;
				int sc = cow1.c;
				dr = cow2.r;
				dc = cow2.c;
				flag = false;
				
				visited[sr][sc] = true;
				
				dfs(sr, sc);
				
				if (!flag) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int r, int c) {
		if (flag) return;
		
		int p = N*r + c;
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			int np = N*nr + nc;
			
			if (!isInMap(nr, nc) || visited[nr][nc] || set.contains(p+" "+np)) continue;
			
			if (nr == dr && nc == dc) {
				flag = true;
				return;
			}
			
			visited[nr][nc] = true;
			dfs(nr, nc);
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
