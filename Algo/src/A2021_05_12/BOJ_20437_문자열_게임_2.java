package A2021_05_12;

import java.util.*;
import java.io.*;

/**
 * String
 * 55MIN
 * 어려웠던 이유
 * - 문제 해석 오류 : 3번 조건을 만족하는 문자열의 시작과 끝 문자로 4번을 구하라고 하는줄
 * - 투 포인터로 잘 못 접근
 * - 문제 해석이 요즘 잘 안되네...
 * @author beaverbae
 *
 */

public class BOJ_20437_문자열_게임_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			List<Integer>[] list = new List[26];
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}
			
			String str = br.readLine();
			char[] arr = new char[str.length()];
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				arr[i] = ch;
				list[ch-'a'].add(i);
			}
			
			int K = Integer.parseInt(br.readLine());
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			for (int i = 0; i < list.length; i++) {
				for (int j = K-1; j < list[i].size(); j++) {
					int start = list[i].get(j-K+1);
					int end = list[i].get(j);
					
					int len = end - start + 1;
					
					min = Math.min(min, len);
					max = Math.max(max, len);
				}
			}
			
			if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				sb.append(-1);
			} else {
				sb.append(min).append(" ").append(max);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
