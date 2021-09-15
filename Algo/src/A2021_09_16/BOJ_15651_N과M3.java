package A2021_09_16;

import java.util.*;
import java.io.*;

public class BOJ_15651_N과M3 {
	static int[] arr;
	static StringBuilder sb;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		sb = new StringBuilder();
		
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
		
		for (int i = 1; i <= N; i++) { 
			arr[cnt] = i;
			dfs(cnt+1);
		}
	}
}
