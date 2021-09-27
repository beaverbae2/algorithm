package A2021_05_31;

import java.util.*;
import java.io.*;

/**
 * backtracking
 * 
 * @see 김재현
 * @author beaverbae
 * 
 * 문제 : 현재 지점 처리, 방향 전환 처리 오류가 있었음
 * 해결 : 한 칸씩 이동하고, 이동횟수 증가는 방향 전환시 발생 
 * 
 */

public class BOJ_9944_NxM_보드_완주하기_solution {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int answer;
	static final int INF = 987654321;
	static int b_cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int tc = 1;
		StringBuilder sb = new StringBuilder();
		
		while ((str = br.readLine()) != null) {
			String[] s = str.split(" ");
			answer = INF;
			R = Integer.parseInt(s[0]);
			C = Integer.parseInt(s[1]);
			map = new char[R][C];
			b_cnt = 0;
			
			for (int i = 0; i < R; i++) {
				str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
					
					if (map[i][j] == '.') {
						b_cnt++;
					}
				}
			}
			
			for (int sr = 0; sr < R; sr++) {
				for (int sc = 0; sc < C; sc++) {
					if (map[sr][sc] == '*') continue;
					
					visited = new boolean[R][C];
					visited[sr][sc] = true;
					for (int d = 0; d < dirs.length; d++) {
						dfs(sr, sc, d, 1, 1);
					}
				}
			}
			
			sb.append("Case ").append(tc).append(": ");
			if (b_cnt == 1) {
				sb.append(0);
			} else if (answer == INF) {
				sb.append(-1);
			} else {
				sb.append(answer);
			}
			sb.append("\n");
			tc++;
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, int dir, int cnt, int depth) {
		if (depth >= answer) return;
		
		if (cnt == b_cnt) {
			answer = Math.min(answer, depth);
			return;
		}
		
		int nr = r + dirs[dir][0];
		int nc = c + dirs[dir][1];
		
		if (isIn(nr, nc) && !visited[nr][nc]) {// 방향 그대로 - depth 그대로
			visited[nr][nc] = true;
			dfs(nr, nc, dir, cnt+1, depth);
			visited[nr][nc] = false;
		} else {// 방향 전환 = depth+1
			for (int d = 0; d < dirs.length; d++) {
				if (d == dir) continue;
			
				nr = r + dirs[d][0];
				nc = c + dirs[d][1];
				
				if (isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc, d, cnt+1, depth+1);
					visited[nr][nc] = false;
				}
			}
		}
		
		
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != '*';
	}
}
