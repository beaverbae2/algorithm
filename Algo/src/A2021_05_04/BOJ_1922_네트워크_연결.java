package A2021_05_04;

import java.util.*;
import java.io.*;

/**
 * MST
 * 10MIN
 * @author beaverbae
 *
 */

public class BOJ_1922_네트워크_연결 {
	static int N, M;
	static List<Node> list;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
	
		list = new ArrayList<>();
		parent = new int[N+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			if (a == b) continue;
			list.add(new Node(a, b, w));
		}
		
		Collections.sort(list, (o1, o2) -> o1.w-o2.w);
		
		int answer = 0;
		int cnt = 0;
		for (Node cur : list) {
			int a = cur.a;
			int b = cur.b;
			int w = cur.w;
			
			if (findParent(a, b)) continue;
			unionParent(a, b);
			cnt++;
			answer += w;
			
			if (cnt == N-1) break;
		}
		
		System.out.println(answer);
	}
	
	private static int getParent(int v) {
		if (v == parent[v]) return v;
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
	
	static class Node {
		int a, b, w;

		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
	}
}
