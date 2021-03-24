package A2021_03_24;

import java.util.*;
import java.io.*;

/**
 * fail
 * @author beaverbae
 * counter
	1 1 1 1 1 1 1 0 0 0
	1 1 1 1 1 1 1 0 0 0
	1 1 1 1 1 1 1 0 0 0
	1 1 1 1 1 1 1 0 0 0
	1 1 1 1 1 1 1 0 0 0
	1 1 1 1 1 0 0 0 0 0
	1 1 1 1 1 0 0 0 0 0
	1 1 1 1 1 0 0 0 0 0
	1 1 1 1 1 0 0 0 0 0
	0 0 0 0 0 0 0 0 0 0
 
 * answer : 5
 * 
 */
public class BOJ_17136_색종이_붙이기_fail {
	static int N = 10;
	static Square[] arr;
	static boolean[][] map;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 0) {
					map[i][j] = true;
				}
			}
		}
		
		arr = new Square[5];
		int idx = 0;
		for (int l = 5; l > 0; l--) {
			arr[idx++] = new Square(l, 5);
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (isAttach(r, c, i)) answer++;
				}
			}
		}
		
		boolean flag = true;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!map[r][c]) {
					flag = false;
					break;
				}
			}
			if (!flag) break;
		}
		
		if (flag) System.out.println(answer);
		else System.out.println(-1);
	}
	
	private static boolean isAttach(int sr, int sc, int idx) {
		int l = arr[idx].l;
		
		if (arr[idx].cnt == 0) return false;
		
		for (int r = sr; r < sr+l; r++) {
			for (int c = sc; c < sc+l; c++) {
				if (!isIn(r, c) || map[r][c]) return false;
			}
		}
		
		arr[idx].cnt -= 1;
		for (int r = sr; r < sr+l; r++) {
			for (int c = sc; c < sc+l; c++) {
				map[r][c] = true;
			}
		}
		
		return true;
	}
	
	private static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

	static class Square {
		int l, cnt;

		public Square(int l, int cnt) {
			this.l = l;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Square [l=" + l + ", cnt=" + cnt + "]";
		}
	}
}
