package A2020_09_23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//dp
public class BOJ_1520_내리막길 {
	static int[][] map;
	static int[][] dp;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int R,C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		dp = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0,0));
	}
	
	private static int dfs(int r, int c) {
		if(dp[r][c]!=-1) return dp[r][c];
		
		if(r==R-1&&c==C-1) return 1;
		
		dp[r][c] = 0;
		
		for (int i = 0; i < dirs.length; i++) {
			int nr = r+dirs[i][0];
			int nc = c+dirs[i][1];
			
			if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
			
			if(map[r][c]>map[nr][nc]) {
				dp[r][c] += dfs(nr,nc);
			}
		}
		return dp[r][c];
	}
}
