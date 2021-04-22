package A2021_04_22;

import java.util.*;
import java.io.*;

public class BOJ_6443_애너그램_fail {
	static LinkedList<String> list;
	static HashSet<String> temp_set;
	static char[] arr;
	static boolean[] visited;
	static int N;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			arr = br.readLine().toCharArray();
			N = arr.length;
			visited = new boolean[N];
			list = new LinkedList<>();
			temp_set = new HashSet<>();
			dfs(0, "");
			Collections.sort(list);
			int len = list.size();
			for (int j = 0; j < len; j++) {
				sb.append(list.poll()).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}


	private static void dfs(int cnt, String s) {
		if (cnt == N) {
			list.add(s);
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			String next_s = s + arr[i];
			if (visited[i] || temp_set.contains(next_s)) continue;
		
			visited[i] = true;
			temp_set.add(next_s);
			dfs(cnt+1, next_s);
			visited[i] = false;
		}
	}
}