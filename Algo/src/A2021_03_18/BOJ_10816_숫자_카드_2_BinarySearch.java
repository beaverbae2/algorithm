package A2021_03_18;

import java.util.*;
import java.io.*;

/**
 * Binary Search
 * @author beaverbae
 *
 */

public class BOJ_10816_숫자_카드_2_BinarySearch {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		HashMap<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		Arrays.sort(arr);
		
		System.out.println(Arrays.toString(arr));
		
		int M = Integer.parseInt(br.readLine());
		int[] target = new int[M];
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
			
			int start = lower_bound(arr, N, target[i]);
			int end = upper_bound(arr, N, target[i]);
			
			if (start == -1 && end == -1) {
				sb.append(0);
			} else {
				sb.append((end-start+1));
			}
			
			sb.append(" ");
		}
		
		System.out.println(sb.toString());
	}

	private static int upper_bound(int[] arr, int N, int t) {
		int idx = -1;
		int left = 0;
		int right = N-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] <= t) {
				if (arr[mid] == t) idx = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return idx;
	}

	private static int lower_bound(int[] arr, int N, int t) {
		int idx = -1;
		int left = 0;
		int right = N-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] >= t) {
				if (arr[mid] == t) idx = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return idx;
	}
}
