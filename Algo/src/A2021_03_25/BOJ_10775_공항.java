package A2021_03_25;

import java.util.*;
import java.io.*;

/**
 * Disjoint Set
 * 23MIN
 * @author beaverbae
 *
 */

public class BOJ_10775_공항 {
	static int[] parent;
	static int G, P;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		parent = new int[G+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		P = Integer.parseInt(br.readLine());
		int answer = 0;
		boolean isStop = false;
		for (int i = 0; i < P; i++) {
			int v = Integer.parseInt(br.readLine());
			if (isStop) continue;
			
			int p = getParent(v);
			if (p == 0) {
				isStop = true;
				answer = i;
			}
		}
		
		if (!isStop) answer = P;
		
		System.out.println(answer);
		
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) {
			parent[v] = v-1;
			return v;
		}
		return parent[v] = getParent(parent[v]);
	}
}
