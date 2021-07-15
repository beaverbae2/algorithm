package A2021_07_13;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * 어려웠던 부분
 * - 완전히 꼬였었다... -> 꼬였던 부분 주석 참조
 * @author beaverbae
 *
 */

public class BOJ_2015_수들의_합_4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		int[] arr;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			if (i - 1 >= 0) arr[i] += arr[i-1];
			
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}
		
		long ans = 0;
		
		for (int i = 0; i < N; i++) {
			int k = K;
			if (i - 1 >= 0) {
				k += arr[i-1];
			}
			
			if (map.containsKey(k)) {
				ans += (long) map.get(k);
			}
			
			map.put(arr[i], map.get(arr[i]) - 1);// k가 아닌 현재 누적합을 빼줘야함!!
		}
		
		System.out.println(ans);
	}
}
