package A2022_02_20;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 1H47MIN
 * @author beaverbae
 * - 현재 물고기들의 상태를 저장해놔야 함(중요) : 주소 문제
 * - null 문제 : null일때(물고기가 없을 때) id(물고기번호를)를 받아올 때 문제 발생 -> dir == 0을 죽은 물고기(빈칸)로 표현
 * 
 */

public class BOJ_19236_청소년_상어 {
	static final int N = 4;
	static final int[][] dirs = {{}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Pair[] board = new Pair[N * N + 1];
		Fish[][] fishes = new Fish[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				board[id] = new Pair(r, c);
				fishes[r][c] = new Fish(id, dir);
			}
		}
		
		// 초기화
		Fish f = fishes[0][0];
		int dir = f.dir;
		fishes[0][0].dir = 0;// dir == 0 -> 죽은 물고기
		
		dfs(board, fishes, 0, 0, dir, f.id, 0);
		System.out.println(ans);
	}

	private static void dfs(Pair[] board, Fish[][] fishes, int sr, int sc, int sd, int sum, int cnt) {
		ans = Math.max(ans, sum);// 최댓값 구하기
		
		// 물고기의 이동
		for (int id = 1; id < N * N + 1; id++) {
			int r = board[id].r;
			int c = board[id].c;
			Fish f = fishes[r][c];
			int d = f.dir;
			int nr = r;
			int nc = c;
			
			if (f.dir == 0) continue; // 죽은 물고기(빈칸) 이라면 pass
			
			for (int i = 0; i < dirs.length - 1; i++) {// 최대 7번 회전
				nr = r + dirs[d][0];
				nc = c + dirs[d][1];
				
				if (!isIn(nr, nc) || isShark(nr, nc, sr, sc)) {// 경계 벗어남 or 상어가 있는 경우
					d += 1;// 반시계
					if (d == dirs.length) d = 1;// d = 0이 아님에 주의
					continue;
				}
				
				// 빈칸 or 다른 물고기 존재
				break;
			}
			fishes[r][c].dir = d; // 방향 반영
			swap(board, fishes, r, c, nr, nc);
		}
		
		// 현재 물고기의 상태 저장(중요)
		Pair[] prevBoard = new Pair[N * N + 1];
		Fish[][] prevFishes = new Fish[N][N];
		deepCopy(board, prevBoard, fishes, prevFishes);
		
		// 상어의 이동
		int r = sr;
		int c = sc;
		
		while (true) {
			int nr = r + dirs[sd][0];
			int nc = c + dirs[sd][1];
			
			if (!isIn(nr, nc)) break;// 경계 벗어나면 종료
		
			// 물고기 먹기
			if (!isBlank(fishes, nr, nc)) {
				int dir = fishes[nr][nc].dir;
				fishes[nr][nc].dir = 0;
				dfs(board, fishes, nr, nc, dir, sum + fishes[nr][nc].id, cnt+1);
				fishes[nr][nc].dir = dir;
				deepCopy(prevBoard, board, prevFishes, fishes);// 원상복구
			}

			r = nr;
			c = nc;
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	private static boolean isShark(int r, int c, int sr, int sc) {
		return r == sr && c == sc;
	}
	
	private static boolean isBlank(Fish[][] fishes, int r, int c) {
		return fishes[r][c].dir == 0;
	}
	
	private static void swap(Pair[] board, Fish[][] fishes, int r, int c, int nr, int nc) {
		if (r == nr && c == nc) return;
		
		int id = fishes[r][c].id;
		int nid = fishes[nr][nc].id;
		
		Fish f = fishes[r][c];
		fishes[r][c] = fishes[nr][nc];
		fishes[nr][nc] = f;
		board[id].r = nr;
		board[id].c = nc;
		board[nid].r = r;
		board[nid].c = c;
	}
	
	private static void deepCopy(Pair[] s1, Pair[] d1, Fish[][] s2, Fish[][] d2) {
		for (int i = 1; i < s1.length; i++) {
			d1[i] = new Pair(s1[i].r, s1[i].c);
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				Fish f = s2[r][c];
				d2[r][c] = new Fish(f.id, f.dir);
			}
		}
	}

	static class Fish {
		int id, dir;

		public Fish(int id, int dir) {
			this.id = id;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Fish [id=" + id + ", dir=" + dir + "]";
		}
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
