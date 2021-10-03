package A2021_10_03;

import java.util.*;
import java.io.*;

/**
 * 48MIN
 * Tree
 * @author beaverbae
 * 이진 탐색 트리가 아니고 이진 트리 임에 주의
 * 배열로 구현하려면 (left : 2*i, right : 2*i+1 이용) 메모리 낭비가 심함
 */

public class BOJ_1991_트리_순회 {
	static int N;
	static HashMap<Character, Character> leftMap, rightMap;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		leftMap = new HashMap<>();
		rightMap = new HashMap<>();
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char p = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			if (left != '.') leftMap.put(p, left);
			if (right != '.') rightMap.put(p, right);
		}
		
		preOrder('A');
		sb.append("\n");
		inOrder('A');
		sb.append("\n");
		postOrder('A');
		System.out.println(sb);
	}
	
	private static void preOrder(char c) {
		sb.append(c);
		if (leftMap.containsKey(c)) preOrder(leftMap.get(c));
		if (rightMap.containsKey(c)) preOrder(rightMap.get(c));
	}
	
	private static void inOrder(char c) {
		if (leftMap.containsKey(c)) inOrder(leftMap.get(c));
		sb.append(c);
		if (rightMap.containsKey(c)) inOrder(rightMap.get(c));
	}
	
	private static void postOrder(char c) {
		if (leftMap.containsKey(c)) postOrder(leftMap.get(c));
		if (rightMap.containsKey(c)) postOrder(rightMap.get(c));
		sb.append(c);
	}
	
}
