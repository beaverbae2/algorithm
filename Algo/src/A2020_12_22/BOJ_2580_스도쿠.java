package A2020_12_22;

import java.util.*;
import java.io.*;

public class BOJ_2580_스도쿠 {
	static int[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};//8방 좌표
	static int N;
	static List<Pair> list;
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		list = new ArrayList<>();
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) list.add(new Pair(i, j));
			}
		}
		N = list.size();
		dfs(0);
	}

	private static void dfs(int k) {
		if (k == N) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		boolean[] visited = new boolean[10];//1~9
		int r = list.get(k).r;
		int c = list.get(k).c;
		check(visited, r, c);
		
		for (int i = 1; i < visited.length; i++) {
			if (visited[i]) continue;
			
			map[r][c] = i;
			dfs(k+1);
			map[r][c] = 0;//반드시 해줘야함
		}
	}



	private static void check(boolean[] visited, int sr, int sc) {
		//가로
		for (int i = 0; i < map.length; i++) {
			visited[map[sr][i]] = true;
		}
		
		//세로
		for (int i = 0; i < map.length; i++) {
			visited[map[i][sc]] = true;
		}
		
		//3X3
		int r = 3*(sr/3) + 1;
		int c = 3*(sc/3) + 1;
		
		visited[map[r][c]] = true;
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			visited[map[nr][nc]] = true;
		}
	}
}
