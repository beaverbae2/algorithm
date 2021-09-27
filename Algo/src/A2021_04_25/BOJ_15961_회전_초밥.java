package A2021_04_25;

import java.util.*;
import java.io.*;

/**
 * Sliding Window
 * 30MIN
 * @author beaverbae
 *
 */

public class BOJ_15961_회전_초밥 {
	static HashMap<Integer, Integer> map;
	static int[] arr;
	static int N, D, K, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		arr = new int[N];
		map.put(C, 1); // 쿠폰으로 먹는 초밥 추가
		
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < K; i++) {
			int key = arr[i];
			
			if (map.containsKey(key)) {
				map.put(key, map.get(key)+1);
			} else {
				map.put(key, 1);
			}
		}
		
		int start = 0;
		int end = K;
		int answer = map.size();
		
		for (int i = 0; i < N; i++) {
			// 맨 앞 요소 제외
			int first = arr[start++];
			if (map.containsKey(first)) {
				int value = map.get(first);
				if (value == 1) {
					map.remove(first);
				} else {
					map.put(first, value-1);
				}
			}
			
			// 맨 뒤 요소 추가
			int last = arr[end++];
			if (map.containsKey(last)) {
				int value = map.get(last);
				map.put(last, value+1);
			} else {
				map.put(last, 1);
			}
			
			if (end == N) {
				end = 0;
			}
			if (start == N) {
				start = 0;
			}
			
			answer = Math.max(answer, map.size());
		}
		System.out.println(answer);
	}
}
