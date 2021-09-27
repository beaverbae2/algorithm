package A2021_04_21;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * 64MIN
 * 오래 걸린 이유
 * - 문제 이해 잘못함 : 테케 분석을 아예 잘못해버림
 * - 이분 탐색 구현 : lower bound로 접근해야함, 아직 개념이 헷갈림....
 * @author beaverbae
 *
 */
public class BOJ_1477_휴게소_세우기 {
	static int N, M, L;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	
		arr = new int[N+2];
		arr[0] = 0;
		arr[N+1] = L;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int max = 0;
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i] - arr[i-1]);
		}
		
		
		System.out.println(binary_search(max));
	}

	private static int binary_search(int max) {
		int left = 0;
		int right = max;
		int result = max;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = getCnt(mid);
			if (cnt > M) {// 휴게소를 덜 지어야함
				left = mid + 1;
			} else {
				right = mid - 1;
				result = mid;
			}
		}
		
		return result;
	}

	// 지을 수 있는 휴게소의 개수
	private static int getCnt(int mid) {
		int result = 0;
		for (int i = 1; i < arr.length; i++) {
			result += (arr[i] - arr[i-1])/mid;
			if ((arr[i] - arr[i-1]) % mid == 0) {
				result--;
			}
		}
		return result;
	}	
}
