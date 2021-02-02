package A2021_02_02;

import java.util.*;
import java.io.*;

/**
 * UnionFind
 * @author beaverbae
 *
 */

public class BOJ_1976_여행가자_UnionFind {
	static int[] parent;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] graph = new int[N+1][N+1];
		final int INF = 987654321;
		
		
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int v = Integer.parseInt(st.nextToken());
				
				if (v == 1){
					graph[i][j] = v;
					unionParent(i, j);
				}else graph[i][j] = INF;
			}
		}
		
		String answer = "YES";
		String[] path = br.readLine().split(" ");
		for (int i = 1; i < path.length; i++) {
			int a = Integer.parseInt(path[i-1]);
			int b = Integer.parseInt(path[i]);
			
			if(!findParent(a, b)) {
				answer = "NO";
			}
		}
		System.out.println(answer);
	}
	
	public static int getParent(int v) {
		if (parent[v] == v) return v;
		return parent[v] = getParent(parent[v]);
	}
	
	public static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	public static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if (a == b) return true;
		return false;
	}
}
