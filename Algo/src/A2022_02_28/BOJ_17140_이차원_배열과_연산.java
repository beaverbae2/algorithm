package A2022_02_28;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 52MIN
 * @author beaverbae
 * - 함정 : 0 제외, 행or열 크기가 100초과 이면 100 
 */

public class BOJ_17140_이차원_배열과_연산 {
	static int R = 3, C = 3;
	static int sR, sC, k;
	static int[][] A;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sR = Integer.parseInt(st.nextToken()) - 1;
		sC = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
	
		A = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(exec());
	}
	
	private static int exec() {
		int result = 0;
		
		while (!isAnswer() && result <= 100) {
			result++;
			if (R >= C) rowCalc();
			else colCalc();
		}
		
		if (result > 100) return -1;
		return result;
	}
	
	private static boolean isAnswer() {
		return sR >= 0 && sR < R && sC >= 0 && sC < C && A[sR][sC] == k;
	}
	
	private static void rowCalc() {
		PriorityQueue<Node>[] pq = new PriorityQueue[R];
		int nextC = 0;
		
		for (int r = 0; r < R; r++) {
			pq[r] = new PriorityQueue<>();
			Map<Integer, Integer> map = new HashMap<>();
			
			for (int c = 0; c < C; c++) {
				int n = A[r][c];
				
				if (n == 0) continue;
				if (!map.containsKey(n)) map.put(n, 1);
				else map.put(n, map.get(n) + 1);
			}
			
			for (int n : map.keySet()) {
				int cnt = map.get(n);
				pq[r].add(new Node(n, cnt));
			}
			
			nextC = Math.max(nextC, pq[r].size() * 2);
		}
		
		// 삽입
		C = nextC;
		if (C > 100) C = 100;
		A = new int[R][C];
		for (int r = 0; r < R; r++) {
			int c = 0;
			while(!pq[r].isEmpty() && c < C) {
				Node node = pq[r].poll();
				A[r][c] = node.n;
				A[r][c+1] = node.cnt;
				c += 2;
			}
			
			for (;c < C; c++) {
				A[r][c] = 0;
			}
		}
	}
	
	private static void colCalc() {
		PriorityQueue<Node>[] pq = new PriorityQueue[C];
		int nextR = 0;
		
		for (int c = 0; c < C; c++) {
			pq[c] = new PriorityQueue<>();
			Map<Integer, Integer> map = new HashMap<>();
			
			for (int r = 0; r < R; r++) {
				int n = A[r][c];
				
				if (n == 0) continue;
				if (!map.containsKey(n)) map.put(n, 1);
				else map.put(n, map.get(n) + 1);
			}
			
			for (int n : map.keySet()) {
				int cnt = map.get(n);
				pq[c].add(new Node(n, cnt));
			}
			
			nextR = Math.max(nextR, pq[c].size() * 2);
		}
		
		// 삽입
		R = nextR;
		if (R > 100) R = 100;
		A = new int[R][C];
		for (int c = 0; c < C; c++) {
			int r = 0;
			while(!pq[c].isEmpty() && r < R) {
				Node node = pq[c].poll();
				A[r][c] = node.n;
				A[r+1][c] = node.cnt;
				r += 2;
			}
			
			for (;r < R; r++) {
				A[r][c] = 0;
			}
		}
	}
	
	

	private static void print() {
		for (int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(A[r]));
		}
		System.out.println();
	}
	
	static class Node implements Comparable<Node>{
		int n, cnt;

		public Node(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [n=" + n + ", cnt=" + cnt + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.cnt != o.cnt) return this.cnt - o.cnt;
			return this.n - o.n;
		}
		
		
	}
}
