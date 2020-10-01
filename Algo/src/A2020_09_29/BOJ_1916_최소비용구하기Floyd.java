package A2020_09_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용구하기Floyd {
	static int[][] dist;
	static int N,M,start,end;
	static int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1][N+1];
		for (int i = 1; i < dist.length; i++) {
			for (int j = 1; j < dist.length; j++) {
				if(i==j) continue;
				dist[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//단방향이었다....
			dist[a][b] = Math.min(dist[a][b], cost);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		for (int temp = 1; temp < dist.length; temp++) {
			for (int from = 1; from < dist.length; from++) {
				for (int to = 1; to < dist.length; to++) {
					if(dist[from][to] > dist[from][temp]+dist[temp][to]) {
						dist[from][to] = dist[from][temp]+dist[temp][to];
					}
				}
			}
		}
		
		System.out.println(dist[start][end]);
	}
}
