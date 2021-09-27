package A2021_07_08;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * 21MIN
 * 어려웠던 부분
 * - 타입 문제 : long
 * 
 * @author beaverbae
 *
 */

public class BOJ_3079_입국심사 {
	static int N, M;
	static long[] arr;
	static long left, right;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N];
		left = 1000000000;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
			left = Math.min(left, arr[i]);
			right = Math.max(right, M * arr[i]);
		}
		
		System.out.println(parametric_search());
	}

	private static long parametric_search() {
		long result = 0;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			
			for (int i = 0; i < arr.length; i++) {
				cnt += mid / arr[i];
			}
			
			if (cnt >= M) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return result;
	}
}
