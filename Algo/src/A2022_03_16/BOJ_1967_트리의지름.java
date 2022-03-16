package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 28MIN
 * @author beaverbae
 * - a -> b로 이동하는 경로가 하나라는 트리의 특성 이용
 * - max_d가 0일때 문제 있었음
 * 
 */

public class BOJ_1967_트리의지름 {
	static int N, max_d, max_v;
	static List<Pair>[] tree;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new List[N+1];
		for (int v = 1; v <= N; v++) {
			tree[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			tree[a].add(new Pair(b, w));
			tree[b].add(new Pair(a, w));
		}
		
		visited = new boolean[N+1];
		dfs(1, 0);
		visited = new boolean[N+1];
		dfs(max_v, 0);
		System.out.println(max_d);
	}
	
	private static void dfs(int v, int d) {
		visited[v] = true;
		
		if (d >= max_d) {
			max_d = d;
			max_v = v;
		}
		
		for (Pair n : tree[v]) {
			if (visited[n.v]) continue;
			
			dfs(n.v, d + n.w);
		}
	}
	
	static class Pair {
		int v, w;

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Pair [v=" + v + ", w=" + w + "]";
		}
	}
}
