package A2022_03_17;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 50MIN
 * @author beaverbae
 * - root가 1이 아닐수도 있음
 */

public class BOJ_2250_트리의높이와너비 {
	static int N, level, width, v;
	static boolean[] isChild;
	static int[] values;
	static int[][] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new int[N+1][2];
		values = new int[N+1];
		isChild = new boolean[N+1];
		v = 1;
		int root = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			tree[p][0] = Integer.parseInt(st.nextToken());
			tree[p][1] = Integer.parseInt(st.nextToken());
			if (tree[p][0] != -1) isChild[tree[p][0]] = true;
			if (tree[p][1] != -1) isChild[tree[p][1]] = true;
		}
		
		for (int i = 1; i <= N; i++) {
			if (!isChild[i]) {
				root = i;
				break;
			}
		}
		
		inOrder(root);
		bfs(root);
		System.out.println(level+" "+width);
	}
	
	private static void inOrder(int p) {
		if (tree[p][0] != -1) inOrder(tree[p][0]);
		values[p] = v++;
		if (tree[p][1] != -1) inOrder(tree[p][1]);
	}
	
	private static void bfs(int s) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {s, 1});
		int lev = 0;
		int min = N, max = 0;
		
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int v = arr[0];
			int l = arr[1];
			
			if (l != lev) {
				lev = l;
				min = max = values[v];
			} 
			min = Math.min(min, values[v]);
			max = Math.max(max, values[v]);
			
			if (width < max - min + 1) {
				width = max - min + 1;
				level = lev;
			}
			
			for (int nv : tree[v]) {
				if (nv != -1) q.offer(new int[] {nv, l+1});
			}
		}
	}
}
