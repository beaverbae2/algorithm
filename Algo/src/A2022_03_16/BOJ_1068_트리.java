package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * @author beaverbae
 * - 트리를 직접 끊어줘야함
반례 
4
-1 0 1 2
2
 */

public class BOJ_1068_트리 {
	static int N, ans;
	static int[] parent;
	static List<Integer>[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		tree = new List[N+1];
		for (int v = 0; v <= N; v++) {
			tree[v] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int v = 1; v <= N; v++) {
			parent[v] = Integer.parseInt(st.nextToken()) + 1;
			tree[parent[v]].add(v);
		}
		
		int rv = Integer.parseInt(br.readLine()) + 1; 
		tree[parent[rv]].remove((Integer) rv);
		dfs(0);
		System.out.println(ans);
	}
	
	private static void dfs(int v) {
		if (tree[v].size() == 0) {
			if (v != 0) ans++;
			return;
		}
		
		for (int nv : tree[v]) {
			dfs(nv);
		}
	}
}
