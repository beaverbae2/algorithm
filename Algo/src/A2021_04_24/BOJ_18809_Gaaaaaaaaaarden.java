package A2021_04_24;

import java.util.*;
import java.io.*;

/**
 * Combination, BFS, Memoization
 * 개선한 부분
 * - state와 time을 동시 확인해야 했던거 객체 사용(null 문제, 가독성 문제) -> state와 time을 각각 담당하는 배열 생성
 * - Queue의 값으로 행과 열좌표만 들어감 -> 탐색시 state와 time은 이를 저장하는 각각의 배열에서 값을 가져옴
 * @author beaverbae
 *
 */

public class BOJ_18809_Gaaaaaaaaaarden {
	static int R, C, red, green;
	static int[][] map;
	static List<Pair> list;
	static int[] visited;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		green = Integer.parseInt(st.nextToken());
		red = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			
				if (map[i][j] == 2) {
					list.add(new Pair(i, j));
				}
			}
		}
		
		visited = new int[list.size()];
		
		getGreen(0, 0);
		System.out.println(answer);
	}
	
	private static void getGreen(int start, int cnt) {
		if (cnt == green) {
			getRed(0, 0);
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			if (visited[i] != 0) continue;
			visited[i] = 1;
			getGreen(i+1, cnt+1);
			visited[i] -= 1;
		}
	}

	private static void getRed(int start, int cnt) {
		if (cnt == red) {
			answer = Math.max(answer, move());
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			if (visited[i] != 0) continue;
			visited[i] = 2;
			getRed(i+1, cnt+1);
			visited[i] -= 2;
		}
	}

	private static int move() {
		int result = 0;
		Queue<Pair> q = new LinkedList<>();
		int[][] map_state = new int[R][C];
		int[][] map_time = new int[R][C];
		
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0) continue;
			
			Pair p = list.get(i);
			q.offer(new Pair(p.r, p.c));
			map_state[p.r][p.c] = visited[i];
		}
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int state = map_state[r][c];
			int time = map_time[r][c];
			
			if (map_state[r][c] == 3) continue;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (isIn(nr, nc) && map[nr][nc] != 0) {
					if (map_state[nr][nc] == 0) {
						map_state[nr][nc] = state;
						map_time[nr][nc] = time+1;
						q.offer(new Pair(nr, nc));
					} else if (map_state[nr][nc] < 3) {
						if (map_time[nr][nc] == time+1 && map_state[nr][nc] + state == 3) {
							map_state[nr][nc] = 3;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map_state[i][j] == 3) {
					result++;
				}
			}
		}

		return result;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}
}
