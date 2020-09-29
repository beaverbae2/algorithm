package A2020_09_28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_D4_1259_보급로 {
	static int [][] map;
	static int[][] dist;
	static int N;
	static int INF = 987654321;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], INF);
			}

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j)-'0';
				}
			}
			bfs();
			sb.append(dist[N-1][N-1]).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,0});
		dist[0][0] = 0;
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int cost = elem[2];
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
			
				if(nr<0||nr>=N||nc<0||nc>=N||dist[nr][nc]<cost+map[nr][nc]) continue;
				
				if(dist[nr][nc]>cost+map[nr][nc]) {
					dist[nr][nc] = cost+map[nr][nc];
					q.offer(new int[] {nr,nc,dist[nr][nc]});
				}
			}
		}
	}
}
