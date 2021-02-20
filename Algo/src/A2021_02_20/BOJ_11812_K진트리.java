package A2021_02_20;

import java.io.*;
import java.util.*;

/**
 * LCA
 * 
 * @author beaverbae
 * @see https://manzoo.tistory.com/88
 *
 */

public class BOJ_11812_K진트리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			long cnt = 0;

			if (K == 1) {
				cnt = Math.abs(a - b);
			} else {
				// 최소 공통 조상 찾기
				while (a != b) {
					if (a < b) {
						b = 1 + (b - 2) / K;
					} else {
						a = 1 + (a - 2) / K;
					}
					cnt++;
				}
			}

			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
