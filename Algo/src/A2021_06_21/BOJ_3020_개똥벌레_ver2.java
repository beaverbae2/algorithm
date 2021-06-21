package A2021_06_21;

import java.util.*;
import java.io.*;

/**
 * Prefix sum + Binary search
 * 비고
 * - lower랑 upper bound 구현
 * - 왜 시간이 더 오래 걸리지?
 * @author beaverbae
 *
 */

public class BOJ_3020_개똥벌레_ver2 {
	static int N, H;
	static int[] arr;
	static final int INF = 200001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
	
		arr = new int[H+1];
		for (int i = 1; i <= N; i++) {
			int k = Integer.parseInt(br.readLine());
			
			if (i % 2 == 1) {// 석순
				arr[0] += 1;
				arr[k] += -1;
			} else {// 종유석
				arr[H] += -1;
				arr[H-k] += 1;
			}
		}
		
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i < arr.length; i++) {
			arr[i] = arr[i-1] + arr[i];
			
			if (i == arr.length - 1) continue;
			
			min = Math.min(min, arr[i]);
		}
		
		arr[H] = INF;
		Arrays.sort(arr);
		int l = lower_bound(min);
		int u = upper_bound(min);
		int cnt = u - l + 1;
	
		System.out.println(min+" "+cnt);
	}

	private static int upper_bound(int target) {
		int result = -1;
		int left = 0;
		int right = H-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int h = arr[mid];
			
			if (h > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
				result = mid;
			}
		}
		
		return result;
	}

	private static int lower_bound(int target) {
		int result = -1;
		int left = 0;
		int right = H-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int h = arr[mid];
			
			if (h < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
				result = mid;
			}
		}
		
		return result;
	}
}
