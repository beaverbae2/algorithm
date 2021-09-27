package A2021_02_03;

import java.util.*;
import java.io.*;

/**
 * Union Find
 * 
 * @author beaverbae
 *
 */

public class BOJ_1043_거짓말 {
	static int[] parent;
	static List<Integer>[] list;
	static int N, M;
	static int root;// 진실 그룹 중 가장 작은 값(조상)
	static final int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		root = INF;
		List<Integer> init = new ArrayList<>();
		for (int i = 0; i < T; i++) {
			int v = Integer.parseInt(st.nextToken());
			root = Math.min(root, v);
			init.add(v);
		}

		for (int i = 0; i < init.size(); i++) {
			int v = init.get(i);
			if (v == root)
				continue;

			unionParent(root, v);
		}

		list = new List[M];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}

		int idx = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int p = INF;// 조상 노드

			for (int j = 0; j < num; j++) {
				int v = Integer.parseInt(st.nextToken());
				list[idx].add(v);
				p = Math.min(p, v);
			}

			for (int j = 0; j < list[idx].size(); j++) {
				int v = list[idx].get(j);

				if (p == v)
					continue;
				unionParent(p, v);
			}

			idx++;
		}

		int answer = 0;
		if (root == INF) {
			answer = M;
		} else {
			for (int i = 0; i < list.length; i++) {
				boolean flag = true;
				for (int j = 0; j < list[i].size(); j++) {
					int v = list[i].get(j);

					if (findParent(root, v)) {
						flag = false;
						break;
					}
				}
				if (flag)
					answer++;
			}
		}
		System.out.println(answer);
	}

	private static int getParent(int v) {
		if (parent[v] == v)
			return v;
		return parent[v] = getParent(parent[v]);
	}

	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b) {
			if (root == b) {
				root = a;
			}
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a == b)
			return true;
		return false;
	}

}
