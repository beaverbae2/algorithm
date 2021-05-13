package A2021_05_13;

import java.util.*;
import java.io.*;

/**
 * 
 * @author beaverbae
 * @see J.H.KIM
 *
 */

public class BOJ_1749_점수_따먹기_solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
	 	int C = Integer.parseInt(st.nextToken());
	
		int[][] map = new int[R+1][C+1];
		int[][] dp = new int[R+1][C+1];
		
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j];
			}
		}
		
		
		int answer = Integer.MIN_VALUE;
		
		
		for (int x1 = 1; x1 <= R; x1++) {
			for (int y1 = 1; y1 <= C; y1++) {
				for (int x2 = x1; x2 <= R; x2++) {
					for (int y2 = y1; y2 <= C; y2++) {
						answer = Math.max(answer, dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1]);
					}
				}
			}
		}
		
		System.out.println(answer);
	}		
}
