package A2021_01_06;

import java.util.*;

/**
 * 
 * @author beaverbae
 * @see https://programmers.co.kr/learn/courses/30/lessons/1836
 *
 */

public class PGS_리틀_프렌즈_사천성_Solution {
	private char[][] map;
	private HashMap<Integer, Character> hm = new HashMap<>();
	private HashMap<Character, Pair> startMap = new HashMap<>();
	private HashMap<Character, Pair> endMap = new HashMap<>();
	private boolean[] visited;
	private int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};//동남서북
	private String answer = "";
	
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
	
	static class Elem {
		int r, c, dir;
		boolean flag;
		
		public Elem(int r, int c, int dir, boolean flag) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.flag = flag;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", dir=" + dir + ", flag=" + flag + "]";
		}
	}
	
	public String solution(int m, int n, String[] board) {
		///초기화
		map = new char[m][n];
		TreeSet<Character> set = new TreeSet<>();
		
		for (int r = 0; r < board.length; r++) {
			String str = board[r];
			for (int c = 0; c < str.length(); c++) {
				char ch = str.charAt(c);
				map[r][c] = ch;
				if(ch >= 'A' && ch <= 'Z') {
					set.add(ch);
					if (startMap.get(ch) == null) {
						startMap.put(ch, new Pair(r, c));
					}else if(endMap.get(ch) == null) {
						endMap.put(ch, new Pair(r, c));
					}
				}
			}
		}
		
		int idx = 0;
		for (char ch : set) {
			hm.put(idx, ch);
			idx++;
		}
		
		///////////////		THIS IS SOLUTION PART		///////////////
		StringBuilder sb = new StringBuilder();
		boolean isRemoved = true;
		
		//되는거 부터 순차적으로 삭제
		while(true) {
			if(!isRemoved) break;
			isRemoved = false;
			
			Iterator<Integer> iter = hm.keySet().iterator();
			while(iter.hasNext()) {
				int key = iter.next();
				char ch = hm.get(key);
				Pair start = startMap.get(ch);
				Pair end = endMap.get(ch);
				
				int sr = start.r;
				int sc = start.c;
				int er = end.r;
				int ec = end.c;
				
				
				if (bfs(sr, sc, er, ec)) {
					map[sr][sc] = '.';
					map[er][ec] = '.';
					
					hm.remove(key);
					sb.append(ch);
					isRemoved = true;
					break;
				}
				
			}
			
		}
		
		if (hm.size()!=0) return "IMPOSSIBLE";
		return sb.toString();
		/////////////////////////////////////////////////////////////
	}
	
	public boolean bfs(int sr, int sc, int er, int ec) {
		
		Queue<Elem> q = new LinkedList<>();
		int R = map.length;
		int C = map[0].length;
		boolean[][][] qVisited = new boolean[4][R][C];
		
		for (int d = 0; d < dirs.length; d++) {
			int nr = sr + dirs[d][0];
			int nc = sc + dirs[d][1];
			
			if(!isOk(sr, sc, nr, nc, R, C)) continue;
			
			q.offer(new Elem(nr, nc, d, false));
			qVisited[d][nr][nc] = true;
		}
		
		while(!q.isEmpty()) {
			Elem e = q.poll();
			int r = e.r;
			int c = e.c;
			int dir = e.dir;
			boolean flag = e.flag;
			
			if (r==er && c==ec) return true;
		
			if (!flag) {
				for (int i = -1; i <= 1; i+=2) {
					int next_dir = (dir + i + 4) % 4;
					int nr = r+dirs[next_dir][0];
					int nc = c+dirs[next_dir][1];
					
					if (!isOk(sr, sc, nr, nc, R, C)) continue;
					if (qVisited[next_dir][nr][nc]) continue;
					
					q.offer(new Elem(nr, nc, next_dir, true));
					qVisited[next_dir][nr][nc] = true;
				}
			}
			
			int nr = r + dirs[dir][0];
			int nc = c + dirs[dir][1];
			
			
			if (!isOk(sr, sc, nr, nc, R, C)) continue;
			if (qVisited[dir][nr][nc]) continue;
			
			q.offer(new Elem(nr, nc, dir, flag));
			qVisited[dir][nr][nc] = true;
		}
		
		
		return false;
	}
	
	public boolean isOk(int sr, int sc, int nr, int nc, int R, int C) {
		return nr>=0 && nr<R && nc>=0 && nc<C && (map[nr][nc] == '.' || map[nr][nc] == map[sr][sc]);
	}

	public static void main(String[] args) {
		System.out.println(new PGS_리틀_프렌즈_사천성_Solution().solution(3, 3, new String[] {"DBA", "C*A", "CDB"}));
		System.out.println(new PGS_리틀_프렌즈_사천성_Solution().solution(2, 4, new String[] {"NRYN", "ARYA"}));
		System.out.println(new PGS_리틀_프렌즈_사천성_Solution().solution(4, 4, new String[] {".ZI.", "M.**", "MZU.", ".IU."}));
		System.out.println(new PGS_리틀_프렌즈_사천성_Solution().solution(2, 2, new String[] {"AB", "BA"}));
	}
}
