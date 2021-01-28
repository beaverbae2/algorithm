package A2020_12_11;

import java.io.*;
import java.util.*;

public class BOJ_10836_여왕벌 {
	static int[][] map, grow;
	static int N, M;
	static int[][] dirs = {{0,-1},{-1,-1},{-1,0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		grow = new int[M][3];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 1);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				grow[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		growUp();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void growUp() {
		for (int i = 0; i < grow.length; i++) {
			growUpSide(i);
		}
		growUpRemain();
		
	}

	private static void growUpSide(int k) {
		//거꾸로 진행한다
		int index = 2;
		boolean flag = true;
		
		//윗 열
		for (int i = N-1; i >= 0; i--) {
			while(grow[k][index] == 0) index--;
			if(index==0) {
				flag = false;
				break;
			}
			grow[k][index]--;
			map[0][i] += index;
		}
		
		//왼쪽 행
		if(flag) {
			for (int i = 1; i < N; i++) {
				while(grow[k][index] == 0) index--;
				if(index==0) {
					flag = false;
					break;
				}
				grow[k][index]--;
				map[i][0] +=index;
			}
		}
		
	}
	
	private static void growUpRemain() {
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				map[i][j] = Math.max(Math.max(map[i-1][j], map[i-1][j-1]), map[i][j-1]);
			}
		}
	}

	
}
