package A2020_11_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2667_단지번호붙이기_BFS {
	static boolean[][] visited;
	static int N;
	static int totalHomeCnt;
	static ArrayList<Integer> homeCntList;
	static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		homeCntList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				if(str.charAt(j)=='0') visited[i][j] = true;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				homeCntList.add(bfs(i,j));
				totalHomeCnt++;
			}
		}
		Collections.sort(homeCntList);
		StringBuilder sb = new StringBuilder();
		sb.append(totalHomeCnt).append("\n");
		for(int homeCnt: homeCntList) {
			sb.append(homeCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int bfs(int start_r, int start_c) {
		int homeCnt = 1;
		Queue<int[]> q = new LinkedList<>();
		visited[start_r][start_c] = true;
		q.offer(new int[] {start_r, start_c});
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
		
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				
				if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
				homeCnt++;
			}
		}
		
		return homeCnt;
	}
}
