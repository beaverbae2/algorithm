package A2021_06_10;

import java.util.*;
import java.io.*;

/**
 * DP
 * 48MIN
 * 
 * 어려웠던 부분
 * - 문제해석 : 왤캐 문제 해석을 잘못하지.. -> 방문할 수 있는 쉼터 개수를 해당 쉼터에서 다른 모든 쉼터로 갈 수 있는 개수라고 해석함
 * - 문제를 천천히 읽자
 * 
 * @author beaverbae
 *
 */

public class BOJ_14699_관악산_등산 {
	static int N, M;
	static int[] height;
	static List<Integer>[] graph;
	static int[] rest;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		height = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			
		}
		
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (height[a] < height[b]) {
				graph[a].add(b);
			} else {
				graph[b].add(a);
			}
			
		}
		
		rest = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			dfs(i);
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(rest[i]);
		}
	}
	
	private static int dfs(int v) {
		if (rest[v] != 0) {
			return rest[v];
		}
		
		rest[v] = 1;
		
		int max = 0;
		int temp = 0;
		for (int next : graph[v]) {
			temp = 0;
			temp += dfs(next);
			max = Math.max(max, temp);
		}
		
		rest[v] += max;
		
		return rest[v];
		
	}

}
