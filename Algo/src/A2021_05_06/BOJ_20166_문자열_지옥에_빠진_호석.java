package A2021_05_06;

import java.util.*;
import java.io.*;

/**
 * 29MIN
 * 어려웠던 부분
 * - 중복 방문 가능 -> 문제에서 주어진 메모리가 넉넉해서 BFS로 진행
 * - BFS -> 신이 좋아하는 문자열의 크기와 Queue의 원소로 들어있는 문자열이 같을 떄 Queue의 크기 리턴
 * - 신이 좋아하는 문자열 -> 중복된것 존재하므로, map 활용
 * @author beaverbae
 *
 */

public class BOJ_20166_문자열_지옥에_빠진_호석 {
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
	static int R, C, K;
	static char[] arr;
	static char[][] map;
	static HashMap<String, Integer> hashMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		hashMap = new HashMap<>();
		for (int i = 0; i < K; i++) {
			String str = br.readLine();
			if (hashMap.containsKey(str)) {
				sb.append(hashMap.get(str)).append("\n");
				continue;
			}
			
			arr = str.toCharArray();
			
			int answer = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (arr[0] != map[r][c]) continue;
					
					 answer += bfs(r,c);
				}
			}
			
			hashMap.put(str, answer);
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int bfs(int sr, int sc) {
		int result = 0;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(sr, sc, arr[0]+""));
		
		while (!q.isEmpty()) {
			Node cur = q.peek();
			int idx = cur.word.length();
			
			if (idx == arr.length) {
				result = q.size();
				break;
			}
			
			q.poll();
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = cur.r + dirs[d][0];
				int nc = cur.c + dirs[d][1];
				
				nr = getNextRow(nr);
				nc = getNextCol(nc);
			
				if (arr[idx] == map[nr][nc]) {
					q.offer(new Node(nr, nc, cur.word+arr[idx]));
				}
			}
		}
		
 		return result;
	}

	private static int getNextRow(int nr) {
		if (nr < 0) nr = R-1;
		else if (nr >= R) nr = 0;
		return nr;
	}
	
	private static int getNextCol(int nc) {
		if (nc < 0) nc = C-1;
		else if (nc >= C) nc = 0;
		return nc;
	}
	
	static class Node {
		int r, c;
		String word;
		
		public Node(int r, int c, String word) {
			this.r = r;
			this.c = c;
			this.word = word;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", word=" + word + "]";
		}
	}
}
