package A2022_03_24;

import java.util.*;
import java.io.*;

/**
 * Disjoint set
 * 29MIN
 * @author beaverbae
 * - union find를 활용하여 cycle 여부 확인
 * 
 */

public class BOJ_20040_사이클게임 {
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean isCycle = false;
		int ans = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		
		for (int v = 0; v < N; v++) {
			parent[v] = v;
		}
		
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			if (isCycle) continue;
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (findParent(a, b)) {
				isCycle = true;
				ans = m;
			} else {
				unionParent(a, b);
			}
			
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

	private static boolean findParent(int a, int b) {
		return getParent(a) == getParent(b);
	}
}
