package A2021_07_12;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 35MIN
 * 백준 질문 검색 참고 -> M == N 일 때 
 * @author beaverbae
 *
 */

public class BOJ_13422_도둑 {
	static int N, M, K, ans;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			
			arr = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 2; i <= N; i++) {
				arr[i] += arr[i-1]; 
			}
			
			if (N == M) {
				if (arr[N] - arr[0] < K) {
					sb.append(1);
				} else {
					sb.append(0);
				}
				sb.append("\n");
			} else {
				for (int start = 0; start < N; start++) {
					int end = start + M;
					int sum = 0;
					
					if (end > N) {
						end = end % N;
						sum = (arr[N] - arr[start]) + (arr[end] - arr[0]);
					} else {
						sum = arr[end] - arr[start];
					}
					
					if (sum < K) ans++;
				}
				sb.append(ans).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
