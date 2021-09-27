package A2021_02_24;

import java.util.*;
import java.io.*;

/**
 * Binary Tree
 * @author beaverbae
 * @see https://lemonlemon.tistory.com/93?category=838045
 */

public class BOJ_2263_트리의_순회_review {
	static int N;
	static int[] inOrder;
	static int[] postOrder;
	static int[] idx;
	static StringBuilder sb;
	static int MAX;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(br.readLine());
		inOrder = new int[N];
		postOrder = new int[N];
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
			MAX = Math.max(inOrder[i], MAX);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}
		
		idx = new int[MAX+1];
		for (int i = 0; i < N; i++) {
			idx[inOrder[i]] = i;
		}
		
		solve(0, N-1, 0, N-1);
		System.out.println(sb.toString());
	}
	
	
	public static void solve(int inStart, int inEnd, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd) return;
		
		int key = postOrder[postEnd];
		sb.append(key).append(" ");
		
		int index = idx[key];
		
		solve(inStart, index-1, postStart, postStart + (index-1-inStart));//left
		solve(index+1, inEnd, postEnd - (inEnd-index), postEnd-1);//right
	}
}
