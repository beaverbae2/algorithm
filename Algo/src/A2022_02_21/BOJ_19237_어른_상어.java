package A2022_02_21;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 2H
 * @author beaverbae
 * - 1000초까지는 정답취급!
 * - 문제가 해석이 명확하지 않은듯 -> 냄새뿌리는 타이
 */

public class BOJ_19237_어른_상어 {
	static final int[][] dirs = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, K;
	static PriorityQueue<Shark> sharks, nextSharks;
	static Smell[][] smells;
	static int[][][] pos;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		PriorityQueue<Shark> initSharks = new PriorityQueue<>();
		sharks = new PriorityQueue<>();
		nextSharks = new PriorityQueue<>();
		smells = new Smell[N][N];
		pos = new int[M+1][5][5];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine()); 
			for (int c = 0; c < N; c++) {
				int id = Integer.parseInt(st.nextToken());
				if (id == 0) continue;
				
				initSharks.offer(new Shark(id, r, c, -1));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int n = M;
		while (n-- > 0) {
			Shark s = initSharks.poll();
			s.d = Integer.parseInt(st.nextToken());
			sharks.add(s);
			smells[s.r][s.c] = new Smell(s.id, K);
		}
		
		for (int m = 1; m <= M; m++) {
			for (int k = 1; k <= 4; k++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 4; j++) {
					pos[m][k][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		System.out.println(exec());
	}
	
	private static int exec() {
		int result = 0;
		while (!isEnd(result)) {
			moveSharks();
			downSmell();
			addSmell();
			result++;
		}
		
		if (result == 1001) result = -1;
		return result;
	}

	private static void addSmell() {
		boolean[][] visited = new boolean[N][N];
		
		while (!nextSharks.isEmpty()) {
			Shark s = nextSharks.poll();
			int id = s.id;
			int r = s.r;
			int c = s.c;
			int d = s.d;
			
			if (visited[r][c]) continue;
			
			smells[r][c] = new Smell(id, K);
			sharks.add(new Shark(id, r, c, d));
			visited[r][c] = true;
		}
	}

	private static void moveSharks() {
		while (!sharks.isEmpty()) {
			Shark s = sharks.poll();
			int id = s.id;
			int r = s.r;
			int c = s.c;
			int d = s.d;
			
			int nr = -1;
			int nc = -1;
			int nd = -1;
			int[] sdirs = pos[id][d];
			boolean isBlank = false;
			for (int i = 1; i < pos[id][d].length; i++) {
				nd = sdirs[i];
				nr = r + dirs[nd][0];
				nc = c + dirs[nd][1];
				
				if (!isIn(nr, nc)) continue;
				
				if (smells[nr][nc] == null) {
					isBlank = true;
					break;
				}
			}
			
			if (!isBlank) {
				for (int i = 1; i < pos[id][d].length; i++) {
					nd = sdirs[i];
					nr = r + dirs[nd][0];
					nc = c + dirs[nd][1];
					
					if (!isIn(nr, nc)) continue;
					
					if (smells[nr][nc].id == s.id) {
						break;
					}
				}
			}
			
			nextSharks.add(new Shark(s.id, nr, nc, nd));
		}
	}

	private static void downSmell() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (smells[r][c] == null) continue;
				
				smells[r][c].t--;
				if (smells[r][c].t == 0) smells[r][c] = null;
			}
		}
	}

	private static boolean isEnd(int result) {
		return sharks.size() == 1 || result == 1001;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static class Shark implements Comparable<Shark>{
		int id, r, c, d;

		public Shark(int id, int r, int c, int d) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Shark [id=" + id + ", r=" + r + ", c=" + c + ", d=" + d + "]";
		}

		@Override
		public int compareTo(Shark o) {
			return this.id - o.id;
		}
	}
	
	static class Smell {
		int id, t;

		public Smell(int id, int t) {
			this.id = id;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Smell [id=" + id + ", t=" + t + "]";
		}
	}
}
