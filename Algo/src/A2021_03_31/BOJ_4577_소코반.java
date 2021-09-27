package A2021_03_31;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 46MIN
 * 후기 : 좀 더 차분하게 풀자
 * @author beaverbae
 *
 */

public class BOJ_4577_소코반 {
	static char[][] map;
	static char[] commands;
	static int R, C;
	static int r, c;// 플레이어 위치
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; // 상, 하, 좌, 우
	static HashMap<Character, Integer> dirs_map;
	static int completeCnt;// 목표지점에 도착한 박스의 수
	
	static StringBuilder sb;
	static int gameNum = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		dirs_map = new HashMap<>();
		dirs_map.put('U', 0);
		dirs_map.put('D', 1);
		dirs_map.put('L', 2);
		dirs_map.put('R', 3);
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
		
			if (R == 0 && C == 0) break;
			sb.append("Game ").append(gameNum++).append(": ");
			
			completeCnt = 0;
			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'w' || map[i][j] == 'W' ) {// 바로 생각 못한 부분2
						r = i;
						c = j;
					} else if (map[i][j] == 'b' || map[i][j] == 'B') {// 바로 생각 못한 부분2
						completeCnt++;
					}
				}
			}
			
			boolean flag = false;
			commands = br.readLine().toCharArray();
			for (char command : commands) {
				movePlayer(command);
				if (check()) {
					flag = true;
					break;
				}
			}
			
			if (flag) sb.append("complete").append("\n");
			else sb.append("incomplete").append("\n");
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static boolean check() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'B') cnt++;
			}
		}
		
		if (cnt == completeCnt) return true;
		return false;
	}

	private static void movePlayer(char command) {
		int d = dirs_map.get(command);// 방향
		
		// 다음 좌표
		int nr = r + dirs[d][0];
		int nc = c + dirs[d][1];
		
		char player = map[r][c];
		char next = getNext(player);// 바로 생각 못한 부분1
		
		if (map[nr][nc] == '#') return;
		else if (map[nr][nc] == '.') {
			map[r][c] = next;
			map[nr][nc] = 'w';
		} else if (map[nr][nc] == 'b') {// 그냥 박스
			// 다다음 좌표
			int nnr = nr + dirs[d][0];
			int nnc = nc + dirs[d][1];
			
			// 박스가 이동 가능한지 검사
			if (map[nnr][nnc] == '#' || map[nnr][nnc] == 'b' || map[nnr][nnc] == 'B') return;
			else if (map[nnr][nnc] == '.') {
				map[r][c] = next;
				map[nr][nc] = 'w';
				map[nnr][nnc] = 'b';
			} else if (map[nnr][nnc] == '+') {
				map[r][c] = next;
				map[nr][nc] = 'w';
				map[nnr][nnc] = 'B';
			}
			
		} else if (map[nr][nc] == 'B') {
			// 다다음 좌표
			int nnr = nr + dirs[d][0];
			int nnc = nc + dirs[d][1];
			
			// 박스가 이동 가능한지 검사
			if (map[nnr][nnc] == '#' || map[nnr][nnc] == 'b' || map[nnr][nnc] == 'B') return;
			else if (map[nnr][nnc] == '.') {
				map[r][c] = next;
				map[nr][nc] = 'W';
				map[nnr][nnc] = 'b';
			} else if (map[nnr][nnc] == '+') {
				map[r][c] = next;
				map[nr][nc] = 'W';
				map[nnr][nnc] = 'B';
			}
		} else if (map[nr][nc] == '+') {
			map[r][c] = next;
			map[nr][nc] = 'W';
		}
		
		r = nr;
		c = nc;
	}

	private static char getNext(char player) {
		if (player == 'w') {
			return '.';
		}
		return '+';
	}
}
