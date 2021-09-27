package A2021_03_27;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 108MIN
 * 오래걸린 이유 : ddir를 써야되는데 dir을 써버렸다...
 * @author beaverbae
 *
 */

public class BOj_4574_스도미노쿠 {
	static int[][] map;
	static List<Integer> list;
	static final int R = 9, C = 9, MAX = 81;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};// 스도쿠 만들기용
	static int[][] ddirs = {{0,1}, {1,0}};// 도미노 용
	static int N;
	static StringBuilder sb;
	static boolean flag;
	static LinkedHashMap<Integer, Integer> hmap;
	static LinkedHashSet<Integer> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int num = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			flag = false;
			if (N == 0) break;
			sb.append("Puzzle ").append(num).append("\n");
			map = new int[R][C];
			list = new ArrayList<>();
			hmap = new LinkedHashMap<>();
			set = new LinkedHashSet<>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				String pos1 = st.nextToken();
				int r1 = pos1.charAt(0)-'A';
				int c1 = pos1.charAt(1)-'1';
				
				map[r1][c1] = n1;
				
				int n2 = Integer.parseInt(st.nextToken());
				String pos2 = st.nextToken();
				int r2 = pos2.charAt(0)-'A';
				int c2 = pos2.charAt(1)-'1';
				
				map[r2][c2] = n2;
				
				int idx1 = r1*R+c1;
				int idx2 = r2*R+c2;
				hmap.put(idx1, idx2);
				hmap.put(idx2, idx1);
				
				int min = Math.min(n1, n2);
				int max = Math.max(n1, n2);
				set.add((10*min+max));
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= 9; n++) {
				String pos = st.nextToken();
				int r = pos.charAt(0)-'A';
				int c = pos.charAt(1)-'1';
				int idx = r*R+c;
				
				map[r][c] = n;
				hmap.put(idx, -1);
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 0) {
						int idx = i*R+j;
						list.add(idx);
					}
				}
			}
			dfs(0);
			num++;
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int i) {
		if (flag) return;
		
		if (i == list.size()) {
			domino(0);
			if (flag) {
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						sb.append(map[r][c]);
					}
					sb.append("\n");
				}
			}
			
			return;
		}
		
		int idx = list.get(i);
		int r = idx/C;
		int c = idx%C;
		
		for (int n = 1; n <= 9; n++) {
			if (!checkRow(r, c, n) || !checkCol(r, c, n) || !checkSquare(r, c, n)) continue;
		
			map[r][c] = n;
			dfs(i+1);
			map[r][c] = 0;
		}
	}
	
	private static void domino(int idx) {
		if (flag) return;
		
		if (idx == MAX) {
			flag = true;
			return;
		}
		
		if (hmap.containsKey(idx)) {
			domino(idx+1);
		} else {
			int r = idx/C;
			int c = idx%C;
			int n1 = map[r][c];
			
			// 도미노 만드는 부분
			for (int d = 0; d < ddirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				int nidx = nr*R + nc;
				if (!isIn(nr, nc) || hmap.containsKey(nidx)) continue;
				
				int n2 = map[nr][nc];
				int min = Math.min(n1, n2);
				int max = Math.max(n1, n2);
				int n = 10*min+max;
				if (set.contains(n)) continue;
				
				set.add(n);
				hmap.put(idx, nidx);
				hmap.put(nidx, idx);
				domino(idx+1);
				set.remove(n);
				hmap.remove(idx);
				hmap.remove(nidx);
			}
		}
		
		
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}

	private static boolean checkSquare(int sr, int sc, int n) {
		int r = getCenter(sr);
		int c = getCenter(sc);
		
		if (map[r][c] == n) return false;
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if (map[nr][nc] == n) return false;
		}
		
		
		return true;
	}

	private static boolean checkCol(int sr, int sc, int n) {
		// 열 고정
		for (int r = 0; r < R; r++) {
			if (map[r][sc] == n) return false;
		}
		
		return true;
	}

	private static boolean checkRow(int sr, int sc, int n) {
		// 행 고정
		for (int c = 0; c < C; c++) {
			if (map[sr][c] == n) return false;
		}
		
		return true;
	}
	
	private static int getCenter(int r) {
		if (r < 3) return 1;
		else if (r < 6) return 4;
		else if (r < 9) return 7;
		return -1;
	}
}
