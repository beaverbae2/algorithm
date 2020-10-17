package SAMSUNG_2020_10_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100_2048Easy {
	static int N;
	static int answer = 0;
	static String src = "3\r\n" + 
			"2 2 2\r\n" + 
			"4 4 4\r\n" + 
			"8 8 8";
	static String src2 = "3\r\n" + 
			"2 2 2\r\n" + 
			"4 4 2\r\n" + 
			"4 4 4";
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new StringReader(src2));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
		dfs(map,0);
//		int[][] next_map = new int[N][N];
//		deepcopy(map,next_map);
//		move(next_map,3);
//		concat(next_map, 3);
//		move(next_map,3);
//		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(next_map[i]));
//		}
		System.out.println(answer);
	}

	private static void dfs(int[][] map,int cnt) {
		if(cnt==6) return;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, map[i][j]);
			}
		}
		
		for (int dir = 1; dir <=4; dir++) {//오왼상하
			int[][] next_map = new int[N][N];
			deepcopy(map,next_map);
			move(next_map,dir);
			concat(next_map,dir);
			move(next_map,dir);
			dfs(next_map,cnt+1);
		}
	}

	private static void concat(int[][] next_map, int dir) {
		if(dir==1) {//오
			for (int r = 0; r < N; r++) {
				int next_c = 0;
				for (int c = N-1; c >= 1; c--) {
					if(next_map[r][c]==next_map[r][c-1]) {
						next_map[r][c] *= 2;
						next_map[r][c-1] = 0;
					}
				}
				
			}
		}else if(dir==2) {//왼
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N-1; c++) {
					if(next_map[r][c]==next_map[r][c+1]) {
						next_map[r][c] *= 2;
						next_map[r][c+1] = 0;
					}
				}
			}
		}else if(dir==3) {//상
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N-1; r++) {
					if(next_map[r][c]==next_map[r+1][c]) {
						next_map[r][c] *= 2;
						next_map[r+1][c] = 0;
					}
				}
			}
		}else if(dir==4) {//하
			for (int c = 0; c < N; c++) {
				for (int r = N-1; r >= 1; r--) {
					if(next_map[r][c]==next_map[r-1][c]) {
						next_map[r][c] *= 2;
						next_map[r-1][c] = 0;
					}
				}
			}
		}
	}

	private static void move(int[][] map, int dir) {
		int[][] next_map = new int[N][N];
		if(dir==1) {//오
			for (int r = 0; r < N; r++) {
				int next_c = N-1;
				for (int c = N-1; c >= 0; c--) {
					if(map[r][c]!=0) {
						next_map[r][next_c] = map[r][c];
						next_c--;
					}
				}
			}
		}else if(dir==2) {//왼
			for (int r = 0; r < N; r++) {
				int next_c = 0;
				for (int c = 0; c < N; c++) {
					if(map[r][c]!=0) {
						next_map[r][next_c] = map[r][c];
						next_c++;
					}
				}
			}
		}else if(dir==3) {//상
			for (int c = 0; c < N; c++) {
				int next_r = 0;
				for (int r = 0; r < N; r++) {
					if(map[r][c]!=0) {
						next_map[next_r][c] = map[r][c];
						next_r++;
					}
				}
			}
		}else if(dir==4) {//하
			for (int c = 0; c < N; c++) {
				int next_r = N-1;
				for (int r = N-1; r >= 0; r--) {
					if(map[r][c]!=0) {
						next_map[next_r][c] = map[r][c];
						next_r--;
					}
				}
			}
		}
		deepcopy(next_map, map);
	}

	private static void deepcopy(int[][] map, int[][] next_map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				next_map[i][j] = map[i][j];
			}
		}
	}
}
