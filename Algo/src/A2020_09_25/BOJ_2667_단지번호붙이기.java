package A2020_09_25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class BOJ_2667_단지번호붙이기 {
	static boolean[][] map;
	static int N,dangiNum;
	static LinkedList<Integer> dangies = new LinkedList<>();
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				if(str.charAt(j)=='0') map[i][j] = true; 
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!map[i][j]) {
					map[i][j] = true;
					dangies.add(dfs(i,j));
					dangiNum++;
				}
			}
		}
		sb.append(dangiNum).append('\n');
		Collections.sort(dangies);
		for (int dangi : dangies) {
			sb.append(dangi).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int dfs(int r, int c) {
		int n = 1;
		
		for (int i = 0; i < dirs.length; i++) {
			int nr = r+dirs[i][0];
			int nc = c+dirs[i][1];
			
			if(nr<0||nr>=N||nc<0||nc>=N||map[nr][nc]) continue;
		
			map[nr][nc] = true;
			n+=dfs(nr,nc);
		}
		
		return n;
	}
}
