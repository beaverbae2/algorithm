package A2022_04_25;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 91MIN
 * @author beaverbae
 * - 쉽지 않구먼유
 */

public class BOJ_23290_마법사_상어와_복제 {
	static int sr, sc, M, S, N = 4;
	static List<Integer>[][] board, nextBoard, copyBoard;
	static List<Node> list;
	static int[][] smells;
	static final int[][] f_dirs = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	static final int[][] s_dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
	
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			board[r][c].add(d);
		}
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		
		System.out.println(exec());
	}
	
	private static int exec() {
		while (S-- > 0) {
			copy();
			moveFishes();
			moveShark();
			deleteSmells();
			paste();
		}
		
		return getRemainFishes();
	}
	
	private static void copy() {
		copyBoard = new List[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copyBoard[r][c] = new ArrayList<>();
			
				for (int d : board[r][c]) {
					copyBoard[r][c].add(d);
				}
			}
		}
	}

	private static void moveFishes() {
		nextBoard = new List[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				nextBoard[r][c] = new ArrayList<>();
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < board[r][c].size(); i++) {
					int d = board[r][c].get(i);
					int cnt = 0;
					int nr = r;
					int nc = c;
					
					while (cnt < 8) {
						nr = r + f_dirs[d][0];
						nc = c + f_dirs[d][1];
						if (isOk(nr, nc)) break;
						
						cnt++;
						d--;
						
						if (d < 0) d = 7;
						// 이동하지 않는 경우 고려
						nr = r;
						nc = c;
					}
					
					nextBoard[nr][nc].add(d);
				}
			}
		}
		
		board = nextBoard;
	}

	private static void moveShark() {
		list = new ArrayList<>();
		dfs(sr, sc, 0, 0, 0);
		
		Collections.sort(list);
		
		Node n = list.get(0);
		
		int dirSum = n.dirSum;
		int rem = 100;
		int r = sr;
		int c = sc;
		int nr = r;
		int nc = c;
		
		while (rem > 0) {
			int d = dirSum / rem;
			nr = r + s_dirs[d][0];
			nc = c + s_dirs[d][1];
			
			// 물고기가 존재해야만 함
			if (!board[nr][nc].isEmpty()) {
				board[nr][nc].clear();
				smells[nr][nc] = 3; // 냄새는 3으로 초기화
			}
			
			// 상어의 이동 후 위치 저장
			r = nr;
			c = nc;
			// 계산 진행
			dirSum %= rem;
			rem /= 10;
		}
		
		sr = nr;
		sc = nc;
	}

	private static void deleteSmells() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (smells[r][c] > 0) {
					smells[r][c]--;
				}
			}
		}
	}

	private static void paste() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d : copyBoard[r][c]) {
					board[r][c].add(d);
				}
			}
		}
	}
	
	private static void dfs(int r, int c, int dirSum, int fishAteCnt, int cnt) {
		if (cnt == 3) {
			list.add(new Node(dirSum, fishAteCnt));
			return;
		}
		
		for (int d = 0; d < s_dirs.length; d++) {
			int nr = r + s_dirs[d][0];
			int nc = c + s_dirs[d][1];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			List<Integer> temp = new ArrayList<>();
			for (int nd : board[nr][nc]) {
				temp.add(nd);
			}
			board[nr][nc].clear();
			
			dfs(nr, nc, dirSum * 10 + d, fishAteCnt+temp.size(), cnt+1);
			
			for (int nd : temp) {
				board[nr][nc].add(nd);
			}
		}
	}

	private static int getRemainFishes() {
		int remainFishes = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				remainFishes += board[r][c].size();
			}
		}
		
		return remainFishes;
	}
	
	private static boolean isOk(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N) return false;
		if (sr == nr && sc == nc) return false;
		if (smells[nr][nc] > 0) return false;
		
		return true;
	}
	
	private static void init() {
		board = new List[N][N];
		copyBoard = new List[N][N];
		smells = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				board[r][c] = new ArrayList<>();
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int dirSum, fishAteCnt;

		public Node(int dirSum, int fishAteCnt) {
			this.dirSum = dirSum;
			this.fishAteCnt = fishAteCnt;
		}

		@Override
		public String toString() {
			return "Node [dirSum=" + dirSum + ", fishAteCnt=" + fishAteCnt + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.fishAteCnt != o.fishAteCnt) return o.fishAteCnt - this.fishAteCnt;
			return this.dirSum - o.dirSum;
		}
	}
}
