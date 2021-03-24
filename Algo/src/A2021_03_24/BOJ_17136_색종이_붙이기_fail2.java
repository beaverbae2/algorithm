package A2021_03_24;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

/**
 * 
 * @author beaverbae
 * 
 */
public class BOJ_17136_색종이_붙이기_fail2 {
	static int N = 10;
	static boolean[][] map;
	static int answer = Integer.MAX_VALUE;
	static int remain = 100;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 0) {
					map[i][j] = true;
					remain--;
				}
			}
		}
		dfs(0, 0, 5, 5, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int r, int c, int l, int cnt, int total) {
		if (remain == 0) {
			answer = Math.min(answer, total);
			return;
		}
		
		if (total > answer) return;
		
		if (l == 0) return;
		
		if (isAttach(r,c,l,cnt)) {
			attach(r,c,l);
			int nr = getNextRow(r,c);
			int nc = getNextCol(r,c);
			
			System.out.println("l : "+l+", r : "+r+", c : "+c);
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (nr == 0 && nc == 0) {
				dfs(0, 0, l-1, 5, total+1);
			} else {
				dfs(nr, nc, l, cnt-1, total+1);
			}
			detach(r,c,l);
			
			if (nr == 0 && nc == 0) {
				dfs(0, 0, l-1, 5, total);
			} else {
				dfs(nr, nc, l, cnt, total);
			}
			return;
		} else {
			int nr = getNextRow(r,c);
			int nc = getNextCol(r,c);
			
			if (nr == 0 && nc == 0) {
				dfs(0, 0, l-1, 5, total);
			} else {
				dfs(nr, nc, l, cnt, total);
			}
		}
	}

	private static boolean isAttach(int sr, int sc, int l, int cnt) {
		if (cnt == 0) return false;
		
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
		remain -= l*l;
		for (int i = sr; i < sr+l; i++) {
			for (int j = sc; j < sc+l; j++) {
				map[i][j] = true;
			}
		}
	}

	private static void detach(int sr, int sc, int l) {
		remain += l*l;
		for (int i = sr; i < sr+l; i++) {
			for (int j = sc; j < sc+l; j++) {
				map[i][j] = false;
			}
		}
	}
	
	private static int getNextRow(int r, int c, int l) {
		if (c+l == N) {
			r += l;
		}
		
		if (r == N) {
			r = 0;
		}
		return r;
	}
	
	private static int getNextCol(int r, int c, int l) {
		c += l;
		if (c == N) {
			c = 0;
		}		
		
		return c;
	}


	private static int getNextRow(int r, int c) {
		if (c == N-1) {
			r++;
		}
		
		if (r == N) {
			r = 0;
		}
		return r;
	}

	private static int getNextCol(int r, int c) {
		c++;
		if (c == N) {
			c = 0;
		}		
		
		return c;
	}

}
