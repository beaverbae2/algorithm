package A2020_12_14;

import java.util.*;
import java.io.*;

public class BOJ_15686_치킨배달 {
	static int[][] map;
	static ArrayList<int[]> chicken;
	static boolean[][] visitedChicken;
	static int N, M;
	static int answer;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visitedChicken = new boolean[N][N];
		chicken = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chicken.add(new int[] {i,j});
					map[i][j] = 0;
				}
			}
		}
		
		combi(0,0);
		
		System.out.println(answer);
	}

	private static void combi(int start, int r) {
		if (r == M) {
			int dist = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						dist += bfs(i, j);
					}
				}
			}
			answer = Math.min(answer, dist);
			
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			int r1 = chicken.get(i)[0];
			int c1 = chicken.get(i)[1];
			
			visitedChicken[r1][c1] = true;
			combi(i+1, r+1);
			visitedChicken[r1][c1] = false;
		}
	}

	private static int bfs(int sr, int sc) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int d = elem[2];
			
			if (visitedChicken[r][c]) return d;
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];
				
				if (nr<0||nr>=N||nc<0||nc>=N) continue;
				
				if (!visited[nr][nc]) {
					q.offer(new int[] {nr, nc, d+1});
					visited[nr][nc] = true;
				}
				
			}
		}
		
		return -1;//실제로는 X
	}
}
