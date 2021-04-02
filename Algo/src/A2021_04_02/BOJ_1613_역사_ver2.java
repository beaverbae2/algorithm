package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * @author beaverbae
 *
 */

public class BOJ_1613_역사_ver2 {
	static boolean[][] dist1, dist2;// 방향 그래프1, 방향 그래프2 (서로 방향 반대)
	static int N, M, K;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist1 = new boolean[N+1][N+1];
		dist2 = new boolean[N+1][N+1];
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			// 서로 반대 방향
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
		
		StringBuilder sb = new StringBuilder();
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (!dist1[a][b] &&! dist2[a][b]) {
				sb.append(0);
			} else if (dist1[a][b]) { 
				sb.append(-1);
			} else if (dist2[a][b]) {
				sb.append(1);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
