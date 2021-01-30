package N_and_M;

import java.io.*;
import java.util.*;

// 순열
public class N과M_5 {
	static int[] input;
	static int[] number;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		input = new int[N];
		visited = new boolean[N];
		number = new int[M];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		permutation(0);
		System.out.println(sb.toString());
	}
	
	private static void permutation(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			number[cnt] = input[i];
			permutation(cnt+1);
			visited[i] = false;
		}
	}
}
