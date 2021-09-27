package A2021_09_16;

import java.util.*;
import java.io.*;

public class BOJ_15654_N과M5 {
	static int[] input, arr;
	static boolean[] visited;
	static StringBuilder sb;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];
		visited = new boolean[10001];
		arr = new int[M];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
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
			if (visited[i]) continue;
			
			visited[i] = true;
			arr[cnt] = i;
			dfs(cnt+1);
			visited[i] = false;
		}
	}
}
