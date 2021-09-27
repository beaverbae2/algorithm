package A2021_04_15;

import java.util.*;
import java.io.*;

/**
 * DP
 * 오래걸린 이유 : 점화식 이해(dp배열에 매번 할당), 최댓값(50, 50 , 50)을 계산하면 모든 경우에 풀이가 가능한 줄...
 * @author beaverbae
 *
 */

public class BOJ_9184_신나는_함수_실행 {
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		dp = new int[101][101][101];
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			if (a == -1 && b == -1 && c == -1) break;
			sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
			
			a += 50;
			b += 50;
			c += 50;
			
			calc(a, b, c);
			sb.append(dp[a][b][c]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int calc(int a, int b, int c) {
		if (dp[a][b][c] != 0) return dp[a][b][c];
		
		if (a <= 50 || b <= 50 || c <= 50) return dp[a][b][c] = 1;
		
		if (a > 70 || b > 70 || c > 70) return dp[a][b][c] = calc(70, 70, 70);
		
		if (a < b && b < c) {
			return dp[a][b][c] = calc(a, b, c-1) + calc(a, b-1, c-1) - calc(a, b-1, c);
		}
		
		return dp[a][b][c] = calc(a-1, b, c) + calc(a-1, b-1, c) + calc(a-1, b, c-1) - calc(a-1, b-1, c-1);
	}
}
