package A2021_04_20;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 80MIN
 * 오래 걸린 이유 : dirs에서 북쪽과 남쪽 좌표 거꾸로 함.... 레잔도...
 * @author beave
 *
 */

public class BOJ_2174_로봇_시뮬레이션 {
	static int R, C;
	static int N, M;
	static Robot[] robots;
	static int[][] map;
	static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
	static HashMap<Character, Integer> dirs_map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
	
		map = new int[R][C];
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robots = new Robot[N+1];
		
		dirs_map = new HashMap<>();
		dirs_map.put('N', 0);
		dirs_map.put('E', 1);
		dirs_map.put('S', 2);
		dirs_map.put('W', 3);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			
			int c = x-1;
			int r = R-y;
			int dir = dirs_map.get(ch);
			
			robots[i] = new Robot(r, c, dir);
			map[r][c] = i;
		}
		
		boolean isGo = true;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {// 명령어
			st = new StringTokenizer(br.readLine());
			
			if (!isGo) continue;
			
			int idx = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			
			int sr = robots[idx].r;
			int sc = robots[idx].c;
			
			int r = sr;
			int c = sc;
			int dir = robots[idx].dir;
			
			for (int j = 0; j < cnt; j++) {
				if (command == 'L') {
					dir -= 1;
					if (dir == -1) dir = 3;
				} else if (command == 'R') {
					dir += 1;
					if (dir == 4) dir = 0;
					
				} else {// F : 이동
					int nr = r + dirs[dir][0];
					int nc = c + dirs[dir][1];
					
					if (!isInWall(nr, nc)) {// 벽에 부딫힌 경우
						isGo = false;
						sb.append("Robot ").append(idx).append(" crashes into the wall").append("\n");
						break;
					} else if (map[nr][nc] != 0) {// 다른 로봇과 부딫힌 경우
						isGo = false;
						sb.append("Robot ").append(idx).append(" crashes into robot ").append(map[nr][nc]).append("\n");
						break;
					}
					r = nr;
					c = nc;
				}
			}
			
			// 이동 완료 후 
			if (isGo) {
				map[sr][sc] = 0;
				map[r][c] = idx;
				robots[idx].r = r;
				robots[idx].c = c;
				robots[idx].dir = dir;
			}
		}
		
		if (!isGo) {
			System.out.println(sb.toString());
		} else {
			System.out.println("OK");
		}
	}
	
	private static boolean isInWall(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
	
	static class Robot {
		int r, c, dir;

		public Robot(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Robot [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
	}
}
