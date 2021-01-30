package N_and_M;

import java.io.*;
import java.util.*;

public class N과M_12 {
	static int[] input;
	static int[] number;
	static int N, M;
	static StringBuilder sb;
	static HashSet<String> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		input = new int[N];
		number = new int[M];
		set = new HashSet<>();
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		dfs(0);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int cnt) {
		if (cnt == M) {
			StringBuilder temp = new StringBuilder();
			
			for (int i = 0; i < M; i++) {
				temp.append(number[i]).append(" ");
			}
			temp.append("\n");
			
			String str = temp.toString();
			if (!set.contains(str)) {
				set.add(str);
				sb.append(str);
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (cnt != 0 && number[cnt-1] > input[i]) continue;
			
			number[cnt] = input[i];
			dfs(cnt+1);
		}
	}
}
