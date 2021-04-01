package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * @author beaverbae
 *
 */

public class BOJ_11441_합_구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] prefix_sum = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < prefix_sum.length; i++) {
			prefix_sum[i] = prefix_sum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			sb.append((prefix_sum[b]-prefix_sum[a-1])).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
