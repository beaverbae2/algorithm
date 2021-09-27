package A2021_06_21;

import java.util.*;
import java.io.*;

public class BOJ_10816_숫자카드_review {
	static int[] arr, targets;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		targets = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		Arrays.sort(arr);
		for (int target : targets) {
			sb.append((upper_bound(target) - lower_bound(target))).append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int lower_bound(int target) {
		int left = 0;
		int right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
		
			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
	
	private static int upper_bound(int target) {
		int left = 0;
		int right = N;
		
		while (left < right) {
			int mid = (left + right) / 2;
		
			if (arr[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
}
