package A2021_06_10;

import java.util.*;
import java.io.*;

/**
 * Binary Search
 * 13MIN
 * 
 * @author beaverbae
 *
 */

public class BOJ_13702_이상한_술집 {
	static int N, K;
	static long left, right;
	static int[] arr;
	static long answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		left = 1;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, arr[i]);
		}
		
		binary_search();
		System.out.println(answer);
	}

	private static void binary_search() {
		while (left <= right) {
			long mid = (left + right) / 2; 
			
			long cnt = 0;
			for (int i = 0; i < N; i++) {
				cnt += arr[i] / mid;
			}
			
			if (cnt >= K) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
}
