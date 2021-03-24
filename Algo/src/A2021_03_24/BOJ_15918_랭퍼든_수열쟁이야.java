package A2021_03_24;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 35MIN
 * @author beaverbae
 *
 */

public class BOJ_15918_랭퍼든_수열쟁이야 {
	static int answer;
	static int[] arr;
	static int N;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
	
		N = 2*n;
		arr = new int[N];
		int num = y-x-1;
		arr[x] = num;
		arr[y] = num;
		
		visited = new boolean[n+1];
		visited[num] = true;
		
		dfs(0, n);
		System.out.println(answer);
	}
	
	
	private static void dfs(int idx, int n) {
		if (idx == N) {
			answer++;
			return;
		}
		
		if (arr[idx] != 0) {
			dfs(idx+1, n);
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (visited[i]) continue;
			
			int next_idx = idx+i+1;
			
			if (!isIn(next_idx) || arr[next_idx] != 0) continue;
			
			visited[i] = true;
			arr[idx] = i;
			arr[next_idx] = i;
			dfs(idx+1, n);
			visited[i] = false;
			arr[idx] = 0;
			arr[next_idx] = 0;
		}
		
	}
	
	private static boolean isIn(int n) {
		return n>=0 && n<N;
	}
}
