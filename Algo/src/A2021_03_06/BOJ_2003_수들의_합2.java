package A2021_03_06;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * 9MIN
 * @author beaverbae
 *
 */

public class BOJ_2003_수들의_합2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int answer = 0;
		
		while (true) {
			if (sum >= M) {
				if (sum == M) answer++;
				sum -= arr[start++];
				if (start > end) end = start;
			} else {
				if (end == N) break;
				sum += arr[end++];
			}
		}
		
		System.out.println(answer);
	}
}
