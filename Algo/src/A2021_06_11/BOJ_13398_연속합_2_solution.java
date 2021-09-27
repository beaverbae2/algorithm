package A2021_06_11;

import java.util.*;
import java.io.*;

/**
 * 예전 풀이 참고
 * @author beaverbae
 *
 */

public class BOJ_13398_연속합_2_solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N][2]; // 0 : 모두 뽑은 경우, 1 : 하나 배제
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
	
		int answer = arr[0];
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]); // 직전까지의 합 + 현재값, 현재값
			dp[i][1] = Math.max(dp[i-1][0] , dp[i-1][1] + arr[i]);// 직전까지 합(현재 값 배제), 직전까지의 합(이전에 하나 배제) + 현재 값
			answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.println(answer);
	}
}
