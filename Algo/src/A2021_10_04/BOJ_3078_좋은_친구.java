package A2021_10_04;

import java.util.*;
import java.io.*;

/**
 * Sliding window
 * @author beaverbae
 * 어려웠던 점
 * - 정답이 long 범위 일수도 있음 : 최댓값이 300000C2
 * - 동명이인 존재 가능
 * 
 */

public class BOJ_3078_좋은_친구 {
	static LinkedList<Integer> list;
	static HashMap<Integer, HashSet<Integer>> map;
	static HashMap<Integer, Integer> size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		long ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new LinkedList<>();
		map = new HashMap<>();
		size = new HashMap<>();
		
		String name = br.readLine();
		int idx = 0;
		list.add(idx);
		HashSet<Integer> set = new HashSet<>();
		set.add(idx);
		map.put(name.length(), set);
		size.put(idx, name.length());
		N--;
		
		while (N-- > 0) {
			idx++;
			name = br.readLine();
			int len;
			if (list.size() > K) {
				int first = list.removeFirst();
				len = size.remove(first);
				set = map.get(len);
				set.remove(first);
				
				if (set.size() == 0) {
					map.remove(len);
				} else {
					map.put(len, set);
				}
			}
			
			len = name.length();
			
			if (!map.containsKey(len)) {
				set = new HashSet<>();
			} else {
				ans += map.get(len).size();
				set = map.get(len);
			}
			
			list.add(idx);
			set.add(idx);
			map.put(len, set);
			size.put(idx, len);
		}
		
		System.out.println(ans);
	}
}
