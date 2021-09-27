package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * @author beave
 * @see https://steady-coding.tistory.com/100
 * 
 */

public class BOJ_10159_저울_ver2 {
	static boolean[][] dist1, dist2;
	static final int INF = 987654321;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist1 = new boolean[N+1][N+1];
		dist2 = new boolean[N+1][N+1];
		
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			dist1[a][b] = true;
			dist2[b][a] = true;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist1[i][k] && dist1[k][j]) {
						dist1[i][j] = true;
					}
					
					if (dist2[i][k] && dist2[k][j]) {
						dist2[i][j] = true;
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dist1[i][j] |= dist2[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				
				if (!dist1[i][j]) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
