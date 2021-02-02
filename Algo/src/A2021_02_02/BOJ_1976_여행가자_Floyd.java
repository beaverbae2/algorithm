package A2021_02_02;

import java.util.*;
import java.io.*;

/**
 * 플로이드 와샬
 * @author beaverbae
 *
 */
/*
3
2
0 0 0
0 0 0
0 0 0
1 1
*/
public class BOJ_1976_여행가자_Floyd {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] dist = new int[N+1][N+1];
		final int INF = 987654321;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int v = Integer.parseInt(st.nextToken());
				
				if (v == 1){
					dist[i][j] = v;
				}else dist[i][j] = INF;
			}
		}
		
		////////////// 놓쳤던 부분!!! ///////////////
		for (int i = 1; i <= N; i++) {
			dist[i][i] = 0;// 자기 자신은 연결 되어있음
		}
		///////////////////////////////////////////
		
		// 플로이드 와샬
		for (int k = 1; k <= N; k++) {// 거쳐가는 노드
			for (int i = 1; i <= N; i++) {// 중간 노드
				for (int j = 1; j <= N; j++) {// 도착 노드
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		
		String answer = "YES";
		String[] path = br.readLine().split(" ");
		for (int i = 1; i < path.length; i++) {
			int a = Integer.parseInt(path[i-1]);
			int b = Integer.parseInt(path[i]);
			
			
			if(dist[a][b] == INF) {
				answer = "NO";
			}
		}
		System.out.println(answer);
	}
}
