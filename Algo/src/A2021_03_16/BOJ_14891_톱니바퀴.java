package A2021_03_16;

import java.util.*;
import java.io.*;

/**
 * Simulation
 * 58MIN
 * 문제 똑바로 읽자...
 * @author beaverbae
 *
 */

public class BOJ_14891_톱니바퀴 {
	static int[][] map;
	static boolean[] visited;
	static final int N = 8;
	static final int K = 4;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[K][N];
		for (int i = 0; i < K; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			visited = new boolean[K];
			check(idx, dir);
			rotate(idx, dir);
		}
		
		int ans = 0;
		for (int i = 0; i < K; i++) {
			if (map[i][0] == 1) {
				ans += (int) Math.pow(2, i);
			}
		}
		
		System.out.println(ans);
	}
	
	private static void check(int idx, int dir) {
		visited[idx] = true;
		boolean left = false;
		boolean right = false;
		
		// 왼
		if (isInMap(idx-1) && !visited[idx-1]) {
			if (map[idx][6] != map[idx-1][2]) left = true;
		}
		
		// 오
		if (isInMap(idx+1) && !visited[idx+1]) {
			if (map[idx][2] != map[idx+1][6]) right = true;
		}
		
		if (left) {
			check(idx-1, dir * -1);
			rotate(idx-1, dir * -1);
		}
		
		if (right) {
			check(idx+1, dir * -1);
			rotate(idx+1, dir * -1);
		}
	}

	private static boolean isInMap(int k) {
		return k>=0 && k<K;
	}
	
	private static void rotate(int idx, int d) {
		int[] temp = new int[N];
		
		for (int i = 0; i < N; i++) {
			temp[(i+d+N)%N] = map[idx][i];
		}
		
		map[idx] = temp;
	}
}
