package A2021_09_16;

import java.util.*;
import java.io.*;

public class BOJ_15663_N과M9 {
	static int[] count, arr;
	static TreeSet<Integer> input;
	static StringBuilder sb;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new TreeSet<>();
		count = new int[10001];
		arr = new int[M];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			input.add(n);
			count[n]++;
		}
		
		dfs(0);
		
		System.out.println(sb);
	}

	private static void dfs(int cnt) {
		// 종료조건
		if (cnt == M) {
			for (int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i : input) { 
			if (count[i] == 0) continue;
			
			count[i]--;
			arr[cnt] = i;
			dfs(cnt+1);
			count[i]++;
		}
	}
}
