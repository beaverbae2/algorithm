package A2020_09_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드와샬 알고리즘
public class BOJ_1719_택배Floyd {
	static int[][] dist;//i -> j 까지 이동하는데 드는 최소비용
	static int[][] next_vertex;//i -> j 까지 이동할때 i바로 다음 접점
	static int N,M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1][N+1];
		next_vertex = new int [N+1][N+1];
	

		//graph 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i!=j) {
					dist[i][j] = 987654321;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			//무방향
			dist[a][b] = Math.min(dist[a][b], cost);
			dist[b][a] = Math.min(dist[b][a], cost);
			
			next_vertex[a][b] = b;
			next_vertex[b][a] = a;
		}
		
		for (int temp = 1; temp < dist.length; temp++) {
			for (int from = 1; from < dist.length; from++) {
				for (int to = 1; to < dist.length; to++) {
					if(dist[from][to]>dist[from][temp]+dist[temp][to]) {
						dist[from][to] = dist[from][temp]+dist[temp][to];
						next_vertex[from][to] = next_vertex[from][temp];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < next_vertex.length; i++) {
			for (int j = 1; j < next_vertex.length; j++) {
				if(i==j) sb.append('-').append(' ');
				else sb.append(next_vertex[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	
	}

}
