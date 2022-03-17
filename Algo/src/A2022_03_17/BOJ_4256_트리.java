package A2022_03_17;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 45MIN
 * @author beaverbae
 * - 이진트리에서 전위, 중위 순회 -> 후위 순회
 */

public class BOJ_4256_트리 {
	static int[] pre, in, post;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (TC-- > 0) {
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
			
			calc(0, N-1, 0, N-1, 0, N-1);
			
			for (int i = 0; i < N; i++) {
				sb.append(post[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void calc(int s1, int e1, int s2, int e2, int s3, int e3) {
		if (s1 > e1) return;
		int v = pre[s1];
		post[e3] = v;
		
		int m = -1, l, r;
		for (int i = s2; i <= e2; i++) {
			if (v == in[i]) m = i;
		}
		
		l = m - s2;
		r = e2 - m;
		
		calc(s1+1, s1+l, s2, m-1, s3, s3+l-1);
		calc(e1-r+1, e1, m+1, e2, e3-r, e3-1);
	}
}
