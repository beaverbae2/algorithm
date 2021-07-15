package A2021_07_15;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 41MIN
 * 어려웠던 점
 * - 문자와 문자의 개수가 주어짐 : 중복 발생할 줄 알았으나 아니었음
 * - 재귀함수의 매개변수 사용 된 String에서 결합 연산 있는 경우 메모리 낭비 심함
 * 
 * @author beaverbae
 *
 */

public class BOJ_1342_행운의_문자열 {
	static int N, ans;
	static int[] arr;
	static List<Integer> list;
	static ArrayList<Integer> selected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		N = input.length;
		Arrays.sort(input);
		
		char ch = input[0];
		int cnt = 1;
		list = new ArrayList<>();
		
		for (int i = 1; i < N; i++) {
			if (ch == input[i]) {
				cnt++;
			} else {
				list.add(cnt);
				cnt = 1;
				ch = input[i];
			}
		}
		list.add(cnt);
		arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		
		selected = new ArrayList<>();
		dfs(0);
		System.out.println(ans);
	}
	
	private static void dfs(int cnt) {
		if (cnt == N) {
			if (isLuckyString()) {
				ans++;
			}
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= 0) continue;
			
			arr[i]--;
			selected.add(i);
			dfs(cnt+1);
			selected.remove(selected.size() - 1);
			arr[i]++;
		}
	}

	private static boolean isLuckyString() {
		for (int i = 1; i < selected.size() - 1; i++) {
			int cur = selected.get(i);
			int left = selected.get(i-1);
			int right = selected.get(i+1);
			
			if (left == cur || right == cur) return false;
		}
		
		return true;
	}
}
