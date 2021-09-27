package A2021_08_01;

import java.util.*;
import java.io.*;

/**
 * 58MIN
 * 다시 한 번 풀어보자
 * @author beaverbae
 *
 */

public class BOJ_1158_요세푸스_문제 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int N, K;
		int[] arr;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}
		
		sb.append("<").append(arr[K]);
		arr[K] = -1;
		
		int i = K;
		int cnt = 1;
		
		while (cnt < N) {
			while (arr[i] == -1) {
				i++;
				if (i > N) i = 1;
			}
			
			int k = 1;
			
			while (k < K) {
				i++;
				if (i > N) i = 1;
				if (arr[i] != -1) k++;
			}
			
			cnt++;
			arr[i] = -1;
			
			sb.append(", ").append(i);
		}
		sb.append(">");
		
		System.out.println(sb);
	}
}
