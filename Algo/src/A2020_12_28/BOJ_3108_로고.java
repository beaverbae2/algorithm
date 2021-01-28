package A2020_12_28;

import java.util.*;
import java.io.*;

public class BOJ_3108_로고 {
	static boolean[][] map;
	static int N;
	static boolean[][] visited;
	static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	static Map<String, Boolean> hm;
	
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
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[1001][1001];
		visited = new boolean[1001][1001];
		hm = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken())+500;
			int c1 = Integer.parseInt(st.nextToken())+500;
			int r2 = Integer.parseInt(st.nextToken())+500;
			int c2 = Integer.parseInt(st.nextToken())+500;
			
			
			for (int r = r1; r <= r2; r++) {
				map[r][c1] = true;
				map[r][c2] = true;
			}
			
			for (int r = r1; r < r2; r++) {
				hm.put(r+" "+c1+" "+(r+1)+" "+c1, true);
				hm.put((r+1)+" "+c1+" "+r+" "+c1, true);
				hm.put(r+" "+c2+" "+(r+1)+" "+c2, true);
				hm.put((r+1)+" "+c2+" "+r+" "+c2, true);
			}
			
			for (int c = c1; c <= c2; c++) {
				map[r1][c] = true;
				map[r2][c] = true;
			}
			
			for (int c = c1; c < c2; c++) {
				hm.put(r1+" "+c+" "+r1+" "+(c+1), true);
				hm.put(r1+" "+(c+1)+" "+r1+" "+c, true);
				hm.put(r2+" "+c+" "+r2+" "+(c+1), true);
				hm.put(r2+" "+(c+1)+" "+r2+" "+c, true);
			}
		}
		
		int answer = 0;
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(!visited[r][c]&&map[r][c]) {
					answer += bfs(r,c);
				}
			}
		}
		
		if(map[500][500]) answer--;
//		bfs(501, 501);
		System.out.println(answer);
	}

	private static int bfs(int sr, int sc) {
//		System.out.println(sr+", "+sc);
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;
		
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (nr<0||nr>1000||nc<0||nc>1000||visited[nr][nc]||hm.get(r+" "+c+" "+nr+" "+nc)==null) continue;
				
				if(hm.get(r+" "+c+" "+nr+" "+nc)) {
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		
		return 1;
	}
}
