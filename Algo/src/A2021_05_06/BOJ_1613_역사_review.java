package A2021_05_06;

import java.util.*;
import java.io.*;

public class BOJ_1613_역사_review {
	static boolean[][] dist;
	static int N, K, S;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		dist = new boolean[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			dist[a][b] = true;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] |= (dist[i][k] & dist[k][j]);
				}
			}
		}
		
		sb = new StringBuilder();
		S = Integer.parseInt(br.readLine());
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (dist[a][b]) {
				if (dist[b][a]) {
					sb.append(0);
				} else {
					sb.append(-1);
				}
			} else {
				if (dist[b][a]) {
					sb.append(1);
				} else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
