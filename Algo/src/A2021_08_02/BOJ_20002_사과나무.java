package A2021_08_02;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 점화식 짜기가 너무 어려워요
 * 74MIN
 * @author beaverbae
 *
 */

public class BOJ_20002_사과나무 {
	static int N, ans;
	static int[][] A;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = Integer.MIN_VALUE;
		
		A = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				A[i][j] += A[i-1][j] + A[i][j-1] - A[i-1][j-1];
			}
		}
		
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int min = Math.min(r, c);
				int max = Math.max(r, c);
				int i = 0;
				int j = 0;
				
				if (r == max) {
					j = r - c;
				} else {
					i = c - r;
				}
				
				for (int k = 0; k < min; k++) {
					int sum = A[r][c] - A[r][i] - A[j][c] + A[j][i];
					ans = Math.max(ans, sum);
					i++;
					j++;
				}
			}
		}
		
		System.out.println(ans);
	}
}
