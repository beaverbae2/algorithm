package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * Prefix sum
 * @author beaverbae
 *
 */

public class BOJ_21318_피아노_체조_ver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] prefix_sum = new int[N+1];
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if (arr[i-1] > arr[i]) {
				prefix_sum[i] = prefix_sum[i-1] + 1;
			} else {
				prefix_sum[i] = prefix_sum[i-1];
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append((prefix_sum[end] - prefix_sum[start])).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
