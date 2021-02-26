package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * Floyd - Warshall
 * 11MIN
 * @author beaverbae
 *
 */

public class BOJ_1976_여행가자_review2 {
	static final int INF = 987654321;
	static int[][] dist;
	static int[] path;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
	
		dist = new int[N+1][N+1];
		for (int i = 1; i < dist.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < dist.length; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (i == j) {
					dist[i][j] = 0;
				} else {
					if (n == 0) {
						dist[i][j] = INF;
					} else {
						dist[i][j] = 1;
					}
				}
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		path = new int[M+1];
		for (int i = 1; i < path.length; i++) {
			path[i] = Integer.parseInt(st.nextToken());
		}
		
		// Floyd Warshall
		for (int k = 1; k < dist.length; k++) {
			for (int i = 1; i < dist.length; i++) {
				for (int j = 1; j < dist.length; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		if (check()) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static boolean check() {
		for (int i = 2; i < path.length; i++) {
			if (dist[path[i-1]][path[i]] == INF) return false; 
		}
		return true;
	}
}
