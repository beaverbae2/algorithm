package A2021_03_23;

import java.util.*;
import java.io.*;

/**
 * Brute force
 * 
 * @author beave
 * @see https://justicehui.github.io/koi/2018/09/09/BOJ7573/
 */

public class BOJ_7573_고기잡이_solution {
	static ArrayList<Pair> fishes;
	static int N, L, M;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		fishes = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			fishes.add(new Pair(r, c));
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 1; k < L/2; k++) {
					answer = Math.max(answer, getCnt(fishes.get(i).r, fishes.get(j).c, k, L/2-k));
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static int getCnt(int r, int c, int dr, int dc) {
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			Pair p = fishes.get(i);
			if (r <= p.r && p.r <= r+dr && c <= p.c && p.c <= c+dc) {
				cnt++;
			}
		}
		
		return cnt;
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}

	}
}
