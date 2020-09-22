package A2020_09_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드 와샬 알고리즘
public class BOJ_1719_택배Fail {
	static int[][] graph;
	static int[][] path;
	static int[][] next_vertex;
	static int N,M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];
		path = new int[N+1][N+1];
		next_vertex = new int [N+1][N+1];
		
		//graph 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i!=j) {
					graph[i][j] = 987654321;
					path[i][j] = 987654321;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			//무방향
			graph[a][b] = cost;
			graph[b][a] = cost;
			
			path[a][b] = cost;
			path[b][a] = cost;
		
			next_vertex[a][b] = b;
			next_vertex[b][a] = a;
		}
		
		//플로이드 와샬
		for (int k = 1; k <= N; k++) {//중간
			for (int i = 1; i <= N; i++) {//출발
				for (int j = 1; j <= N; j++) {//도착
					if(path[i][k]+path[k][j]<path[i][j]) {
						path[i][j] = path[i][k]+path[k][j];
						if(path[i][k]==graph[i][k]) {
							next_vertex[i][j] = k;
						}
					}
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i==j) answer.append('-').append(' ');
				else answer.append(next_vertex[i][j]).append(' ');
			}
			answer.append('\n');
		}
		
		System.out.println(answer);
		
	}
}
