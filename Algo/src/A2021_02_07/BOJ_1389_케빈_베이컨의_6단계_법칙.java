package A2021_02_07;

import java.util.*;
import java.io.*;

public class BOJ_1389_케빈_베이컨의_6단계_법칙 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dist = new int[N+1][N+1];
		final int INF = 987654321;
		for (int i = 1; i < dist.length; i++) {
			for (int j = 1; j < dist.length; j++) {
				if (i == j) continue;
				
				dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			dist[a][b] = 1;
			dist[b][a] = 1;
		}
		
		//Floyd-Warshall
		for (int k = 1; k < dist.length; k++) {
			for (int i = 1; i < dist.length; i++) {
				for (int j = 1; j < dist.length; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int answer = 0;
		int min = INF;
		for (int i = 1; i < dist.length; i++) {
			int temp = 0;
			
			for (int j = 1; j < dist.length; j++) {
				if (i == j) continue;
				temp += dist[i][j];
			}
			if (min > temp) {
				min = temp;
				answer = i;
			}
		}
		
		System.out.println(answer);
	}
}
