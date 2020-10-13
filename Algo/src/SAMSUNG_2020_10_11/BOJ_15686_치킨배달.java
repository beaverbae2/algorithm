package SAMSUNG_2020_10_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//34min

public class BOJ_15686_치킨배달 {
	static int[][] map;
	static int N,M;
	static boolean[] visited;
	static List<Pair> chicken_houses;
	static int answer;
	
	static class Pair{
		int r,c;

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;
		map = new int[N][N];
		chicken_houses = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					chicken_houses.add(new Pair(i, j));
				}
			}
		}
		visited = new boolean[chicken_houses.size()];
	
		makeCombination(0,0);
		System.out.println(answer);
	}

	private static void makeCombination(int start,int r) {
		if(r==M) {
			answer = Math.min(answer, getMinChickenDistance()); 
			return;
		}
		
		for (int i = start; i < chicken_houses.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				makeCombination(i+1, r+1);
				visited[i] = false;
			}
		}
	}

	private static int getMinChickenDistance() {
		int min_distance = 0;
		for (int r1 = 0; r1 < N; r1++) {
			for (int c1 = 0; c1 < N; c1++) {
				if(map[r1][c1]==1) {
					int min_dist1 = Integer.MAX_VALUE;
					for (int i = 0; i < chicken_houses.size(); i++) {
						if(!visited[i]) continue;
						int r2 = chicken_houses.get(i).r;
						int c2 = chicken_houses.get(i).c;
						
						int d = Math.abs(r1-r2)+Math.abs(c1-c2);
						min_dist1 = Math.min(min_dist1, d);
					}
					min_distance += min_dist1;
				}
			}
		}
		
		return min_distance;
	}
}
