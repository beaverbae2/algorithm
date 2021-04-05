package A2021_04_05;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * @see J.H.KIM code
 */

public class BOJ_9177_단어_섞기_solution {
	static char[] word1, word2, target;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("Data set ").append(tc).append(": ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			word1 = st.nextToken().toCharArray();
			word2 = st.nextToken().toCharArray();
			target = st.nextToken().toCharArray();
			
			if (word1.length + word2.length != target.length) {
				sb.append("no").append("\n");
				continue;
			}
			
			/////	target문자열에 word1과 word2의 문자가 없는 경우 정답X	///// 
			flag = true;
			HashSet<Character> set = new HashSet<>();
			for (int i = 0; i < word1.length; i++) {
				set.add(word1[i]);
			}
			
			for (int i = 0; i < word2.length; i++) {
				set.add(word2[i]);
			}
			
			for (int i = 0; i < target.length; i++) {
				if (!set.contains(target[i])) {
					flag = false;
					break;
				}
			}

			if (!flag) {
				sb.append("no").append("\n");
				continue;
			}
			////////////////////////////////////////////////////////////
			
			flag = false;
			dfs(0, 0, 0);
			if (flag) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
		}
 		
		System.out.println(sb.toString());
	}

	private static void dfs(int w1_idx, int w2_idx, int t_idx) {
		if (flag) return;
		
		if (t_idx == target.length) {
			flag = true;
			return;
		}
		
		if (w1_idx < word1.length && word1[w1_idx] == target[t_idx]) {
			dfs(w1_idx+1, w2_idx, t_idx+1);
		}
		
		if (w2_idx < word2.length &&word2[w2_idx] == target[t_idx]) {
			dfs(w1_idx, w2_idx+1, t_idx+1);
		}
	}
}
