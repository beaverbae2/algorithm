package A2021_02_15;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 * @see https://dundung.tistory.com/67
 */

public class BOJ_1525_퍼즐_solution {
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static final int R = 3, C = 3;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int start = 0;
		
		for (int i = 0; i < R; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 0) n = 9;
				start = start*10+n;
			}
		}
		
		// bfs 진행
		Queue<Integer> q = new LinkedList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		map.put(start, 0);
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			String s_cur = Integer.toString(cur);
			int nine_idx = s_cur.indexOf('9');
			
			int r = nine_idx/3;
			int c = nine_idx%3;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isInMap(nr, nc)) continue;
				
				int target_idx = 3*nr+nc;
				
				// 위치 바꾸기
				char ch = s_cur.charAt(target_idx);
				StringBuilder sb = new StringBuilder();
				sb.append(cur);
				sb.setCharAt(nine_idx, ch);
				sb.setCharAt(target_idx, '9');
				
				int next = Integer.parseInt(sb.toString());
				
				if (!map.containsKey(next)) {
					q.offer(next);
					map.put(next, map.get(cur)+1);
				}
			}
		}
		
		if (map.containsKey(123456789)) {
			System.out.println(map.get(123456789));
		}else System.out.println(-1);
	}
	
	static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
