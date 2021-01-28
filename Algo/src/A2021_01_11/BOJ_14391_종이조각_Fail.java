package A2021_01_11;

import java.util.*;
import java.io.*;

public class BOJ_14391_종이조각_Fail {
	static int answer;
	static boolean[][] visited;
	static int[][] map;
	static int R, C, N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = R*C;
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		dfs(0, 0);
		System.out.println(answer);
	}

	private static void dfs(int cnt, int sum) {
		for (int i = 0; i < visited.length; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println();
		if (cnt == N) {
			answer = Math.max(answer, sum);
			System.out.println("answer : "+answer+", sum : "+sum);
			System.out.println();
			return;
		}
		
		int r = -1, c = -1; 
		boolean flag = false;
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				if (!visited[i][j]) { 
					r = i;
					c = j;
					flag = true;
					break;
				}
			}
			if (flag) break;
		}
		
		if (r==-1 && c==-1) System.out.println("sum : "+sum);
		
		//현재 좌표
		visited[r][c] = true;
		dfs(cnt+1, sum+map[r][c]);
		visited[r][c] = false;
		
		//오른쪽
		visited[r][c] = true;
		String temp = Integer.toString(map[r][c]);
		for (int d = 0; d < map[0].length; d++) {
			int nr = r;
			int nc = c + (d+1)*1;
			
			if (isInMap(nr, nc) && !visited[nr][nc]) {
				visited[nr][nc] = true;
				temp += map[nr][nc];
				dfs(cnt+(d+2), sum + Integer.parseInt(temp));
			} else break;
		}
		
		for (int d = 0; d < map[0].length; d++) {
			int nr = r;
			int nc = c + (d+1)*1;
			
			if (isInMap(nr, nc)) {
				visited[nr][nc] = false;
			}
		}
		visited[r][c] = false;
		
		//아래
		visited[r][c] = true;
		temp = Integer.toString(map[r][c]);
		for (int d = 0; d < map.length; d++) {
			int nr = r + (d+1)*1;
			int nc = c;
			
			if (isInMap(nr, nc) && !visited[nr][nc]) {
				visited[nr][nc] = true;
				temp += map[nr][nc];
				dfs(cnt+(d+2), sum + Integer.parseInt(temp));
			} else break;
		}
		
		for (int d = 0; d < map.length; d++) {
			int nr = r + (d+1)*1;
			int nc = c;
			
			if (isInMap(nr, nc)) {
				visited[nr][nc] = false;
			} else break;
		}
		visited[r][c] = false;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
