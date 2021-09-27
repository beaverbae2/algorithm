package A2021_04_21;

import java.util.*;
import java.io.*;

/**
 * DP
 * 74MIN
 * 오래걸린 이유 : 점화식이 짜기 너무 힘들었다.....
 * @author beaverbae
 *
 */

public class BOJ_10653_마라톤2 {
	static int N, K;
	static Pair[] pos;
	static int[][] dist;
	static int[][] dp;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		pos = new Pair[N];
		dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			pos[i] = new Pair(r, c);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				
				dist[i][j] = Math.abs(pos[i].r - pos[j].r) + Math.abs(pos[i].c - pos[j].c);
			}
		}
		
		dp = new int[N][K+1]; // dp[i][k] : 출발지(0) 에서 i까지 이동 시,  체크포인트를 k개 건너뛴 경우 이동거리의 최소 
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		dp[0][0] = 0;
		
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = dp[i-1][0] + dist[i][i-1]; // 건너뛴 체크포인트가 없는 경우
			for (int j = 1; j <= K; j++) {// 나머지 건너뛴 체크포인트 개수 만큼 확인
				int a = 1;// 현재 위치에서 몇 칸 전인지
				for (int k = j; k >= 0; k--) {
					int prev_i = i-a;// i-a : 이전 좌표(확인해야하는 좌표)
					if (prev_i >= 0) {// 범위 확인
						dp[i][j] = Math.min(dp[i][j], dp[prev_i][k] + dist[i][prev_i]); // prev_i에서 k번 건너뛴 이동거리 + prev_i와 i사이의 거리
						a++;
					}
				}
			}
			
		}
		
		
		System.out.println(dp[N-1][K]);
	}
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
}
