package A2021_04_22;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @see https://zoosso.tistory.com/129
 * @author beaverbae
 *
 */

public class BOJ_6443_애너그램_solution {
	static StringBuilder sb;
	static char[] arr;
	static LinkedList<Character> list;
	static int[] alphabet;
	static boolean[] visited;
	static int N;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			arr = br.readLine().toCharArray();
			N = arr.length;
			visited = new boolean[N];
			alphabet = new int[26];
			list = new LinkedList<>();
			
			for (char ch : arr) {
				alphabet[ch-'a']++;
			}
			dfs(0);
			
		}
		
		System.out.println(sb.toString());
	}


	private static void dfs(int cnt) {
		if (cnt == N) {
			for (char ch : list) {
				sb.append(ch);
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] <= 0) continue;
			
			alphabet[i]--;
			list.add((char) ('a'+i));
			dfs(cnt+1);
			alphabet[i]++;
			list.removeLast();
		}
	}
}