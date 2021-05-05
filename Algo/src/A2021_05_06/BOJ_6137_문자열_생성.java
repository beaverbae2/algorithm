package A2021_05_06;

import java.util.*;
import java.io.*;

/**
 * Two Pointer
 * 54MIN
 * 어려웠던 부분
 * - left, right가 가리키는 문자열이 서로 같을 떄의 처리
 * @author beave
 * @see 백준 질문검색
 */

public class BOJ_6137_문자열_생성 {
	static int left, right, cnt;
	static int N;
	static char[] arr;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		arr = new char[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().charAt(0);
		}
		
		sb = new StringBuilder();
		cnt = 0;
		left = 0;
		right = N-1;
		
		while (true) {
			if (left == right) {
				add(arr[left]);
				break;
			}
		
			add(getMin(arr[left], arr[right]));
		}
		System.out.println(sb.toString());
	}
	
	private static void add(char c) {
		sb.append(c);
		cnt++;
		
		if (cnt % 80 == 0) {
			sb.append("\n");
		}
	}

	private static char getMin(char c1, char c2) {
		char result;
		if (c1 < c2) {
			left++;
			result = c1;
		} else if (c1 > c2){
			right--;
			result = c2;
		} else {
			if (arr[left+1] < arr[right-1]) {
				left++;
				result = c1;
			} else if (arr[left+1] > arr[right-1]){
				right--;
				result = c2;
			} else {
				int l = left;
				int r = right;
				int cnt = 0;
				
				result = arr[left];
				while (l + cnt <= r - cnt) {
					if (arr[l+cnt] < arr[r-cnt]) {
						left++;
						return result;
					} else if (arr[l+cnt] > arr[r-cnt]) {
						result = arr[right];
						right--;
						return result;
					}
					cnt++;
				}
				left++;
			}
		}
		return result;
	}
}
