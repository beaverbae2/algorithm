package A2021_04_05;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 방문처리 시점 확인이 까다로움
 * @author beaverbae
 */

public class BOJ_9177_단어_섞기_dfs {
	static char[] word1, word2, target;
	static HashSet<String> set;
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
			
			set = new HashSet<>();
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
		
		String str = w1_idx+" "+w2_idx+" "+t_idx;// 현재 위치
		if (set.contains(str)) return;
		
		if (w1_idx < word1.length && word1[w1_idx] == target[t_idx]) {
			set.add(str);// 현재 위치 확인 완료
			dfs(w1_idx+1, w2_idx, t_idx+1);// 다음 위치 검사
		}
		
		if (w2_idx < word2.length &&word2[w2_idx] == target[t_idx]) {
			set.add(str);// 현재 위치 확인 완료
			dfs(w1_idx, w2_idx+1, t_idx+1);// 다음 위치 검사
		}
	}
}
