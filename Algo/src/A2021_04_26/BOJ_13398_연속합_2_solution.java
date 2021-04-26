package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * DP
 * @author beaverbae
 * @see https://steady-coding.tistory.com/181
 *
 */

public class BOJ_13398_연속합_2_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N][2]; // 0 : 다 뽑음, 1 : 1개 안뽑음
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
		
		int answer = arr[0];// 최소 한개는 무조건 뽑아야 하므로
		
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]); // 다 봅은 경우
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i]);// 1개 안 뽑은 경우 
			answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.println(answer);
	}
}
