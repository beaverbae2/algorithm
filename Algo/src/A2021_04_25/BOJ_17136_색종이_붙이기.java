package A2021_04_25;

import java.util.*;
import java.io.*;

/**
 * Backtracking
 * 21MIN
 * @author beaverbae
 *
 */

public class BOJ_17136_색종이_붙이기 {
	static int[][] map;
	static final int N = 10;
	static int[] paper = {0, 5, 5, 5, 5, 5}; 
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	private static void dfs(int idx, int cnt) {
		if (idx == N*N) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		int r = idx / N;
		int c = idx % N;
		
		if (map[r][c] == 0) dfs(idx+1, cnt);
		else {
			for (int i = 1; i < paper.length; i++) {
				if (paper[i] == 0) continue;
				
				if (isAttachOk(r, c, i)) {
					attach(r, c, i);
					paper[i]--;
					dfs(idx+1, cnt+1);
					detach(r, c, i);
					paper[i]++;
				}
			}
		}
	}

	private static void attach(int r, int c, int a) {
		for (int i = r; i < r+a; i++) {
			for (int j = c; j < c+a; j++) {
				map[i][j] = 0;
			}
		}
	}
	
	private static void detach(int r, int c, int a) {
		for (int i = r; i < r+a; i++) {
			for (int j = c; j < c+a; j++) {
				map[i][j] = 1;
			}
		}
	}

	private static boolean isAttachOk(int r, int c, int a) {
		for (int i = r; i < r+a; i++) {
			for (int j = c; j < c+a; j++) {
				if (!isIn(i, j) || map[i][j] == 0) return false;
			}
		}
		
		return true;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
