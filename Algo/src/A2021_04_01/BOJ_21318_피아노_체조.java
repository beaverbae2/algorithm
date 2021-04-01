package A2021_04_01;

import java.util.*;
import java.io.*;

/**
 * Hash
 * 22MIN
 * @author beaverbae
 *
 */

public class BOJ_21318_피아노_체조 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> map = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		map.put(1, 0);
		for (int i = 2; i <= N; i++) {
			int prev = arr[i-1];
			int value = map.get(i-1);
		
			if (prev > arr[i]) {
				map.put(i, value+1);
			} else {
				map.put(i, value);
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			int ans = map.get(end) - map.get(start);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
