package A2021_03_26;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 12MIN
 * @author beaverbae
 *
 */

public class BOJ_3980_선발_명단 {
	static int[][] board;
	static boolean[] visited;
	static final int N = 11;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			board = new int[N][N];
			visited = new boolean[N];
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			
			System.out.println(answer);
		}
	}

	private static void dfs(int idx, int score) {
		if (idx == N) {
			answer = Math.max(answer, score);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i] || board[idx][i] == 0) continue;
			
			visited[i] = true;
			dfs(idx+1, score+board[idx][i]);
			visited[i] = false;
		}
	}
}
