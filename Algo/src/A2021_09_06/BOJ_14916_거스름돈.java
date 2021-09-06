package A2021_09_06;

import java.util.*;
import java.io.*;

/**
 * DP
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_14916_거스름돈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		final int INF = 100000;
		Arrays.fill(arr, INF);
		arr[0] = 0;
		
		// 2원
		for (int i = 2; i <= N; i++) {
			arr[i] = Math.min(arr[i], arr[i-2] + 1);
		}
		
		// 5원
		for (int i = 5; i <= N; i++) {
			arr[i] = Math.min(arr[i], arr[i-5] + 1);
		}
		
		if (arr[N] == INF) System.out.println(-1);
		else System.out.println(arr[N]);
	}
}
