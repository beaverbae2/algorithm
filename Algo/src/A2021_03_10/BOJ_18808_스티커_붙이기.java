package A2021_03_10;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 회전에서 객체 참조(지역 변수)를 잘못해서, 시간을 많이 잡아 먹었다
 * 58MIN
 * @author beaverbae
 *
 */

public class BOJ_18808_스티커_붙이기 {
	static int[][] map;
	static int cnt;
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
	
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[R][C];
			
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			boolean isOk = false;
			
			for (int l = 0; l < 4; l++) {
				R = sticker.length;
				C = sticker[0].length;
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						isOk = check(sticker, r, c, R, C);
						if (isOk) break;
					}
				
					if (isOk) break;
				}
				
				if (isOk) {
					break;
				}
				sticker = rotate(sticker, R, C);
			}
		}
		
		System.out.println(cnt);
	}
	
	private static boolean check (int[][] sticker, int sr, int sc, int R, int C) {
		// 왼쪽 위 부터
		for (int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				int nr = sr + r;
				int nc = sc + c;
			
				if (!isInMap(nr, nc) || (map[nr][nc] == 1 && sticker[r][c] == 1)) return false;
			
			}
		}
		
		for (int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				int nr = sr + r;
				int nc = sc + c;
				
				if (map[nr][nc] == 0 && sticker[r][c] == 1) {
					map[nr][nc] = sticker[r][c];
					cnt++;
				}
			}
		}
		
		return true;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr < N && nc>=0 && nc < M;
	}
	
	private static int[][] rotate(int[][] sticker, int R, int C) {
		int[][] next_sticker = new int[C][R];	
		
		for (int c = 0; c < C; c++) {
			for (int r = R-1; r >= 0; r--) {
				next_sticker[c][(R-1)-r] = sticker[r][c];
			}
		}
		
		return next_sticker;
	}
}
