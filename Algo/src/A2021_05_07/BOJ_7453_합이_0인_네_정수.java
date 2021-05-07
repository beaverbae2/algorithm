package A2021_05_07;

import java.util.*;
import java.io.*;

public class BOJ_7453_합이_0인_네_정수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] AB = new int[N*N];
		int[] CD = new int[N*N];
	
		
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx] = C[i] + D[j];
				idx++;
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		long answer = 0;
		int left = 0;
		int right = N*N-1;
		
		while (left < N*N && right > -1) {
			int left_sum = AB[left];
			int right_sum = CD[right];
			long sum = left_sum + right_sum;
			
			if (sum < 0) {
				left++;
			} else if (sum > 0) {
				right--;
			} else {
				int next_left = upper_bound(AB, left_sum) + 1;
				long left_answer = next_left - left;
				left = next_left;
//				while (left < N*N && left_sum == AB[left]) {
//					left++;
//					left_answer++;
//				}
				
				int next_right = lower_bound(CD, right_sum) - 1;
				long right_answer = right - next_right;
				right = next_right;
				
//				while (right > -1 && right_sum == CD[right]) {
//					right--;
//					right_answer++;
//				}
				
				answer += left_answer * right_answer;
			}
		}
		
		System.out.println(answer);
	}
	
	private static int lower_bound(int[] arr, int target) {
		int left = 0;
		int right = arr.length-1;
		int idx = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
				if (arr[mid] == target) {
					idx = mid;
				}
			}
		}
		
		return idx;
	}
	
	private static int upper_bound(int[] arr, int target) {
		int left = 0;
		int right = arr.length-1;
		int idx = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
				if (arr[mid] == target) {
					idx = mid;
				}
			}
		}
		
		return idx;
	}
}
