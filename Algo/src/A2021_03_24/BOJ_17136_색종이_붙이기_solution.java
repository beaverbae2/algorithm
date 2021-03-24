package A2021_03_24;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * @see https://steady-coding.tistory.com/43
 * 
 */
public class BOJ_17136_색종이_붙이기_solution {
	static int N = 10;
	static boolean[][] map;
	static int answer = Integer.MAX_VALUE;
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 0) {
					map[i][j] = true;
				}
			}
		}
		dfs(0, 0, 0);
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	private static void dfs(int r, int c, int cnt) {
		if (r == N-1 && c == N) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		if (cnt >= answer) return;
		
		if (c == N) {
			dfs(r+1, 0, cnt);
			return;
		}
		
		if (!map[r][c]) {
			for (int l = 5; l > 0; l--) {
				if (paper[l] > 0 && isAttach(r, c, l)) {
					paper[l]--;
					attach(r,c,l);
					dfs(r, c+1, cnt+1);
					detach(r,c,l);
					paper[l]++;
				}
			}
		} else {
			dfs(r, c+1, cnt);
		}
		
	}

	private static boolean isAttach(int sr, int sc, int l) {
		for (int i = sr; i < sr+l; i++) {
			for (int j = sc; j < sc+l; j++) {
				if (!isIn(i, j) || map[i][j]) return false;
			}
		}
		
		return true;
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

	private static void attach(int sr, int sc, int l) {
		for (int i = sr; i < sr+l; i++) {
			for (int j = sc; j < sc+l; j++) {
				map[i][j] = true;
			}
		}
	}

	private static void detach(int sr, int sc, int l) {
		for (int i = sr; i < sr+l; i++) {
			for (int j = sc; j < sc+l; j++) {
				map[i][j] = false;
			}
		}
	}
}
