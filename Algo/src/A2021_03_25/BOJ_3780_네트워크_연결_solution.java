package A2021_03_25;

import java.util.*;

/**
 * Disjoint Set
 * @author beaverbae
 * @see https://js1jj2sk3.tistory.com/21
 */

import java.io.*;

public class BOJ_3780_네트워크_연결_solution {
	static int N;
	static int[] len, parent;
	static final int MOD = 1000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			len = new int[N+1];
			parent = new int[N+1];
			for (int i = 1; i < parent.length; i++) {
				parent[i] = i;
			}
			
			while(true) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				if (command == 'O') {
					break;
				} else if (command == 'E') {
					int v = Integer.parseInt(st.nextToken());
					getParent(v);
					sb.append(len[v]).append("\n");
				} else if (command == 'I') {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					unionParent(a, b);
				}
			}
			
		}
		System.out.println(sb.toString());
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		else {
			int temp = getParent(parent[v]);
			len[v] += len[parent[v]];// 이전 단계에서의 조상 노드
			parent[v] = temp;
			
			return parent[v];
		}
	}
	
	private static void unionParent(int a, int b) {
		len[a] = Math.abs(a-b) % MOD;
		parent[a] = b;
	}
	
}
