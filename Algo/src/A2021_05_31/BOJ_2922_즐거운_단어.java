package A2021_05_31;

/**
 * Backtracking
 * 
 * 62MIN
 * 
 * 어려웠던 이유
 * - 최대 경우의 수 : 26(알파벳 개수)^10 인데 10! 으로 착각
 * - 밑줄(_) 처리 : 현재 밑줄에 단어를 먼저 넣고 했어함, 또한 자음도 모음도 아님 
 * - L이 반드시 하나 "이상" 포함 : L을 한 개만 포함 시킴
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_2922_즐거운_단어 {
	static List<Integer> indexList;
	static char[] input;
	static long ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray(); // 입력값은 즐거운 단어라고 가정
		indexList = new ArrayList<>();
		boolean isL = false;
		
		for (int i = 0; i < input.length; i++) {
			if (input[i] == '_') {
				indexList.add(i);
			} else if (input[i] == 'L') {
				isL = true;
			}
		}
		
		if (indexList.isEmpty()) {
			ans = 1;
		} else {
			dfs(0, isL, 1);
		}
		
		
		System.out.println(ans);
	}

	private static void dfs(int list_idx, boolean isL, long cnt) {
		if (list_idx == indexList.size()) {
			if (isL) {
				ans += cnt;
			}
			return;
		}
		
		int idx = indexList.get(list_idx);
		
		// L 추가
		input[idx] = 'L';
		if (canAddConsonant(idx)) {
			dfs(list_idx + 1, true, cnt);
		}
		input[idx] = '_';

		// 모음 - 임의로 A 추가
		input[idx] = 'A';
		if (canAddVowel(idx)) {
			dfs(list_idx + 1, isL, cnt*5);
		}
		input[idx] = '_';
		
		// 자음 - 임의로 Z 추가
		input[idx] = 'Z';
		if (canAddConsonant(idx)) {
			dfs(list_idx + 1, isL, cnt*20);
		}
		input[idx] = '_';
		
	}

	private static boolean canAddVowel(int idx) {
		int cnt = 0;
		
		for (int i = idx-2; i <= idx; i++) {
			if (isIn(i) && isVowel(i)) {
				cnt++;
			}
		}
		if (cnt == 3) return false;
		
		cnt = 0;
		for (int i = idx-1; i <= idx+1; i++) {
			if (isIn(i) && isVowel(i)) {
				cnt++;
			}
		}
		if (cnt == 3) return false;
		
		cnt = 0;
		for (int i = idx; i <= idx+2; i++) {
			if (isIn(i) && isVowel(i)) {
				cnt++;
			}
		}
		if (cnt == 3) return false;
		
		return true;
	}

	private static boolean canAddConsonant(int idx) {
		int cnt = 0;
		for (int i = idx-2; i <= idx; i++) {
			if (isIn(i) && !isVowel(i) && input[i] != '_') {
				cnt++;
			}
		}
		if (cnt == 3) return false;
		
		cnt = 0;
		for (int i = idx-1; i <= idx+1; i++) {
			if (isIn(i) && !isVowel(i) && input[i] != '_') {
				cnt++;
			}
		}
		if (cnt == 3) return false;
		
		cnt = 0;
		for (int i = idx; i <= idx+2; i++) {
			if (isIn(i) && !isVowel(i) && input[i] != '_') {
				cnt++;
			}
		}
		if (cnt == 3) return false;
		
		return true;
	}
	
	
	private static boolean isVowel(int idx) {
		return input[idx] == 'A' || input[idx] == 'E' || input[idx] == 'I' || input[idx] == 'O' || input[idx] == 'U';
	}
	
	private static boolean isIn(int i) {
		return i >= 0 && i < input.length; 
	}
}
