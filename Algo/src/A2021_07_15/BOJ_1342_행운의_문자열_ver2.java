package A2021_07_15;

import java.util.*;
import java.io.*;

/**
 * DFS
 * StringBuilder 사용 -> 문자열 결합 연산이 잦으므로
 * 
 * @author beaverbae
 *
 */

public class BOJ_1342_행운의_문자열_ver2 {
	static int N, ans;
	static int[] arr;
	static List<Integer> list;
	static StringBuilder sb;
	
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
		
		sb = new StringBuilder();
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
			sb.append(i);
			dfs(cnt+1);
			sb.deleteCharAt(sb.length()-1);
			arr[i]++;
		}
	}

	private static boolean isLuckyString() {
		for (int i = 1; i < sb.length() - 1; i++) {
			char cur = sb.charAt(i);
			char left = sb.charAt(i-1);
			char right = sb.charAt(i+1);
			
			if (left == cur || right == cur) return false;
		}
		
		return true;
	}
}
