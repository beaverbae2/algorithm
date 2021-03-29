package A2021_03_30;

import java.util.*;
import java.io.*;

/**
 * DFS, 문자열
 * 틀렸던 이유 : K-5가 list의 크기보다 큰 경우 고려 못함
 * 오래 걸린 이유 : a,c,i,n,t 배제
 * 최적화 :  Collections개열 자료구조보다 배열이 훨씬 빠르다
 * @author beaverbae
 * 
 */

public class BOJ_1062_가르침_최적화 {
	static int N, K;
	static char[][] input;
	static TreeSet<Character> set;
	static char[] arr;
	static boolean[] selected;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new char[N][];
		set = new TreeSet<>();
		
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine().toCharArray();
			for (int j = 0; j < input[i].length; j++) {
				if(!isDefaultAlphabet(input[i][j])) {
					set.add(input[i][j]);
				}
			}
		}
		
		arr = new char[set.size()];
		int idx = 0;
		for (char ch : set) {
			arr[idx++] = ch;
		}
		K = K-5;
		
		if (K < 0) {
			System.out.println(0);
		} else {
			if (K > arr.length) K = arr.length;// 추가한 부분
			selected = new boolean[26];
			getAlphabets(0, 0);
			System.out.println(answer);
		}
	}
	
	private static void getAlphabets(int start, int cnt) {
		if (cnt == K) {
			answer = Math.max(answer, getWordCnt());
			return;
		}
		
		for (int i = start; i < arr.length; i++) {
			selected[arr[i]-'a'] = true;
			getAlphabets(i+1, cnt+1);
			selected[arr[i]-'a'] = false;
		}
	}

	private static int getWordCnt() {
		int result = 0;
		
		for (int i = 0; i < input.length; i++) {
			boolean flag = true;
			for (int j = 0; j < input[i].length; j++) {
				char ch = input[i][j];
				if (isDefaultAlphabet(ch) || selected[ch-'a']) {
					continue;
				} else {
					flag = false;
					break;
				}
			}
			if (flag) result++;
		}
		
		return result;
	}

	private static boolean isDefaultAlphabet(char ch) {
		if (ch == 'a' || ch == 'c' || ch == 'i' || ch == 'n' || ch == 't') return true;
		return false;
	}
}
