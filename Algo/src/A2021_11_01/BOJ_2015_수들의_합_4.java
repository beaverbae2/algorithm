package A2021_11_01;

import java.util.*;
import java.io.*;

/**
 * Prefix sum, HashMap
 * 46MIN
 * @author beaverbae
 * 어려웠던 부분
 * - 알고리즘 추측 : 투 포인터(오류 찾음) -> 누적합
 * - 범위 : 정답 개수와, (i번째 인덱스까지의 합 - K) 가 long 범위 가능
 */

public class BOJ_2015_수들의_합_4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		long[] arr;
		HashMap<Long, Long> map;
		long ans;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ans = 0;
		arr = new long[N+1];
		map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		map.put(arr[0], 1L);
		
		for (int i = 1; i < N+1; i++) {
			arr[i] += arr[i-1];
			long key = arr[i] - K;
			if (map.containsKey(key)) ans += map.get(key);
			
			if (!map.containsKey(arr[i])) map.put(arr[i], 1L); 
			else {
				long cnt = map.get(arr[i]);
				map.put(arr[i], cnt+1);
			}
		}
		
		System.out.println(ans);
	}
}
