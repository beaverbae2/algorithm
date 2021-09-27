package A2021_03_18;

import java.util.*;
import java.io.*;

/**
 * Parametric Search
 * 23MIN
 * @author beaverbae
 *
 */

public class BOJ_2110_공유기_설치 {
	static int[] arr;
	static int N, C;
	static int left, right;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, arr[i]);
		}
		
		Arrays.sort(arr);
		
		System.out.println(parametric_search());
	}

	private static int parametric_search() {
		int ans = 0;
		left = 1;
		while (left <= right) {
			int mid = (left+right)/2;
			
			int start = 0;
			int cnt = 1;
			for (int i = 1; i < arr.length; i++) {
				if (mid <= arr[i]-arr[start]) {
					cnt++;
					start = i;
				}
			}
			
			if (cnt >= C) {
				ans = mid;
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		
		return ans;
	}
}
