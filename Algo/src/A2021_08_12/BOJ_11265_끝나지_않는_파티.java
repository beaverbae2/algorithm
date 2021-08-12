package A2021_08_12;

import java.util.*;
import java.io.*;

/**
 * Floyd-Warshall
 * 9MIN
 * @author beaverbae
 *
 */

public class BOJ_11265_끝나지_않는_파티 {
	static int N, M;
	static int[][] dist;
	static final String YES = "Enjoy other party";
	static final String NO = "Stay here";
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		dist = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			if (dist[a][b] <= w) {
				sb.append(YES);
			} else {
				sb.append(NO);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
