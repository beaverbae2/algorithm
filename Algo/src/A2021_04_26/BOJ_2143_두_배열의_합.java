package A2021_04_26;

import java.util.*;
import java.io.*;

/**
 * Prefix sum, Binary search
 * 오래 걸린 이유
 * - 첨에 투 포인터로 접근
 * - A, B 배열의 모든 합을 구하는게 O(N^2) 이라 충분한데 잘못 접근함
 * - lower_bound랑 upper_bound 구현에 애먹음 -> 예외 케이스
 * - lower랑 upper bound 재현이형 코드로 가는게 나을듯
 * - answer가 int 초과할 수 있음...
 * @author beaverbae
 * @see JaeHyun`s code
 */

public class BOJ_2143_두_배열의_합 {
	static int[] A, B;
	static int T, N, M;
	static long answer;
	static int[] sum_A, sum_B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B.length; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		sum_A = new int[(N * (N+1)) / 2];
		sum_B = new int[(M * (M+1)) / 2];
		
		int idx = 0;
		for (int i = 0; i < A.length; i++) {
			int sum = 0;
			for (int j = i; j < A.length; j++) {
				sum += A[j];
				sum_A[idx++] = sum; 
			}
		}
		
		idx = 0;
		for (int i = 0; i < B.length; i++) {
			int sum = 0;
			for (int j = i; j < B.length; j++) {
				sum += B[j];
				sum_B[idx++] = sum; 
			}
		}
		
		Arrays.sort(sum_A);
		Arrays.sort(sum_B);
	
		
		for (int i = 0; i < sum_A.length; i++) {
			int target = T - sum_A[i];
			int start = lower_bound(target);
			int end = upper_bound(target);
			
			if (start == -1 && end == -1) continue;
			answer += (end - start) +1;
		}
		
		System.out.println(answer);
	}

	private static int upper_bound(int target) {
		int start = 0;
		int end = sum_B.length - 1;
		int idx = -1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (sum_B[mid] > target) {
				end = mid-1;
			} else {
				start = mid + 1;
				if (sum_B[mid] == target) idx = mid;
			}
		}
		
		return idx;
	}

	private static int lower_bound(int target) {
		int start = 0;
		int end = sum_B.length - 1;
		int idx = -1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (sum_B[mid] < target) {
				start = mid + 1;
			} else {
				end = mid-1;
				if (sum_B[mid] == target) idx = mid;
			}
		}
		
		return idx;
	}

}
