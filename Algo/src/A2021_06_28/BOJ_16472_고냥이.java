package A2021_06_28;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * 35MIN
 * 어려웠던 부분
 * - 시간 복잡도로 인해 brute force 불가 -> 최적화 필요 -> dp안될꺼 같음 -> two pointer 될꺼 같다고 판단 
 * - set -> map 사용 : 앞파벳의 존재 유무 뿐만 아니라 알파벳의 개수도 표현 필요
 * - 투 포인터 탐색 : 어떤 조건에서 left++, 어떤 조건에서 right++, 종료 조건은 뭔지
 * @author beaverbae
 *
 */

public class BOJ_16472_고냥이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		
		HashMap<Integer, Integer> map = new HashMap<>();
		int left = 0;
		int right = 0;
		int ans = 0;
		
		while (true) {
			if (map.size() > N) {
				int key = arr[left] - 'a';
				int value = map.get(key) - 1;
				
				if (value == 0) {
					map.remove(key);
				} else {
					map.put(key, value);
				}
				
				left++;
			} else {
				int key = arr[right] - 'a';
				
				if (!map.containsKey(key)) {
					map.put(key, 1);
				} else {
					int value = map.get(key) + 1;
					map.put(key, value);
				}
				
				right++;
				if (map.size() <= N) {// N 초과인 경우는 배체
					ans = Math.max(ans, right - left);
				}
			}

			if (right == arr.length) break;
		}
		
		System.out.println(ans);
	}
}
