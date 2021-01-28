package A2020_12_14;

import java.util.*;
import java.io.*;


public class BOJ_17822_원판돌리기 {
	static int[][] onePans;
	static int[][] rotate;
	static int R, C, T;
	static int[][] dirs = {{0, 1}, {0, -1}};//시계, 반시계
	static int[][] dirs2 = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		onePans = new int[R][C];
		rotate = new int[T][3];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				onePans[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				rotate[t][i] = Integer.parseInt(st.nextToken());
			}
		}
		
	
		rotatePan();
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				answer += onePans[r][c];
			}
		}
		System.out.println(answer);
		
	}

	private static void rotatePan() {
		for (int t = 0; t < T; t++) {
			int x = rotate[t][0];
			int d = rotate[t][1];
			int k = rotate[t][2];
			
			for (int r = 0; r < R; r++) {
				if((r+1)%x != 0) continue;
				
				int[] nextPan = new int[C];
				
				for (int c = 0; c < C; c++) {
					int nc = c + dirs[d][1]*k;
					
					if (nc>=C) nc -= C;
					else if(nc<0) nc += C;
				
					nextPan[nc] = onePans[r][c];
				}
				onePans[r] = nextPan;
			}
			
			arrangePan();
			
		}
	}
	
	
	//정리
	private static void arrangePan() {
		int[][] nextOnePans = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				nextOnePans[r][c] = onePans[r][c];
			}
		}
		
		boolean flag = false;
		boolean[][] adj = new boolean[R][C]; 
		
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(onePans[r][c] == 0) continue;// 수가 남아있지X
				
				if(checkAdj(r, c)) {
					flag = true;
					adj[r][c] = true;
				}
			}
		}
		
		if(flag) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(adj[r][c]) nextOnePans[r][c] = 0;
				}
			}
		}else {
			int panSum = 0;
			int panCnt = 0;
			double panAvg = 0.0;
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(onePans[r][c] == 0) continue;
					panSum += onePans[r][c];
					panCnt++;
				}
			}
			panAvg = (double) ((double) (panSum) /(double) (panCnt));
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(onePans[r][c] == 0) continue;
					
					if(onePans[r][c] > panAvg) {
						nextOnePans[r][c] -= 1;
					}else if(onePans[r][c] < panAvg) {
						nextOnePans[r][c] += 1;
					}
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				onePans[r][c] = nextOnePans[r][c];
			}
		}
	}
	
	//인접하면서 같은 수 파악
	private static boolean checkAdj(int r, int c) {
		for (int d = 0; d < dirs2.length; d++) {
			int nr = r + dirs2[d][0];
			int nc = c + dirs2[d][1];
			
			if (nr<0||nr>=R) continue;
			if (nc<0) nc = C-1;
			if (nc>=C) nc = 0;
			
			if(onePans[r][c] == onePans[nr][nc]) return true;
		}
		return false;
	}	
}
