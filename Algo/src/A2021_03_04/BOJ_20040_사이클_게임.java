package A2021_03_04;

import java.util.*;
import java.io.*;

/**
 * UnionFind
 * 14 MIN
 * @author beaverbae
 *
 */

public class BOJ_20040_사이클_게임 {
	static int[] parent;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer = Integer.MAX_VALUE;

		parent = new int[N];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (answer != Integer.MAX_VALUE)
				continue;

			if (findParent(a, b)) {
				answer = i;
			}

			unionParent(a, b);
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}

	private static int getParent(int v) {
		if (parent[v] == v)
			return v;
		return parent[v] = getParent(parent[v]);
	}

	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a == b)
			return true;
		return false;
	}

}
