package A2021_03_12;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 52MIN
 * 좀 더 효율적인 방법을 고민해 보자
 * @author beaverbae
 *
 */

public class BOJ_3967_매직_스타 {
	static char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
	static HashSet<Character> visited;
	static char[][] map;
	static char R = 5, C = 9, N = 25;;
	static List<Pair> path;
	static List<Pair> check_path;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][C];
		visited = new HashSet<>();
		path = new ArrayList<>();
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == '.') {
					continue;
				} else if (map[i][j] == 'x') {
					path.add(new Pair(i,j));
				} else {
					visited.add(map[i][j]);
				}
			}
		}
		
		check_path = new ArrayList<>();
		init();
		
		dfs(0);	
	}
	
	private static void init() {
		check_path.add(new Pair(0,4));
		check_path.add(new Pair(1,5));
		check_path.add(new Pair(2,6));
		check_path.add(new Pair(3,7));
		
		check_path.add(new Pair(1,1));
		check_path.add(new Pair(1,3));
		check_path.add(new Pair(1,5));
		check_path.add(new Pair(1,7));
		
		check_path.add(new Pair(0,4));
		check_path.add(new Pair(1,3));
		check_path.add(new Pair(2,2));
		check_path.add(new Pair(3,1));
		
		check_path.add(new Pair(1,1));
		check_path.add(new Pair(2,2));
		check_path.add(new Pair(3,3));
		check_path.add(new Pair(4,4));
		
		check_path.add(new Pair(1,7));
		check_path.add(new Pair(2,6));
		check_path.add(new Pair(3,5));
		check_path.add(new Pair(4,4));
		
		check_path.add(new Pair(3,1));
		check_path.add(new Pair(3,3));
		check_path.add(new Pair(3,5));
		check_path.add(new Pair(3,7));
		
	}

	private static void dfs(int idx) {
		if (idx == path.size()) {
			if (check()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						sb.append(map[i][j]);
					}
					sb.append("\n");
				}
				System.out.println(sb.toString());
				System.exit(0);
			} 
				
			return;
		}
		
		Pair p = path.get(idx);
		int r = p.r;
		int c = p.c;
		
		
		for (char ch : alpha) {
			if (visited.contains(ch)) continue;
			
			visited.add(ch);
			map[r][c] = ch;
			
			dfs(idx+1);
			visited.remove(ch);
			map[r][c] = 'x';
		}
	}
	
	
	private static boolean check() {
		int cnt = 0;
		
		for (int i = 0; i < check_path.size(); i++) {
			Pair p = check_path.get(i);
			int r = p.r;
			int c = p.c;
			
			cnt += getPoint(map[r][c]);
			
			if ((i+1) % 4 == 0) {
				if (cnt != 26) return false;
				else cnt = 0;
			}
		}
		
		return true;
	}
	
	private static int getPoint(char ch) {
		return ch-'A'+1;
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
