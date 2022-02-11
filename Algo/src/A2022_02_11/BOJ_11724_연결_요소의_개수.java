package A2022_02_11;

import java.util.*;
import java.io.*;

/**
 * Union-find
 * 15MIN
 * @author beaverbae
 * - 마지막에 getParent() 로 조상정점을 찾아줘야 함
 */

public class BOJ_11724_연결_요소의_개수 {
	static int[] parent;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		HashSet<Integer> set = new HashSet<>();
		
		for (int v = 1; v < N+1; v++) {
			parent[v] = v;
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			unionParent(a, b);
		}
		
		for (int v = 1; v < N+1; v++) {
			set.add(getParent(v));
		}
		
		System.out.println(set.size());
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
