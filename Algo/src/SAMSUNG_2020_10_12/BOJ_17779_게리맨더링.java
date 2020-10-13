package SAMSUNG_2020_10_12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779_게리맨더링 {
	static int[][] map;
	static int N,min,max,answer;
	static int[][] dirs = {{1,-1},{1,1}};
	static int top_r, top_c, bot_r, bot_c, left_r, left_c, right_r, right_c;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		for (int i = 0; i < N-2; i++) {
			for (int j = 1; j < N-1; j++) {
				drawLine(i,j);//경계 그리기
				//calcMin();//인구수 최솟값 계산
				//break;
			}
			//break;
		}
		System.out.println(answer);
		
	}
	private static void calcMin() {
		//최솟값 구하기
		int[] polulation = new int[5];
		boolean[][] visited = new boolean[N][N];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		//1번
		for (int i = 0; i < top_r; i++) {
			for (int j = 0; j <= top_c; j++) {
				polulation[0] += map[i][j];
				visited[i][j] = true;
			}
		}
		int c1 = 0;
		for (int i = top_r; i < left_r; i++) {
			c1++;
			for (int j = 0; j <= top_c-c1; j++) {
				polulation[0] += map[i][j];
				visited[i][j] = true;
				
			}
		}
//		System.out.println(polulation[0]);
//		for (int i = 0; i < visited.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		//2번
		for (int i = 0; i <= top_r; i++) {
			for (int j = top_c+1; j < N; j++) {
				polulation[1] += map[i][j];
				visited[i][j] = true;
			}
		}
		int c2 = 0;
		for (int i = top_r+1; i <= right_r; i++) {
			c2++;
			for (int j = top_c+1+c2; j < N; j++) {
				polulation[1] += map[i][j];
				visited[i][j] = true;
			}
		}
//		System.out.println(polulation[1]);
//		for (int i = 0; i < visited.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		//3반
		int c3 = 0;
		for (int i = bot_r; i < N; i++) {
			for (int j = 0; j < bot_c; j++) {
				polulation[2] += map[i][j];
				visited[i][j] = true;
			}
		}
		
		for (int i = bot_r-1; i >= left_r; i--) {
			c3++;
			for (int j = 0; j < bot_c-c3; j++) {
				polulation[2] += map[i][j];
				visited[i][j] = true;
			}
		}
		
//		System.out.println(polulation[2]);
//		for (int i = 0; i < visited.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		//4반
		for (int i = bot_r+1; i < N; i++) {
			for (int j = bot_c; j < N; j++) {
				polulation[3] += map[i][j];
				visited[i][j] = true;
			}
		}
		int c4 = 0;
		for (int i = bot_r; i >= right_r+1; i--) {
			c4++;
			for (int j = bot_c+c4; j < N; j++) {
				polulation[3] += map[i][j];
				visited[i][j] = true;
			}
		}
//		System.out.println(polulation[2]);
//		for (int i = 0; i < visited.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		//5번
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					polulation[4] += map[i][j];
				}
			}
		}
		for (int i = 0; i < polulation.length; i++) {
			min = Math.min(min, polulation[i]);
			max = Math.max(max, polulation[i]);
		}
		//System.out.println(max-min);
		
		answer = Math.min(answer, max-min);
		
	}
	private static void drawLine(int r, int c) {
		//상하좌우 좌표 구하기
		//d1,d2 구하기
		for (int d1 = 1; d1 < map.length; d1++) {
			for (int d2 = 1; d2 < map.length; d2++) {
				top_r = r;
				top_c = c;
				left_r = r+d1*dirs[0][0];
				left_c = c+d1*dirs[0][1];
				right_r = r+d2*dirs[1][0];
				right_c = c+d2*dirs[1][1];
				bot_r = right_r+d1*dirs[0][0];
				bot_c = right_c+d1*dirs[0][1];
				
				if (!isInMap()) continue;
				//System.out.println(top_r+", "+top_c+", "+left_r+", "+left_c+", "+right_r+", "+right_c+", "+bot_r+", "+bot_c);
				calcMin();
				//break;
			}
			//break;
		}
	}
	
	private static boolean isInMap() {
		return bot_r>=0 &&bot_r<N && bot_c>=0 &&bot_c<N
				&& left_r>=0 &&left_r<N&& left_c>=0 &&left_c<N
				&& right_r>=0 &&right_r<N&& right_c>=0 &&right_c<N;
	}
}
