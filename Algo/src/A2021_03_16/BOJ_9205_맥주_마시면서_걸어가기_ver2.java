package A2021_03_16;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * @author beaverbae
 *
 */

public class BOJ_9205_맥주_마시면서_걸어가기_ver2 {
	static int[][] dist;
	static List<Pair> spots;
	static final int INF = 987654321;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine())+2;
			dist = new int[N][N];
			spots = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				spots.add(new Pair(r, c));
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					dist[i][j] = INF;
				}
			}
			
			for (int i = 0; i < spots.size(); i++) {
				Pair p1 = spots.get(i);
				
				for (int j = i+1; j < spots.size(); j++) {
					Pair p2 = spots.get(j);
				
					int d = Math.abs(p1.r-p2.r) + Math.abs(p1.c-p2.c);
					
					if (d > 1000) continue;
					
					dist[i][j] = 1;
					dist[j][i] = 1;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
					}
				}
			}
			
			if (dist[0][N-1] != INF) sb.append("happy");
			else sb.append("sad");
			sb.append("\n");
		}
		
		
		System.out.println(sb.toString());
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
