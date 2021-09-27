package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * DFS + Memoization
 * 29MIN
 * 한번에 생각 못한 부분 : 이미 방문한 지점 방문시 경우의 수(탈출 가능한 경우, 못하는 경우)
 * @author beaverbae
 *
 */

public class BOJ_17090_미로_탈출하기 {
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static int R, C;
	static char[][] map;
	static int flag;
	static boolean[][] visited;
	static int[][] isOut;// 0 : 초기화, -1 : 통과X, 1 : 통과O
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		isOut = new int[R][C];
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (isOut[r][c] == 0) {
					flag = 0;
					dfs(r, c);
				}
			}
		}
		
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (isOut[r][c] == 1) answer++;
			}
		}
		System.out.println(answer);
	}
	
	private static void dfs(int r, int c) {
		visited[r][c] = true;
		int d = dirNum(map[r][c]);
		
		int nr = r + dirs[d][0];
		int nc = c + dirs[d][1];
		
		if (isIn(nr, nc)) {
			if (visited[nr][nc]) {
				///		바로 생각 못한 부분		///
				if (isOut[nr][nc] == 1) {// 탈출 가능
					flag = 1;
				} else {// 탈출 불가
					flag = -1;
				}
				///////////////////////////////
				isOut[r][c] = flag;
				return;
			} else {
				dfs(nr, nc);
			}
		} else {// 범위 밖 : 미로 통과
			flag = 1;
			isOut[r][c] = flag;
			return;
		}
		
		// 리턴 후 처리
		if (flag != 0) { 
			isOut[r][c] = flag;
		}
	}

	private static int dirNum(char ch) {
		if (ch == 'U') {
			return 0;
		} else if (ch == 'D') {
			return 1;
		} else if (ch == 'L') {
			return 2;
		} else if (ch == 'R') {
			return 3;
		}
		return -1;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
