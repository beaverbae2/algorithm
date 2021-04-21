package A2021_04_21;

import java.util.*;
import java.io.*;

/**
 * Binary Search(lower & upper bound)
 * 32MIN
 * 오래걸린 이유
 * - 선분의 두 좌표가 모두 점의 최소 보다 작거너 점의 최대 보다 큰 경우 생각 못함
 * @author beaverbae
 *
 */

public class BOJ_11663_선분_위의_점 {
	static int[] arr;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int min = arr[0];
		int max = arr[N-1];
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if ((a < min && b < min) || (a > max && b > max)) {
				sb.append(0);
			} else {
				sb.append(upper_bound(b)-lower_bound(a)+1);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int lower_bound(int target) {
		int result = N-1;
		
		int left = 0;
		int right = N-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
				result = mid;
			}
		}
		return result;
	}

	private static int upper_bound(int target) {
		int result = 0;
		
		int left = 0;
		int right = N-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
				result = mid;
			}
		}
		
		return result;
	}
	
	
}
