package A2022_02_13;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 2H20MIN
 * @author beaverbae
 * 
 */

public class BOJ_23290_마법사_상어와_복제 {
	static int M, S, sR, sC;
	static final int N = 4;
	static int[][] f_dirs = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	static int[][] s_dirs = {{}, {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static List<Integer>[][] fishes, nextFishes;
	static int[][] fishCounts;
	static Queue<Fish> copiedFishes;
	static List<int[]> sharkPaths;
	static int[][] smell;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		fishes = new List[N][N];
		fishCounts = new int[N][N];
		smell = new int[N][N];
		sharkPaths = new ArrayList<>();
		init();
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
		
			fishes[r][c].add(d);
			fishCounts[r][c]++;
		}
		
		st = new StringTokenizer(br.readLine());
		sR = Integer.parseInt(st.nextToken()) - 1;
		sC = Integer.parseInt(st.nextToken()) - 1;
	
		System.out.println(execute());
	}
	
	static int execute() {
		int result = 0;
		int s = 1;
		while (s <= S) {
			copy();
			moveFishes();
//			print();
			moveShark(s);
			removeOldSmell(s);
			copyTo();
			s++;
//			print();
		}
		
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result += fishCounts[r][c];
			}
		}
		
		return result;
	}
	

	static void copy() {
		copiedFishes = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d : fishes[r][c]) {
					copiedFishes.offer(new Fish(r, c, d));
				}
			}
		}
	}

	static void moveFishes() {
		nextFishes = new List[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				nextFishes[r][c] = new ArrayList<>();
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d : fishes[r][c]) {
					int nd = d;
					boolean isMove = false;
					
					for (int i = 0; i < f_dirs.length; i++) {
						int nr = r + f_dirs[nd][0];
						int nc = c + f_dirs[nd][1];
						
						if (!isIn(nr, nc) || isShark(nr, nc) || isSmell(nr, nc)) {
							nd--;
							if (nd < 0) nd = f_dirs.length - 1;
							continue;
						}
						
						nextFishes[nr][nc].add(nd);
						isMove = true;
						fishCounts[r][c]--;
						fishCounts[nr][nc]++;
						break;
					}
					
					if (!isMove) nextFishes[r][c].add(d);
				}
			}
		}
		
		fishes = nextFishes;
	}
	
	static void moveShark(int time) {
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		for (int[] dirs : sharkPaths) {
			int r = sR;
			int c = sC;
			int value = 0;
			int count = 0;
			int s = 100;
			boolean isMove = true;
			boolean[][] visited = new boolean[N][N];
			
			for (int d : dirs) {
				int nr = r + s_dirs[d][0];
				int nc = c + s_dirs[d][1];
				
				if (!isIn(nr, nc)) {
					isMove = false;
					break;
				}
				
				if (!visited[nr][nc] && fishCounts[nr][nc] > 0) {
					count += fishCounts[nr][nc];
				}
				
				visited[nr][nc] = true;
				r = nr;
				c = nc;
				value += s * d;
				s /= 10;
			}
			
			if (isMove) {
				pq.add(new Shark(value, count));
			}
		}
		
//		System.out.println(pq);
		Shark s = pq.poll();
		int value = s.value;
		int r = sR;
		int c = sC;
		int n = 100;
//		System.out.println(s);
		while (n > 0) {
			int d = value / n;
			value %= n;
			n /= 10; 
			r += s_dirs[d][0];
			c += s_dirs[d][1];
			
			if (fishCounts[r][c] > 0) {
				fishCounts[r][c] = 0;
				fishes[r][c] = new ArrayList<>();
				smell[r][c] = time;
			}
		}
		
		sR = r;
		sC = c;
		
//		System.out.println("sR : "+sR+", sC : "+sC);
	}
	
	static void copyTo() {
		while (!copiedFishes.isEmpty()) {
			Fish f = copiedFishes.poll();
			int r = f.r;
			int c = f.c;
			int d = f.d;
			
			fishes[r][c].add(d);
			fishCounts[r][c]++;
		}
	}
	
	static void removeOldSmell(int s) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (s - smell[r][c] == 2) smell[r][c] = 0;
			}
		}
	}
	
	static boolean isSmell(int nr, int nc) {
		return smell[nr][nc] > 0;
	}

	static boolean isShark(int nr, int nc) {
		return nr == sR && nc == sC;
	}

	static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	static void init() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				fishes[r][c] = new ArrayList<>();
			}
		}
		
		for (int d1 = 1; d1 < s_dirs.length; d1++) {
			for (int d2 = 1; d2 < s_dirs.length; d2++) {
				for (int d3 = 1; d3 < s_dirs.length; d3++) {
					sharkPaths.add(new int[] {d1, d2, d3});
				}
			}
		}
	}
	
	static void print() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(fishes[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Shark implements Comparable<Shark>{
		int value, count;

		public Shark(int value, int count) {
			this.value = value;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Shark [value=" + value + ", count=" + count + "]";
		}

		@Override
		public int compareTo(Shark o) {
			if (count != o.count) {
				return o.count - count;
			} else {
				return value - o.value;
			}
		}
	}
	
	static class Fish {
		int r, c, d;

		public Fish(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fish [r=" + (r+1) + ", c=" + (c+1) + ", d=" + (d+1) + "]";
		}
	}
}
