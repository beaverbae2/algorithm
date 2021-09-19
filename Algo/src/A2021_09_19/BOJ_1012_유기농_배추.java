package A2021_09_19;

import java.util.*;
import java.io.*;

/**
 * 11MIN
 * DFS
 * @author beaverbae
 * DFS, BFS 입문 문제
 */

public class BOJ_1012_유기농_배추 {
	static int M, N, K;
	static boolean[][] visited;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			int ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], true);
			}
			
			while (K-- > 0) {
				st = new StringTokenizer(br.readLine());
				int j = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				visited[i][j] = false;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) continue;
					ans++;
					dfs(i, j);
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int d = 0; d < dirs.length; d++) {
			int nx = x + dirs[d][0];
			int ny = y + dirs[d][1];
		
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
			dfs(nx, ny);
		}
	}
}
