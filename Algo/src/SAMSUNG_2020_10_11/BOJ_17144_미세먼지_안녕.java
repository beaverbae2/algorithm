package SAMSUNG_2020_10_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//인덱스 접근을 잘 하자

public class BOJ_17144_미세먼지_안녕 {
	static int[][] map;
	static int R,C,T,answer;
	static int cleaner_R1,cleaner_R2,cleaner_C1,cleaner_C2;
	
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					if(cleaner_R1==0) {
						cleaner_R1 = i;
						cleaner_C1 = j;
					}else {
						cleaner_R2 = i;
						cleaner_C2 = j;
					}
				}
			}
		}
		while(true) {
			if(T==0) break;
			spread();//확산
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			clear();//정화
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			T--;
			//break;
		}
		
		//방에 남아 있는 미세먼지의 양을 계산
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j]>0) answer += map[i][j];
			}
		}
		System.out.println(answer);
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}
	
	private static void spread() {
		//미세먼지가 있는 칸을 선택
		int[][] copy_map = new int[R][C];
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(map[r][c]<=0) continue;
			
				int spread_dust = map[r][c]/5;
				int spread_count = 0;//확산된 방향 개수
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r+dirs[d][0];
					int nc = c+dirs[d][1];
					
					//확산의 조건(칸이 존재, 공기청정기가 아닌 곳)
					if(isInMap(nr, nc)&&map[nr][nc]!=-1) {
						spread_count++;
						copy_map[nr][nc] += spread_dust;
					}
				}
				map[r][c] -= spread_dust*spread_count;//
			}
		}
//		for (int i = 0; i < copy_map.length; i++) {
//			System.out.println(Arrays.toString(copy_map[i]));
//		}
//		System.out.println();
//		for (int i = 0; i < copy_map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
		
		for (int i = 0; i < copy_map.length; i++) {
			for (int j = 0; j < copy_map[i].length; j++) {
				map[i][j] += copy_map[i][j];
			}
		}
	}

	private static void clear() {
		clearbot1(cleaner_R1);
		clearleft1();
		cleartop1(cleaner_R1);
		clearright1();
		
		cleartop2(cleaner_R2);
		clearleft2();
		clearbot2(cleaner_R2);
		clearright2();
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static void cleartop2(int row) {
		for (int i = row+1; i < map.length-1; i++) {
			map[i][0] = map[i+1][0];
			map[i+1][0] = 0;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
	}

	private static void clearright2() {
		int r = cleaner_R2;
		for (int i = map[r].length-1; i > cleaner_C2+1; i--) {
			map[r][i] = map[r][i-1];
			map[r][i-1] = 0;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static void clearbot2(int row) {
		int c = map[0].length-1;
		for (int i = map.length-1; i > row; i--) {
			map[i][c] = map[i-1][c];
			map[i-1][c] = 0;
		}
		
	}

	private static void clearleft2() {
		int r = map.length-1;
		for (int i = 0; i < map[r].length-1; i++) {
			map[r][i] = map[r][i+1];
			map[r][i+1] = 0;
		}
	}

	private static void clearright1() {
		int r = cleaner_R1;
		for (int i = map[r].length-1; i > cleaner_C1+1; i--) {
			map[r][i] = map[r][i-1];
			map[r][i-1] = 0;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static void cleartop1(int row) {
		int c = map[0].length-1;
		for (int i = 0; i < row; i++) {
			map[i][c] = map[i+1][c];
			map[i+1][c] = 0;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static void clearleft1() {
		for (int i = 0; i < map[0].length-1; i++) {
			map[0][i] = map[0][i+1];
			map[0][i+1] = 0;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static void clearbot1(int row) {
		for (int i = cleaner_R1-2; i >= 0; i--) {
			map[i+1][0] = map[i][0];
			map[i][0] = 0;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}