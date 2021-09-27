package A2021_05_07;

import java.util.*;
import java.io.*;

public class BOJ_1806_부분합_review {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = Integer.MAX_VALUE;
		int start = 0;
		int end = 0;
		int sum = 0;
		
		while (true) {
			if (sum >= S) {
				answer = Math.min(answer, end-start);
				sum -= arr[start++];
			} else {
				if (end == N) break;
				
				sum += arr[end++];
			}
		}
		
		answer = answer == Integer.MAX_VALUE ? 0 : answer;
		System.out.println(answer);
	}
}
