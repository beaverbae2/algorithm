package A2021_09_18;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * 
 * 큰 실수 
 * - 블록들의 높이를 입력 받을때 N까지 입력 받아야하는데 M으로 함 
 * - 나머지 연산 -> 다 더하고 나머지 연산해야된다
 * 어려웠던 부분
 * - 재귀함수 종료 조건
 */

public class BOJ_18427_함께_블록_쌓기 {
	static int[][] dp;// dp[i][j] : i번째 학생이 높이 j인 블록을 선택했을 때 정답을 만들 수 있는 경우
	static int N, M, H;
	static int[][] blocks;// 학생들이 가지고 있는 블록의 높이모음
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
	
		dp = new int[N+1][H+1];
		blocks = new int[N+2][M+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], -1);
			Arrays.fill(blocks[i], -1);
		}
		for (int i = 1; i <= N; i++) {
			blocks[i][0] = 0;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				if (!st.hasMoreTokens()) break;
				blocks[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int i, int h0) {
		if (i > N || h0 > H) return 0;
		if (h0 == H) return dp[i][h0] = 1;
		if (dp[i][h0] != -1) return dp[i][h0];
		dp[i][h0] = 0;
		
		for (int j = 0; j < blocks[i+1].length; j++) {
			if (blocks[i+1][j] == -1) break;
			
			int h = blocks[i+1][j];
			dp[i][h0] = (dp[i][h0] + dfs(i+1, h0 + h)) % 10007;
		}
		
		return dp[i][h0];
	}
}
