package A2021_09_21;

import java.util.*;
import java.io.*;

/**
 * Subset, Binary search
 * 
 * 핵심
 * - 최대 2^40개의 부분집합이 만들어지므로 시간 초과 발생 -> 반(2^20)으로 나눠서 해결
 * 
 * 놓친 부분
 * - 배열을 반으로 나눌떄 각 배열의 크기 초기화 -> N이 홀수일때, 짝수일떄 구분 필요
 * - 결과 값의 범위 long가능(최대 : 2^40 이므로)
 * 
 * 공부할 부분
 * - lower bound, upper bound
 * 
 * 
 * @author beaverbae
 * @see https://jaimemin.tistory.com/1107
 *
 */

public class BOJ_1208_부분수열의_합_2_solution {
	static int idx;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, S;
		long ans = 0;
		int[] input;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] arr1 = new int[1 << N/2];
		int[] arr2;
		if (N % 2 == 0) {
			arr2 = new int[1 << N/2]; // 실수한 부분
		} else {
			arr2 = new int[1 << 1+N/2]; // 실수한 부분
		}
		
		
		idx = 0;
		subset(input, arr1, 0, N/2, 0);
		idx = 0;
		subset(input, arr2, N/2, N, 0);
		
		Arrays.sort(arr2);
		
		for (int n1 : arr1) {
			ans += upper_bound(arr2, S - n1) - lower_bound(arr2, S - n1);
		}
		
		if (S == 0) ans--;
		
		System.out.println(ans);
	}
	
	private static long lower_bound(int[] arr, int S) {
		int left = 0;
		int right = arr.length;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] >= S) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
	
	private static long upper_bound(int[] arr, int S) {
		int left = 0;
		int right = arr.length;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] > S) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}

	private static void subset(int[] input, int[] arr, int i, int N, int sum) {
		if (i == N) {
			arr[idx++] = sum;
			return;
		}
		
		subset(input, arr, i+1, N, sum + input[i]);
		subset(input, arr, i+1, N, sum);
	}
}
