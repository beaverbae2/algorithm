package A2022_02_08;

import java.util.*;
import java.io.*;

public class BOJ_16234_인구이동 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		Board board = new Board(N, L, R);
		
		for (int r = 0; r < N; r++) {
			board.setUp(br.readLine(), r);
		}
		
		System.out.println(board.excute());
	}
}

class Pair {
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

class Board {
	int N, L, R;
	int[][] board, dirs;
	boolean[][] isMove;
	HashSet<String> set;
	
	public Board(int N, int L, int R) {
		this.N = N;
		this.L = L;
		this.R = R;
		board = new int[N][N];
		dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	}
	
	public void setUp(String s, int r) {
		StringTokenizer st = new StringTokenizer(s);
		for (int c = 0; c < N; c++) {
			board[r][c] = Integer.parseInt(st.nextToken());
		}
	}
	
	public int excute() {
		int cnt = 0;
		while (open()) {
			move();
			cnt++;
		}
		
		return cnt;
	}
	
	public boolean open() {
		boolean isOpenExist = false;
		set = new HashSet<>();
		isMove = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					if (!isOk(nr, nc)) continue;
					
					int sub = Math.abs(board[r][c] - board[nr][nc]);
					if (sub < L || sub > R) continue;
					
					set.add((r * N + c)+" "+(nr * N + nc));
					isMove[r][c] = true;
					if (!isOpenExist) isOpenExist = true;
				}
			}
		}
		
		return isOpenExist;
	}
	
	private void move() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!isMove[r][c]) continue;
				bfs(r, c);
			}
		}
	}
	
	private void bfs(int sr, int sc) {
		Queue<Pair> q = new LinkedList<>();
		LinkedList<Pair> list = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int total = 0;
		
		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			list.add(p);
			total += board[p.r][p.c];
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = p.r + dirs[d][0];
				int nc = p.c + dirs[d][1];
				
				if (!isOk(nr, nc) || visited[nr][nc] || !isUnion(p.r * N + p.c, nr * N + nc)) continue;
			
				q.offer(new Pair(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		int n = total / list.size();
		
		for (Pair p : list) {
			board[p.r][p.c] = n;
			isMove[p.r][p.c] = false;
		}
	}
	
	private boolean isUnion(int pos, int npos) {
		return set.contains(pos+" "+npos);
	}
	
	private boolean isOk(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}