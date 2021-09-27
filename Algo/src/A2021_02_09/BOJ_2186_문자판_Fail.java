package A2021_02_09;

import java.util.*;
import java.io.*;

public class BOJ_2186_문자판_Fail {
	static List<Pair>[] init;
	static List<Pair>[] list;
	static int R, C, K;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int N, answer;
	static int[][][] memo;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init = new List[26];
		for (int i = 0; i < init.length; i++) {
			init[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str.charAt(j);
				int idx = getIdx(ch);
				init[idx].add(new Pair(i, j));
			}
		}
		
		String target = br.readLine();
		list = new List[target.length()];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < target.length(); i++) {
			char ch = target.charAt(i);
			int idx = getIdx(ch);
		
			list[i].addAll(init[idx]);
		}
		
//		for (int i = 0; i < list.length; i++) {
//			System.out.println(list[i]);
//		}
		N = target.length();
		memo = new int[N][R][C];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < R; j++) {
				Arrays.fill(memo[i][j], INF);
			}
		}
		
		for (Pair p : list[0]) {
			int r = p.r;
			int c = p.c;
			memo[0][r][c] = 0;
			dfs(r, c, 0);
		}
		
		for (Pair p : list[0]) {
			answer += memo[0][p.r][p.c];
		}
		System.out.println(answer);
	}
	
	private static int dfs(int pr, int pc, int cnt) {
		if (cnt+1 == N) {
			return memo[cnt][pr][pc] = 1;
		}
		
//		if (memo[cnt][pr][pc] != INF) {
//			return memo[cnt][pr][pc];
//		}
		
		for (Pair pair : list[cnt+1]) {
			int r = pair.r;
			int c = pair.c;
			
			boolean flag = false;
			for (int k = 1; k <= K; k++) {
				for (int d = 0; d < dirs.length; d++) {
					int nr = pr + k*dirs[d][0];
					int nc = pc + k*dirs[d][1];
					
					if (isInMap(nr, nc) && r == nr && c == nc) {
						if (memo[cnt+1][r][c] == INF) {
							memo[cnt+1][r][c] = 0;
							memo[cnt+1][r][c] += dfs(nr, nc, cnt+1);
						}
						flag = true;
						break;
					}
				}
				if (flag) break;
			}
			if (flag && memo[cnt+1][r][c] != INF) {
				memo[cnt][pr][pc] += memo[cnt+1][r][c];
			}
		}
		return memo[cnt][pr][pc];
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static int getIdx(char ch) {
		return ch - 'A';
	}
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
}
