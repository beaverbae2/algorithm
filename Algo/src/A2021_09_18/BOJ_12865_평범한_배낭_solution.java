package A2021_09_18;

import java.util.*;
import java.io.*;

/**
 * DP(0/1 Knapsack)
 * @author beaverbae
 * @see https://gsmesie692.tistory.com/113
 */

public class BOJ_12865_평범한_배낭_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		int[] W, V;
		int[][] P;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		W = new int[N+1];
		V = new int[N+1];
		P = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			int w = W[i];
			int v = V[i];
			for (int j = 1; j <= K; j++) {
				P[i][j] = P[i-1][j];
				if (j - w >= 0) P[i][j] = Math.max(P[i-1][j-w] + v, P[i-1][j]);
			}
		}
		
		System.out.println(P[N][K]);
	}
}
