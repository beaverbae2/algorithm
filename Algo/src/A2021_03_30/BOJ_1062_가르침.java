package A2021_03_30;

import java.util.*;
import java.io.*;

/**
 * DFS, 문자열
 * 틀렸던 이유 : K-5가 list의 크기보다 작은 경우 고려 못함
 * 오래 걸린 이유 : a,c,i,n,t 배제
 * @author beave
 * 
 */

public class BOJ_1062_가르침 {
	static int N, K;
	static char[][] input;
	static HashSet<Character> selected;
	static TreeSet<Character> set;
	static ArrayList<Character> list;
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
		
		list = new ArrayList<>();
		for (char ch : set) {
			list.add(ch);
		}
		K = K-5;
		
		if (K < 0) {
			System.out.println(0);
		} else {
			if (K > list.size()) K = list.size();// 추가한 부분
			selected = new HashSet<>();
			getAlphabets(0, 0);
			System.out.println(answer);
		}
	}
	
	private static void getAlphabets(int start, int cnt) {
		if (cnt == K) {
			answer = Math.max(answer, getWordCnt());
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			char ch = list.get(i);
			selected.add(ch);
			getAlphabets(i+1, cnt+1);
			selected.remove(ch);
		}
		
	}

	private static int getWordCnt() {
		int result = 0;
		
		for (int i = 0; i < input.length; i++) {
			boolean flag = true;
			for (int j = 0; j < input[i].length; j++) {
				char ch = input[i][j];
				if (isDefaultAlphabet(ch) || selected.contains(ch)) {
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
