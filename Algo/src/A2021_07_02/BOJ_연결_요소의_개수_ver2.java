package A2021_07_02;

import java.util.*;
import java.io.*;

/**
 * UnionFind
 * @author beaverbae
 *
 */

public class BOJ_연결_요소의_개수_ver2 {
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); 
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			unionParent(a, b);
		}
		
		boolean[] isParent = new boolean[N+1];
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int p = getParent(i);
			if (isParent[p]) continue;
			
			isParent[p] = true;
			ans++;
		}
		
		System.out.println(ans);
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
}
