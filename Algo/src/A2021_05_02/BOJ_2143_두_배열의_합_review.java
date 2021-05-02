package A2021_05_02;

import java.util.*;
import java.io.*;

public class BOJ_2143_두_배열의_합_review {
	static int T, N, M;
	static int[] A, B;
	static int[] sumA, sumB;
	static long answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		sumA = new int[N*(N+1)/2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = i; j < N; j++) {
				sum += A[j];
				sumA[idx++] = sum;
			}
		}
		
		sumB = new int[M*(M+1)/2];
		idx = 0;
		for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int j = i; j < M; j++) {
				sum += B[j];
				sumB[idx++] = sum;
			}
		}
		
		Arrays.sort(sumB);
		
		for (int i = 0; i < sumA.length; i++) {
			int target = T - sumA[i];
			
			int start = lower_bound(target);
			int end = upper_bound(target);
			
			if (start == -1 && end == -1) continue;
			
			answer += end-start+1;
		}
		
		System.out.println(answer);
	}

	private static int upper_bound(int target) {
		
		int left = 0;
		int right = sumB.length-1;
		int idx = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (sumB[mid] > target) {
				right = mid-1;
			} else {
				if (sumB[mid] == target) {
					idx = mid;
				}
				left = mid+1;
			}
		}
		
		return idx;
	}

	private static int lower_bound(int target) {
		int left = 0;
		int right = sumB.length-1;
		int idx = -1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (sumB[mid] < target) {
				left = mid+1;
			} else {
				if (sumB[mid] == target) {
					idx = mid;
				}
				right = mid-1;
			}
		}
		
		return idx;
	}
}
