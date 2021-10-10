package A2021_10_10;

import java.util.*;
import java.io.*;

/**
 * Union-Find
 * 29MIN
 * @author beaverbae
 * Union-Find 에서 노드의 조상 집합 노드를 찾는 메소드에서의 경로 압축 알고리즘 활용
 */

public class BOJ_10775_공항 {
	static int[] parent;
	static int G, P;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		int ans = 0;
		boolean flag = false;
		
		parent = new int[G+1];
		for (int i = 0; i <= G; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < P; i++) {
			int a = Integer.parseInt(br.readLine());
			if (flag) continue;
			
			int p = getParent(a);
			
			if (p == 0) {
				flag = true;
				continue;
			}
			
			parent[p] = p-1;
			ans++;
		}
		
		System.out.println(ans);
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		return parent[v] = getParent(parent[v]);
	}
}
