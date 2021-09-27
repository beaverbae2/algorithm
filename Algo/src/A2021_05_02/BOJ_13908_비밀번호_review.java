package A2021_05_02;

import java.util.*;
import java.io.*;

public class BOJ_13908_비밀번호_review {
	static int[] num;
	static int[] visited;
	static int N, M;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		num = new int[10];
		visited = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			visited[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int idx) {
		if (idx == N) {
			if (check()) {
				answer++;
			}
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			num[i]++;
			dfs(idx+1);
			num[i]--;
		}
	}

	private static boolean check() {
		for (int i = 0; i < visited.length; i++) {
			if (num[visited[i]] == 0) return false;
		}
		
		return true;
	}
}
