package A2020_12_14;

import java.util.*;
import java.io.*;

public class BOJ_15686_치킨배달ver2 {
	static int[][] map;
	static ArrayList<int[]> chicken;
	static LinkedList<int[]> visitedChicken;
	static int N, M;
	static int answer;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visitedChicken = new LinkedList<>();
		chicken = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chicken.add(new int[] {i,j});
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
						dist += getDist(i, j);
					}
				}
			}
			answer = Math.min(answer, dist);
			
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			int r1 = chicken.get(i)[0];
			int c1 = chicken.get(i)[1];
			
			visitedChicken.add(new int[] {r1, c1});
			combi(i+1, r+1);
			visitedChicken.removeLast();
		}
	}

	private static int getDist(int sr, int sc) {
		int dist = Integer.MAX_VALUE;
		for (int[] chicken : visitedChicken) {
			int r = chicken[0];
			int c = chicken[1];
			
			dist = Math.min(dist, Math.abs(sr-r)+Math.abs(sc-c));
		}
		
		return dist;
	}

	
}
