package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * Two Pointers
 * 오래 걸린 이유
 * - 문제 잘못 읽음 :  S 이상
 * @author beaverbae
 *
 */

public class BOJ_1806_부분합 {
	static int[] arr;
	static int N, S;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
	
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int answer = Integer.MAX_VALUE;
		
		while (true) {
			if (sum >= S) {
				answer = Math.min(answer, end-start);
				sum -= arr[start++];
			} else {
				if (end == N) {
					break;
				}
				sum += arr[end++];
			}
		}
		
		if (answer == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(answer);
	}
}
