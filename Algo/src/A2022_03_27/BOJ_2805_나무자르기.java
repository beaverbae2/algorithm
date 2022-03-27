package A2022_03_27;

import java.util.*;
import java.io.*;

/**
 * Binary search
 * 13MIN
 * @author beaverbae
 *
 */

public class BOJ_2805_나무자르기 {
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
		
		System.out.println(binary_search());
	}

	private static int binary_search() {
		int s = 0, e = max, m = -1;
		int ans = -1;
		
		while (s <= e) {
			m = (s + e) / 2;
			long len = 0L;
			for (int i = 0; i < N; i++) {
				if (arr[i] - m > 0) len += arr[i] - m;
			}
			
			if (len >= M) {
				ans = m;
				s = m + 1;
			} else e = m - 1;
		}
		
		return ans;
	}
}
