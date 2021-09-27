package A2021_04_19;

import java.util.*;
import java.io.*;
/**
 * Simulation
 * 52MIN
 * 오래걸린 이유
 * - 중간에 게임 끝난 경우 몇번쨰 게임에서 끝이 났는지 출력해야되는데 문자 그대로 "kraj X" 라고 출력
 * - 미친 아두이노 이동시 종수와 같은 좌표에 있는 검사하는 것 그리고 폭발 검사하는 "시점"
 * - 미친 아두이노들의 좌표를 어떤 자료구조로 표현할 지 (list -> set -> map 순으로 바뀜) 
 * @author beaverbae
 *
 */
public class BOJ_8972_미친_아두이노 {
	static char[][] map;
	static int R, C;
	static int jongsu_R, jongsu_C;
	static HashMap<Integer, Integer> arduinos;
	static int[][] dirs = {{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		arduinos = new HashMap<>();
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				char ch = str.charAt(c);
			
				if (ch == 'I') {
					jongsu_R = r;
					jongsu_C = c;
				} else if (ch == 'R') {
					arduinos.put((r*C+c), 1);
				}
			}
		}
		
		// 게임 시작
		boolean flag = true;
		char[] move = br.readLine().toCharArray();
		int X = -1;
		for (int i = 0; i < move.length; i++) {
			int d = move[i] - '1';
			// 종수가 다음좌표로 이동 가능한지 검사
			if (!isMoveJongsu(d)) {
				flag = false;
				X = i+1;
				break;
			}
			
			// 미친 아두이노 이동
			boolean isCrashJongSu = moveArduinos();
			if (isCrashJongSu) {
				flag = false;
				X = i+1;
				break;
			}
		}
		
		if (!flag) {
			System.out.println("kraj "+X);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < R; i++) {
				Arrays.fill(map[i], '.');
			}
			
			map[jongsu_R][jongsu_C] = 'I';
			for (int idx : arduinos.keySet()) {
				int arduino_R = idx / C;
				int arduino_C = idx % C;
				map[arduino_R][arduino_C] = 'R';
			}
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
		}
		
	}
	
	private static boolean moveArduinos() {
		HashMap<Integer, Integer> next_arduinos = new HashMap<>();
		
		// 모든 아두이노 이동
		Iterator<Integer> iter = arduinos.keySet().iterator();
		while (iter.hasNext()) {
			int arduino_idx = iter.next();
			int arduino_R = arduino_idx / C;
			int arduino_C = arduino_idx % C;
			
			int dist = Integer.MAX_VALUE;
			int next_arduino_R = -1;
			int next_arduino_C = -1;
			
			// 이동할 좌표 구하기
			for (int d = 0; d < dirs.length; d++) {
				int temp_dist = Math.abs(arduino_R + dirs[d][0] - jongsu_R) + Math.abs(arduino_C + dirs[d][1] - jongsu_C);
				if (dist > temp_dist) {
					dist = temp_dist;
					next_arduino_R = arduino_R + dirs[d][0];
					next_arduino_C = arduino_C + dirs[d][1];
				}
			}
			
			int next_arduino_idx = next_arduino_R * C + next_arduino_C;
			if (!next_arduinos.containsKey(next_arduino_idx)) {
				next_arduinos.put(next_arduino_idx, 1);
			} else {
				next_arduinos.put(next_arduino_idx, next_arduinos.get(next_arduino_idx)+1);
			}
			
			// 미친 아두이노가 종수와 같은 좌표에 있는지 검사
			if (jongsu_R == next_arduino_R && jongsu_C == next_arduino_C) return true;
		}
		
		// 미친 아두이노 여럿이 같은 좌표에 있으면 폭발
		arduinos = new HashMap<>();
		iter = next_arduinos.keySet().iterator();
		while (iter.hasNext()) {
			int next_arduino_idx = iter.next();
			int cnt = next_arduinos.get(next_arduino_idx);
			if (cnt == 1) {
				arduinos.put(next_arduino_idx, cnt);
			}
		}
		
		return false;
	}

	private static boolean isMoveJongsu(int d) {
		jongsu_R += dirs[d][0];
		jongsu_C += dirs[d][1];
		
		if (arduinos.containsKey(jongsu_R * C + jongsu_C)) return false;
		return true;
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
