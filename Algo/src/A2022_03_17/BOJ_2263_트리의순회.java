package A2022_03_17;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 20MIN
 * @author beaverbae
 * - 4266번과 동일
 * 
 */

public class BOJ_2263_트리의순회 {
	static int N;
	static int[] pre, in, post;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		pre = new int[N];
		in = new int[N];
		post = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			post[i] = Integer.parseInt(st.nextToken());
		}
		
		calc(0, N-1, 0, N-1, 0, N-1);
		
		for (int i = 0; i < N; i++) {
			sb.append(pre[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void calc(int s1, int e1, int s2, int e2, int s3, int e3) {
		if (s1 > e1) return;
		
		int v = post[e1];
		pre[s3] = v;
		
		int m = -1, l, r;
		for (int i = s2; i <= e2; i++) {
			if (in[i] == v) {
				m = i;
				break;
			}
		}
		l = m - s2;
		r = e2 - m;
		
		calc(s1, s1+l-1, s2, m-1, s3+1, s3+l);
		calc(e1-r, e1-1, m+1, e2, e3-r+1, e3);
	}
}
