package N_and_M;

import java.io.*;
import java.util.*;

// 중복 순열
public class N과M_3 {
	static int[] input;
	static int[] number;
	static int N, M;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		input = new int[N];
		number = new int[M];
		sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		
		dfs(0);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			number[cnt] = input[i];
			dfs(cnt+1);
		}
	}
}
