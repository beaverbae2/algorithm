package A2021_06_25;

import java.util.*;
import java.io.*;

/**
 * Brute-force
 * 12MIN
 * 
 * @author beaverbae
 *
 */

public class BOJ_2961_도영이가_만든_맛있는_음식 {
	static int N;
	static int[] A, B;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			comb(i, 0, 0, 1, 0);
		}
		
		System.out.println(ans);
	}


	private static void comb(int n, int cnt, int start, int sum_a, int sum_b) {
		if (cnt == n) {
			ans = Math.min(ans, Math.abs(sum_a - sum_b));
			return;
		}
		
		for (int i = start; i < N; i++) {
			comb(n, cnt+1, i+1, sum_a * A[i], sum_b + B[i]);
		}
	}
}
