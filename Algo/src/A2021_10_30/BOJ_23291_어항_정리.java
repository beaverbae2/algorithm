package A2021_10_30;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 2H56MIN
 * @author beaverbae
 * 인덱싱은 언제 익숙해지는 걸까
 *
 */

public class BOJ_23291_어항_정리 {
	static int N, K;
	static int ans, max, min;
	static final int MAX = 0, MIN = 10001;
	static int[][] board, nextBoard;
	static int maxR;
	static List<Node> arrangeList;
	static int[][] arrangeArr;
	static final int[][] dirs = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		makeArrangeList();
		makeArrangeArr();
		maxR = maxRow();
		board = new int[maxR][N];
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			board[maxR-1][j] = Integer.parseInt(st.nextToken());
		}
		
		while (true) {
			calcMaxAndMin();// 최대 최소 구하기
			if (max - min <= K) break;
			
			addFishesAtMinIndexs();
			lift1();
			lift2();
			
			ans++;
		}
		
		System.out.println(ans);
	}


	private static void addFishesAtMinIndexs() {
		for (int j = 0; j < N; j++) {
			if (board[maxR-1][j] == min) board[maxR-1][j]++; 
		}
		
	}


	private static void lift1() {
		for (int k = 0; k < arrangeList.size()-1; k++) {
			Node next = arrangeList.get(k+1);
			rotateAndPut1(next);
		}
		
		replaceFishes();
		arrange();
	}


	private static void rotateAndPut1(Node next) {
		Queue<Integer> q = new LinkedList<>();
		for (int r = 0; r < maxR; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == 0) continue;

				q.offer(board[r][c]);
			}
		}
		
		board = new int[maxR][next.bots];
		
		int w = next.w;
		int h = next.h;
		
		for (int c = w-1; c >= 0; c--) {
			for (int r = maxR-1-h; r < maxR-1; r++) {
				board[r][c] = q.poll();
			}
		}
		
		
		for (int c = 0; c < board[0].length; c++) {
			board[maxR-1][c] = q.poll();
		}
	}


	private static void replaceFishes() {
		int R = maxR;
		int C = board[maxR-1].length;
		
		int[][] replaceBoard = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] == 0) continue;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					if (!isIn(nr, nc, R, C) || board[nr][nc] == 0) continue;
					
					int sub = board[r][c] - board[nr][nc];
					int denom = sub / 5;
					
					if (denom > 0) {
						replaceBoard[r][c]-=denom;
						replaceBoard[nr][nc]+=denom;
					}
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				board[r][c] += replaceBoard[r][c];
			}
		}
		
	}

	private static boolean isIn(int nr, int nc, int R, int C) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static void arrange() {
		nextBoard = new int[maxR][N];
		int j = 0;
		
		for (int c = 0; c < board[maxR-1].length; c++) {
			for (int r = maxR-1; r >= 0; r--) {
				if (board[r][c] == 0) continue;
				
				nextBoard[maxR-1][j++] = board[r][c];
				if (j == N) break;
			}
		}
		
		board = nextBoard;
	}


	private static void lift2() {
		print(board);
		
		for (int i = 1; i < arrangeArr.length; i++) {
			int w = arrangeArr[i][0];
			int h = arrangeArr[i][1];
			rotateAndPut2(w, h);
		}
		replaceFishes();
		arrange();
	}


	private static void rotateAndPut2(int w, int h) {
		System.out.println(w+", "+h);
		Queue<Integer> q = new LinkedList<>();
		System.out.println("R : "+maxR);
		for (int r = maxR-1; r > maxR-1 - h/2; r--) {
			for (int c = w-1; c>= 0; c--) {
				q.offer(board[r][c]);
			}
		}
		
		
		for (int r = maxR - h/2; r < maxR; r++) {
			for (int c = w; c < board[r].length; c++) {
				q.offer(board[r][c]);
			}
		}
		
		System.out.println("q : "+q);
		board = new int[maxR][w];
		System.out.println("R : "+maxR);
		for (int r = maxR - h; r < maxR; r++) {
			for (int c = 0; c < w; c++) {
				board[r][c] = q.poll();
			}
		}
		
	}


	private static void print(int[][] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.println(Arrays.toString(b[i]));
		}
		System.out.println();
	}

	// 행의 최대 크기 구하기
	private static int maxRow() {
		int r1 = arrangeList.get(arrangeList.size() - 1).h + 1;
		int r2 = 4;
		
		return Math.max(r1, r2);
	}


	private static void makeArrangeArr() {
		arrangeArr = new int [3][2];
		int w = N;
		int h = 1;
		
		for (int i = 0; i < arrangeArr.length; i++) {
			arrangeArr[i][0] = w;
			arrangeArr[i][1] = h;
			
			w /= 2;
			h *= 2;
		}
		
	}


	private static void makeArrangeList() {
		arrangeList = new ArrayList<>();
		int w = 1;
		int h = 0;
		int tops = w * h;
		int bots = N-tops;
		int idx = 0;
		
		while (true) {
			arrangeList.add(new Node(w, h, tops, bots));
			if (idx % 2 != 0) w++;
			else h++;
			
			tops = w * h;
			bots = N - tops;
			
			if (bots < w) break;
			
			idx++;
		}
		
	}


	private static void calcMaxAndMin() {
		max = MAX;
		min = MIN;
		
		for (int j = 0; j < N; j++) {
			max = Math.max(max, board[maxR-1][j]);
			min = Math.min(min, board[maxR-1][j]);
		}
	}
	
	static class Node {
		int w, h, tops, bots;

		public Node(int w, int h, int tops, int bots) {
			this.w = w;
			this.h = h;
			this.tops = tops;
			this.bots = bots;
		}

		@Override
		public String toString() {
			return "Node [w=" + w + ", h=" + h + ", tops=" + tops + ", bots=" + bots + "]";
		}
	}
}
