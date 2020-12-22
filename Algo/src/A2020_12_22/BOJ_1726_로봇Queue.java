package A2020_12_22;

import java.util.*;
import java.io.*;

public class BOJ_1726_로봇PQ {
	static int[][] map;
	static int R, C;
	static int sr, sc, sdir;
	static int er, ec, edir;
	static int[][] dirs = {{0,0},{0,1},{0,-1},{1,0},{-1,0}};//동서남북
	static int[][][] visited;
	
	static class Elem implements Comparable<Elem>{
		int r, c, dir, depth;

		public Elem(int r, int c, int dir, int depth) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", dir=" + dir + ", depth=" + depth + "]";
		}

		@Override
		public int compareTo(Elem o) {
			return Integer.compare(this.depth, o.depth);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		sdir = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		edir = Integer.parseInt(st.nextToken());
	
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Elem> q = new LinkedList<>();
		q.offer(new Elem(sr, sc, sdir, 0));
		
		visited = new int[5][R][C];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		visited[sdir][sr][sc] = 0;
		
		while(!q.isEmpty()){
			Elem e = q.poll();
			int r = e.r;
			int c = e.c;
			int dir = e.dir;
			int depth = e.depth;//명렬 개수
			
			if (visited[dir][r][c] < depth) continue;
		
			//왼
			int next_dir = dir;
			int next_depth = depth;
			int cnt = 0;
			while(true) {
				if(cnt == 2) break;
				
				for (int k = 1; k <= 3; k++) {
					int nr = r + k*dirs[next_dir][0];
					int nc = c + k*dirs[next_dir][1];
					
					if (isInMap(nr, nc)&&visited[next_dir][nr][nc] >= next_depth+1&&map[nr][nc] == 0) {
						q.offer(new Elem(nr, nc, next_dir, next_depth+1));
						visited[next_dir][nr][nc] = next_depth+1;
					}else break;
				}
				
				next_dir = turnLeft(next_dir);
				next_depth++;
				if (visited[next_dir][r][c]>=next_depth) visited[next_dir][r][c] = next_depth;
				cnt++;
			}
			
			//오
			next_dir = dir;
			next_depth = depth;
			cnt = 0;
			while(true) {
				next_dir = turnRight(next_dir);
				next_depth++;
				cnt++;
				
				if(cnt == 3) break;
				
				for (int k = 1; k <= 3; k++) {
					int nr = r + k*dirs[next_dir][0];
					int nc = c + k*dirs[next_dir][1];
					
					if (isInMap(nr, nc)&&visited[next_dir][nr][nc] >= next_depth+1&&map[nr][nc] == 0) {
						q.offer(new Elem(nr, nc, next_dir, next_depth+1));
						visited[next_dir][nr][nc] = next_depth+1;
					}else break;
				}
				
				if (visited[next_dir][r][c]>=next_depth) visited[next_dir][r][c] = next_depth;
			}
		}
		
		
		return visited[edir][er][ec];
	}

	private static int turnLeft(int dir) {
		switch (dir) {
		case 1:
			dir = 4;
			break;
		case 2:
			dir = 3;
			break;
		case 3:
			dir = 1;
			break;
		case 4:
			dir = 2;
			break;
		default:
			break;
		}
		return dir;
	}
	
	private static int turnRight(int dir) {
		switch (dir) {
		case 1:
			dir = 3;
			break;
		case 2:
			dir = 4;
			break;
		case 3:
			dir = 2;
			break;
		case 4:
			dir = 1;
			break;
		default:
			break;
		}
		return dir;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}