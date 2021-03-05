package A2021_03_05;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * @author beaverbae
 *
 */

public class BOJ_1806_부분합 {
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
		
		int start = 0;
		int end = 0;
		int answer = Integer.MAX_VALUE;
		
		int sum = 0;
		while (true) {
			if (sum >= S) {
				answer = Math.min(answer, (end-start));
				sum -= arr[start++];
				if (start > end) end = start;
			} else {
				if (end >= N) break;
				sum += arr[end++];
			}
		}
		
		if (answer == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(answer);
	}
}
