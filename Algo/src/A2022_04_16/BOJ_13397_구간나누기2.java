package A2022_04_16;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * @author beaverbae
 * @see https://rolypolytoy.tistory.com/51
 * - 이분 탐색 기준 잡기가 어렵네..
 */

public class BOJ_13397_구간나누기2 {
	static int N, M, l, r, ans = 10000;
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
			r = Math.max(r, arr[i]);
		}
		
		binary_search();
		System.out.println(ans);
	}

	private static void binary_search() {
		while (l <= r) {
			int m = (l + r) / 2;
			
			if (isOk(m)) {
				ans = Math.min(ans, m);
				r = m - 1;
			} else l = m + 1;
		}
	}
	
	private static boolean isOk(int m) {
		int cnt = 1;
		
		int min = arr[0];
		int max = arr[0];
		
		for (int i = 1; i < N; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			
			if (max - min > m) {
				cnt++;
				min = max = arr[i];
			}
		}
		
		return cnt <= M;
	}
}
