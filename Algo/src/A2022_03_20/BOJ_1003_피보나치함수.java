package A2022_03_20;

import java.util.*;
import java.io.*;

/**
 * DP
 * 7MIN
 * @author beaverbae
 *
 */

public class BOJ_1003_피보나치함수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = 40;
		int[][] A = new int[N+1][2];
		A[0][0] = A[1][1] = 1;
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 2; j++) {
				A[i][j] = A[i-1][j] + A[i-2][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			int i = Integer.parseInt(br.readLine());
			sb.append(A[i][0]).append(" ").append(A[i][1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
