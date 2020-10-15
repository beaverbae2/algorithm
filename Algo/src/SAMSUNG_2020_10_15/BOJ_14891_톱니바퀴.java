package SAMSUNG_2020_10_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	static int[][] map;
	static int[][] dirs;
	static int[] rotate_arr;
	static int K;// 회전 횟수
	static int[] points = {1,2,4,8};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][8];
		for (int i = 0; i < map.length; i++) {
			String src = br.readLine();
			for (int j = 0; j < src.length(); j++) {
				map[i][j] = src.charAt(j)-'0';
			}
		}
		K = Integer.parseInt(br.readLine());
		dirs = new int[K][2];//번호 방향
		for (int i = 0; i < dirs.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			dirs[i][0] = n;
			dirs[i][1] = d;
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
//		for (int i = 0; i < dirs.length; i++) {
//			System.out.println(Arrays.toString(dirs[i]));
//		}
		
		//K번 회전 한다
		for (int k = 0; k < K; k++) {
			int n = dirs[k][0];//톱니 번호
			int d = dirs[k][1];//방향
			
			rotate_arr = getRotate(n,d);
			rotate();
		}
		int answer = 0;
		for (int i = 0; i < map.length; i++) {
			answer += map[i][0]*points[i];
		}
		System.out.println(answer);
		
	}
	private static int[] getRotate(int n, int d) {
		int[] arr = new int[4];
		arr[n] = d;
		
		for (int i = n-1; i >= 0; i--) {
			if(map[i+1][6]==map[i][2]) break;
			arr[i] = arr[i+1]*(-1);
		}
		for (int i = n+1; i < arr.length; i++) {
			if(map[i-1][2]==map[i][6]) break;
			arr[i] = arr[i-1]*(-1);
		}

		return arr;
	}
	private static void rotate() {
		for (int i = 0; i < rotate_arr.length; i++) {
			int d = rotate_arr[i];
			int[] new_map = new int[8];
			if(d==-1) {//반시계
				for (int j = 7; j > 0; j--) {
					new_map[j-1] = map[i][j];
				}
				new_map[7] = map[i][0];
				map[i] = new_map;
			}else if(d==1) {//시계
				for (int j = 0; j < 7; j++) {
					new_map[j+1] = map[i][j]; 
				}
				new_map[0] = map[i][7];
				map[i] = new_map;
			}
			
		}
	}
}
