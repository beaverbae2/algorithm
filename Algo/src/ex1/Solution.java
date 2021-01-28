package ex1;

import java.util.*;
//2021 THE FIRST HARF SINSEGAE INTERNATIONAL CODING TEST EX1
public class Solution {
	public int solution(int[] arr) {
        int answer = -1;
        LinkedHashMap<Integer, Integer> lastIdxMap = new LinkedHashMap<>();//인덱스
        LinkedHashMap<Integer, Integer> fastDupMap = new LinkedHashMap<>();//중복
        
        
        for (int i = 0; i < arr.length; i++) {
			int customer = arr[i];
			
			if (lastIdxMap.get(customer) == null) {
				lastIdxMap.put(customer, i);
			}else {
				int prev_idx = lastIdxMap.get(customer);
				int sub_idx = i - prev_idx;
				lastIdxMap.put(customer, i);
				
				if (fastDupMap.get(customer) == null) {
					fastDupMap.put(customer, sub_idx);
				}else {
					int prev_sub_idx = fastDupMap.get(customer);
					if (sub_idx < prev_sub_idx) {
						fastDupMap.put(customer, sub_idx);
					}
				}
			}
		}
//        System.out.println(lastIdxMap);
//        System.out.println(fastDupMap);
//        System.out.println();
        if (!fastDupMap.isEmpty()) {
        	Iterator<Integer> iter = fastDupMap.keySet().iterator();
        	LinkedList<Integer> list = new LinkedList<>();
        
        	while(iter.hasNext()) {
        		int key = iter.next();
        		int value = fastDupMap.get(key);
        		list.add(value);
        	}
        	
        	Collections.sort(list);
        	answer = list.get(0);
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(new Solution().solution(new int[] {2, 1, 3, 1, 4, 2, 1, 3}));
		System.out.println(new Solution().solution(new int[] {1, 2, 3}));
	}
}
