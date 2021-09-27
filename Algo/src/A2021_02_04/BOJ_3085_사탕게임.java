package A2021_02_04;

import java.util.*;
import java.io.*;

public class BOJ_3085_사탕게임 {
	static int[][] dirs = {{0,1},{1,0}};
	static char[][] map;
	static int N;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		answer = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d = 0; d < dirs.length; d++) {
					int nr = r + dirs[d][0];
					int nc = c + dirs[d][1];
					
					// 교환이 불가능한 경우
					if (!isInMap(nr, nc)) {
						continue;
					}
					
					// 사탕 교환
					swap(r, c, nr, nc);
					
					answer = Math.max(answer, getMaxSeries());
							
					// 원래 대로 돌려 놓기
					swap(r, c, nr, nc);
				}
			}
		}
		System.out.println(answer);
	}
	
	private static int getMaxSeries() {
		int result = 0;
		
		// 행
		for (int r = 0; r < N; r++) {
			int temp = 1;
			for (int c = 1; c < N; c++) {
				if (map[r][c-1] == map[r][c]) temp++;
				else {
					result = Math.max(result, temp);
					temp = 1;
				}
			}
			result = Math.max(result, temp);
		}
		
		// 열
		for (int c = 0; c < N; c++) {
			int temp = 1;
			for (int r = 1; r < N; r++) {
				if (map[r-1][c] == map[r][c]) temp++;
				else {
					result = Math.max(result, temp);
					temp = 1;
				}
			}
			result = Math.max(result, temp);
		}
		
		return result;
	}

	private static void swap(int r, int c, int nr, int nc) {
		char candy = map[r][c];
		map[r][c] = map[nr][nc];
		map[nr][nc] = candy;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
