package A2022_03_17;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 22MIN
 * @author beaverbae
 * - 자식 -> 부모 로 이동하는 간선은 하나만 있음
 */

public class BOJ_2584_가장가까운공통조상 {
	static int[] parent;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			parent = new int[N+1];
			visited = new boolean[N+1];
			StringTokenizer st;
			
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				parent[c] = p;
			}
			
			int v1, v2;
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
		
			getParent(v1);
			sb.append(getParent(v2)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int getParent(int v) {
		if (visited[v] || v == 0) return v;
		
		visited[v] = true;
		return getParent(parent[v]);
	}
}
