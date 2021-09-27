package A2021_06_11;

import java.util.*;
import java.io.*;

/**
 * Tree(BinaryTree)
 * 75MIN
 * 어려웠던 부분
 * 재귀함수 짜기 : 각 순회의 시작및 끝좌표 표기가 헷갈렸다, 우효한 인덱스 범위 짜는게 제일 어려웠다
 * 
 * @author beaverbae
 *
 */

public class BOJ_4256_트리 {
	static int N;
	static int[] pre, in, post;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			pre = new int[N];
			in = new int[N];
			post = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pre[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}
			
			
			calcPost(0, N-1, 0, N-1, 0, N-1);
		
			for (int v : post) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}

	private static void calcPost(int p_start, int p_end, int i_start, int i_end, int post_start, int post_end) {
		if (p_start > p_end) return;

		int root = pre[p_start];
		post[post_end] = root;
		
		int i_root = findInOrderRootIndex(root, i_start, i_end);

		int left_length = i_root - i_start;
		int right_length = i_end - i_root;
		
		calcPost(p_start+1, p_start+left_length, i_start, i_root-1, post_start, post_start+left_length-1);
		calcPost(p_start+left_length+1, p_end, i_root + 1, i_end, post_end-right_length, post_end-1);
		
	}

	private static int findInOrderRootIndex(int root, int start, int end) {
		for (int i = start; i <= end; i++) {
			if (in[i] == root) return i;
		}
		
		return -1;
	}

}
