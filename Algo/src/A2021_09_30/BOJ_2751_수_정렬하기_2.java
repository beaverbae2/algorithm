package A2021_09_30;

import java.util.*;
import java.io.*;

/**
 * Sorting
 * 3MIN
 * @author beaverbae
 *
 */

public class BOJ_2751_수_정렬하기_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		for (int n : arr) {
			sb.append(n).append("\n");
		}
		
		System.out.println(sb);
	}
}
