package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * Two Pointers
 * 5MIN
 * @author beaverbae
 *
 */

public class BOJ_2003_수들의_합_2 {
	static int[] arr;
	static int N, M;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		
		while (true) {
			if (sum >= M) {
				if (sum == M) {
					answer++;
				}
				sum -= arr[start++];
			} else {
				if (end == N) break;
				sum += arr[end++];
			}
		}
		
		System.out.println(answer);
	}
}
