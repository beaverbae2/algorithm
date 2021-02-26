package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * UnionFind
 * 15MIN
 * @author beaverbae
 *
 */

public class BOJ_1976_여행가자_review1 {
	static int[] parent;
	static int[] path;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		path = new int[M+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		for (int a = 1; a <= N; a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int b = 1; b <= N; b++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 1) unionParent(a, b);
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < path.length; i++) {
			path[i] = Integer.parseInt(st.nextToken());
		}
		
		if (check()) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	
	private static boolean check() {
		for (int i = 2; i < path.length; i++) {
			if (!findParent(path[i-1], path[i])) return false;
		}
		return true;
	}


	private static int getParent(int v) {
		if (parent[v] == v) return parent[v];
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		return false;
	}
}
