package A2021_03_24;

import java.util.*;
import java.io.*;

/**
 * DFS(Permutation)
 * @author beaverbae
 *
 */

public class BOJ_10819_차이를_최대로 {
	static int[] input, arr;
	static boolean[] visited;
	static int N;
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		arr = new int[N];
		visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int idx) {
		if (idx == N) {
			answer = Math.max(answer, calc());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			arr[idx] = input[i];
			dfs(idx+1);
			visited[i] = false;
		}
		
	}

	private static int calc() {
		int result = 0;
		
		for (int i = 0; i < arr.length-1; i++) {
			result += Math.abs((arr[i] - arr[i+1]));
		}
		return result;
	}
	
	
}
