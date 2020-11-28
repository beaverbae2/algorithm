package A2020_11_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_2667_단지번호붙이기_DFS {
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int totalHomeCnt;
	static ArrayList<Integer> homeCntList;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		homeCntList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]&&map[i][j]==1) {
					homeCntList.add(dfs(i,j));
					totalHomeCnt++;
				}
			}
		}
		
		Collections.sort(homeCntList);
		StringBuilder sb = new StringBuilder();
		sb.append(totalHomeCnt).append("\n");
		for(int homeCnt : homeCntList) {
			sb.append(homeCnt).append("\n");
		}
		System.out.println(sb);
	}

	private static int dfs(int r, int c) {
		visited[r][c] = true;
		int cnt = 1;//기본값 1
		for (int i = 0; i < dirs.length; i++) {
			int nr = r+dirs[i][0];
			int nc = c+dirs[i][1];
			
			if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]||map[nr][nc]==0) continue;
			
			cnt+=dfs(nr, nc);
		}
		
		return cnt;
	}
}
