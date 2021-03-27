package A2021_03_27;

import java.util.*;
import java.io.*;

/**
 * DFS
 * fail
 * @author beaverbae
 *
 */

public class BOJ_1799_비숍_시간초과 {
	static int N;
	static int[][] map;
	static int[][] dirs = {{1,1},{-1,-1},{1,-1},{-1,1}};
	static boolean[][] visited;
	static List<Integer> list;
	static int answer;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			
				if (map[i][j] == 1) {
					int idx = i*N + j;
					list.add(idx);
				}
			}
		}
		
		dfs(0, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int idx, int cnt) {
		if (idx == list.size()) {
			answer = Math.max(answer, cnt);
			return;
		}
		
		int i = list.get(idx);
		int r = i/N;
		int c = i%N;
		
		if (check(r,c)) {// 둠
			visited[r][c] = true;
			dfs(idx+1, cnt+1);
			visited[r][c] = false;
		}
		
		// 두지 않음
		dfs(idx+1, cnt);
	}
	
	private static boolean check(int sr, int sc) {
		for (int d = 0; d < dirs.length; d++) {
			int r = sr;
			int c = sc;
			
			while (true) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) break;
				if (visited[nr][nc]) return false;
				
				r = nr;
				c = nc;
			}
		}
		
		return true;
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
