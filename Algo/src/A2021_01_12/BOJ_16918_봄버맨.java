package A2021_01_12;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * @author beaverbae
 *
 */

public class BOJ_16918_봄버맨 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int time = 0;
		
		int[][] map = new int[R][C];
		int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				char ch = str.charAt(j);
				if(ch == 'O') {
					map[i][j] = 0;
				}else {
					map[i][j] = -1;
				}
			}
		}
		
		time++;
		
		while(time < N) {
			time++;
			
			if (time % 2 == 0) {//폭탄 설치
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						if (map[i][j] == -1) map[i][j] = time;
					}
				}
				
			}else {//폭탄 폭발
				boolean[][] visited = new boolean[R][C];
				for (int r = 0; r < map.length; r++) {
					for (int c = 0; c < map[r].length; c++) {
						if (time - map[r][c] == 3) {
							visited[r][c] = true;
							for (int d = 0; d < dirs.length; d++) {
								int nr = r + dirs[d][0];
								int nc = c + dirs[d][1];
								
								if (nr<0||nr>=R||nc<0||nc>=C) continue;
								
								visited[nr][nc] = true;
							}
						}
					}
				}
				
				for (int r = 0; r < map.length; r++) {
					for (int c = 0; c < map[r].length; c++) {
						if (!visited[r][c]) continue;
						
						map[r][c] = -1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == -1) {
					sb.append('.');
				}else {
					sb.append('O');
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
