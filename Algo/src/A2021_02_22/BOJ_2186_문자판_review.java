package A2021_02_22;

import java.util.*;
import java.io.*;

/**
 * dp배열에 index도 포함해야함!!
 * DFS + Memoization
 * @author beaverbae
 *
 */

public class BOJ_2186_문자판_review {
	static char[][] map;
	static int R, C ,K, N;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static char[] target;
	static int answer;
	static int[][][] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		target = br.readLine().toCharArray();
		N = target.length;
		
		memo = new int[R][C][N];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != target[0]) continue;
				
				answer += dfs(i, j, 0);
			}
		}
		
		System.out.println(answer);
	}
	
	static int dfs(int r, int c, int cnt) {
		if (cnt == N-1) {
			return memo[r][c][cnt] = 1;
		}
		
		if (memo[r][c][cnt] != -1) return memo[r][c][cnt];
		
		memo[r][c][cnt] = 0;
	
		for (int k = 1; k <= K; k++) {
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + k*dirs[d][0];
				int nc = c + k*dirs[d][1];
				
				if (!isInMap(nr, nc) || map[nr][nc] != target[cnt+1]) continue;
				
				memo[r][c][cnt] += dfs(nr, nc, cnt+1);
			}
		}
		
		return memo[r][c][cnt];
	}
	
	static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
