package A2022_02_10;

import java.util.*;
import java.io.*;

/**
 * 58MIN
 * Simulation
 * @author beaverbae
 *
 */

public class BOJ_16235_나무_제테크 {
	static int[][] ground, A;
	static PriorityQueue<Tree> aliveTrees, deadTrees, spreadTrees; 
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ground = new int[N][N];
		A = new int[N][N];
		aliveTrees = new PriorityQueue<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				ground[r][c] = 5;
			}
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
		
			aliveTrees.add(new Tree(r, c, age));
		}
		
		System.out.println(execute());
	}
	
	private static int execute() {
		while (K-- > 0) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		return aliveTrees.size();
	}
	
	private static void winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ground[r][c] += A[r][c];
			}
		}
	}

	private static void autumn() {
		while (!spreadTrees.isEmpty()) {
			Tree t = spreadTrees.poll();
			for (int d = 0; d < dirs.length; d++) {
				int nr = t.r + dirs[d][0];
				int nc = t.c + dirs[d][1];
				
				if (!isIn(nr, nc)) continue;
				
				aliveTrees.add(new Tree(nr, nc, 1));// 다음 좌표
			}
		}
	}

	private static void summer() {
		while (!deadTrees.isEmpty()) {
			Tree t = deadTrees.poll();
			ground[t.r][t.c] += (t.age / 2);// 나이/2
		}
	}

	private static void spring() {
		PriorityQueue<Tree> nextAliveTrees = new PriorityQueue<>();
		deadTrees = new PriorityQueue<>();
		spreadTrees = new PriorityQueue<>();
		
		while (!aliveTrees.isEmpty()) {
			Tree t = aliveTrees.poll();
			
			if (!hasEnergy(t.r, t.c, t.age)) {
				deadTrees.add(new Tree(t.r, t.c, t.age));
			} else {
				ground[t.r][t.c] -= t.age;
				nextAliveTrees.add(new Tree(t.r, t.c, t.age+1));// ++안댐
				if ((t.age + 1) % 5 == 0) spreadTrees.add(new Tree(t.r, t.c, t.age+1));
			}
		}
		
		aliveTrees = nextAliveTrees;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
	
	private static boolean hasEnergy(int r, int c, int age) {
		return ground[r][c] - age >= 0;
	}
	
	private static void print(int[][] b) {
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(b[r]));
		}
		System.out.println();
	}
	
	static class Tree implements Comparable<Tree>{
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}

		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
		}
	}
}
