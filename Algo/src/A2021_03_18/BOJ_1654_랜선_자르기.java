package A2021_03_18;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * 17MIN
 * @author beaverbae
 *
 */

public class BOJ_1654_랜선_자르기 {
	static long[] arr;
	static int N, K;
	static long left, right;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new long[K];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Long.parseLong(br.readLine());
			right = Math.max(right, arr[i]);
		}
		
		System.out.println(parametric_search());
		
	}

	private static long parametric_search() {
		long ans = 0;
		left = 1;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			
			// 랜선 개수 확인
			for (int i = 0; i < arr.length; i++) {
				cnt += arr[i]/mid;
			}
			
			if (cnt >= N) {// 덜 잘라도 됌
				ans = mid;
				left = mid+1;
			} else {// 더 잘라야함
				right = mid-1;
			}
		}
		
		return ans;
	}
}
