package A2020_12_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {
	static int R,C;
	static int answer;
	static char[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[26];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		char ch = map[0][0];
		int index = ch-'A';
		visited[index] = true;
		dfs(0,0,1);
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int move_cnt) {
		for (int d = 0; d < dirs.length; d++) {
			int nr = r+dirs[d][0];
			int nc = c+dirs[d][1];
		
			if(isInMap(nr,nc)) {
				int ch = map[nr][nc];
				int index = ch-'A';
				if(visited[index]) {
					answer = Math.max(answer, move_cnt);
					//return; NOT!!!
				}else {
					visited[index] = true;
					dfs(nr, nc, move_cnt+1);
					visited[index] = false;
				}
			}
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<R&&nc>=0&&nc<C;
	}
}
