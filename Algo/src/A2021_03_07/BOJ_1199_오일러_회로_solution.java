package A2021_03_07;

import java.util.*;
import java.io.*;

/**
 * DFS
 * @author beaverbae
 * @see http://blog.naver.com/PostView.nhn?blogId=kerochuu&logNo=222138803003&parentCategoryNo=6&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView
 */

public class BOJ_1199_오일러_회로_solution {
	static int[][] graph;
	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row_sum = 0;
			for (int j = 1; j <= N; j++) {
				int v = Integer.parseInt(st.nextToken());
				graph[i][j] = v;
				row_sum += v;
			}
			
			if (row_sum % 2 == 1) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		sb = new StringBuilder();
		dfs(1);
		System.out.println(sb.toString());
	}

	private static void dfs(int v) {
		for (int next_v = 1; next_v <= N; next_v++) {
			while (graph[v][next_v] >0) {
				graph[v][next_v]--;
				graph[next_v][v]--;
				
				dfs(next_v);
			}
		}
		
		sb.append(v).append(" ");
	}
}
