package A2021_04_23;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_2805_나무_자르기 {
	static int[] arr;
	static int N, M, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		System.out.println(parametric_search());
	}

	private static int parametric_search() {
		int result = -1;
		
		int left = 0;
		int right = max;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			long height = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > mid) {
					height += arr[i] - mid;
				}
			}
			
			if (height < M) {
				right = mid - 1;
			} else {
				result = mid;
				left = mid + 1;
			}
		}
		
		return result;
	}
}
