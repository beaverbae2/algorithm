package A2021_07_06;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * 
 * @author beaverbae
 *
 */

public class BOJ_13397_구간나누기2_solution {
	static int N, M;
	static int[] arr;
	
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
		
		System.out.println(parametric_search());
	}

	private static int parametric_search() {
		int result = 0;
		int left = 0;
		int right = 10000;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			int cnt = 1;
			int min = 10000, max = 0;
			for (int i = 0; i < N; i++) {
				min = Math.min(min, arr[i]);
				max = Math.max(max, arr[i]);
			
				if (max - min > mid) {
					cnt++;
					max = arr[i];
					min = arr[i];
				}
			}
			
			if (cnt <= M) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return result;
	}
}
