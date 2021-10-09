package A2021_10_04;

import java.util.*;
import java.io.*;

/**
 * Sliding window
 * 38MIN
 * @author beaverbae
 * 순환 구조를 만드는 것이 핵심
 */

public class BOJ_15961_회전_초밥 {
	static int[] arr;
	static LinkedList<Integer> list;
	static HashMap<Integer, Integer> map;
	static int N, d, k, c, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		map = new HashMap<>();
		list = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < k; i++) {
			add(arr[i]);
			calcMax();
		}
		
		for (int i = k; i < N + k - 1; i++) {
			int idx = i;
			if (idx >= N) idx -= N; 
		
			add(arr[idx]);
			calcMax();
		}
		
		System.out.println(ans);
	}
	
	private static void add(int sushi) {
		if (list.size() == k) {
			int first = list.removeFirst();
			int cnt = map.get(first);
			cnt--;
			
			if (cnt == 0) {
				map.remove(first);
			} else {
				map.put(first, cnt);
			}
		}
		
		list.add(sushi);
		if (!map.containsKey(sushi)) {
			map.put(sushi, 1);
		} else {
			map.put(sushi, map.get(sushi) + 1);
		}
	}
	
	private static void calcMax() {
		if (map.containsKey(c)) {
			ans = Math.max(ans, map.size());
		} else {
			ans = Math.max(ans, map.size() + 1);
		}
	}
}
