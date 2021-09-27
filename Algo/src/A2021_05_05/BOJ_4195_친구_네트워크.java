package A2021_05_05;

import java.util.*;
import java.io.*;

/**
 * UnionFind
 * 48MIN
 * 어려웠던 부분
 * - 친구네트워크를 어떻게 표현할 것인가? -> 배열 활용
 * - 두 정점의 조상이 같은 경우 처리
 * @author beaverbae
 *
 */

public class BOJ_4195_친구_네트워크 {
	static HashMap<String, Integer> map;
	static int F;
	static int[] parent;
	static int[] networkCnt;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			F = Integer.parseInt(br.readLine());
			parent = new int[2*F];
			networkCnt = new int[2*F];
			map = new HashMap<>();
			
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
				networkCnt[i] = 1;
			}
			
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				
				if (!map.containsKey(s1)) {
					map.put(s1, map.size());
				}
				
				if (!map.containsKey(s2)) {
					map.put(s2, map.size());
				}
				
				// logic
				int a = map.get(s1);
				int b = map.get(s2);
				
				unionParent(a, b);
			}
		}
		
		System.out.println(sb.toString());
	}
	
	
	private static int getParent(int v) {
		if (v == parent[v]) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) {
			parent[b] = a;
			networkCnt[a] += networkCnt[b];
			networkCnt[b] = 0;
		}
		else if (b < a){
			parent[a] = b;
			networkCnt[b] += networkCnt[a];
			networkCnt[a] = 0;
		}
		
		sb.append(networkCnt[Math.min(a, b)]).append("\n");
	}
}
