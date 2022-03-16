package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 25MIN
 * @author beaverbae
 * - 트리의 점점이 정수가 아닌 경우
 * - Map으로 트리 표현
 */

public class BOJ_1991_트리순회 {
	static int N;
	static HashMap<Character, char[]> tree;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		tree = new HashMap<>();
		
		for (int v = 0; v < N; v++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			char[] sub = new char[2];
			sub[0] = left;
			sub[1] = right;
			tree.put(root, sub);
		}
		
		pre('A');
		sb.append("\n");
		in('A');
		sb.append("\n");
		post('A');
		System.out.println(sb);
	}

	private static void post(char v) {
		char[] sub = tree.get(v);
		if (sub[0] != '.') post(sub[0]);
		if (sub[1] != '.') post(sub[1]);
		sb.append(v);
	}

	private static void in(char v) {
		char[] sub = tree.get(v);
		if (sub[0] != '.') in(sub[0]);
		sb.append(v);
		if (sub[1] != '.') in(sub[1]);
	}

	private static void pre(char v) {
		char[] sub = tree.get(v);
		sb.append(v);
		if (sub[0] != '.') pre(sub[0]);
		if (sub[1] != '.') pre(sub[1]);
	}
}
