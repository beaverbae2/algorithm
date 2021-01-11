package A2021_01_11;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbar
 * @see https://herong.tistory.com/entry/BOJ-14391-%EC%A2%85%EC%9D%B4-%EC%A1%B0%EA%B0%81-Java
 *
 */

public class BOJ_14391_종이조각_Solution {
	static int[][] map;
	static int[][] board;
	static int answer, R, C, N;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		board = new int[R][C];
		N = R * C;
		
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		pick(0);
		System.out.println(answer);
	}


	private static void pick(int k) {
		if (k == N) {
			answer = Math.max(answer, getSum2());
			return;
		}
		
		int r = k / C;
		int c = k % C;
		
		board[r][c] = 1;//가로
		pick(k+1);
	
		board[r][c] = 0;//세로
		pick(k+1);
	}

	private static int getSum2() {
		int sum = 0;
		
		//가로
		for (int r = 0; r < R; r++) {
			int temp = 0;
			for (int c = 0; c < C; c++) {
				if(board[r][c] == 1) {
					temp *= 10;
					temp += map[r][c];
				}else {
					sum += temp;
					temp = 0;
				}
			}
			sum += temp;
		}
		
		//세로
		for (int c = 0; c < C; c++) {
			int temp = 0;
			for (int r = 0; r < R; r++) {
				if (board[r][c] == 0) {
					temp *= 10;
					temp += map[r][c];
				}else {
					sum += temp;
					temp = 0;
				}
			}
			sum += temp;
		}
		
		return sum;
	}

	private static int getSum() {
		int sum = 0;
		boolean[][] visited = new boolean[R][C];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!visited[i][j]) {
					StringBuilder sb = new StringBuilder();
					visited[i][j] = true;
					sb.append(map[i][j]);
					int r = i;
					int c = j;
					
					if (board[i][j] == 1) {//가로
						for (int k = 0; k < map[i].length; k++) {
							int nr = r;
							int nc = c+(k+1);
						
							if (isInMap(nr, nc) && !visited[nr][nc]) {
								if (board[r][c] == board[nr][nc]) {
									visited[nr][nc] = true;
									sb.append(map[nr][nc]);
								}else {
									sum += Integer.parseInt(sb.toString());
									break;
								}
							}else {
								sum += Integer.parseInt(sb.toString());
								break;
							}
						}
					}else {
						for (int k = 0; k < map.length; k++) {
							int nr = r+(k+1);
							int nc = c;
						
							if (isInMap(nr, nc) && !visited[nr][nc]) {
								if (board[r][c] == board[nr][nc]) {
									visited[nr][nc] = true;
									sb.append(map[nr][nc]);
								}else {
									sum += Integer.parseInt(sb.toString());
									break;
								}
							}else {
								sum += Integer.parseInt(sb.toString());
								break;
							}
						}
					}
				}
			}
		}
		
		return sum;
	}
	
	private static boolean isInMap(int nr, int nc) { 
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
