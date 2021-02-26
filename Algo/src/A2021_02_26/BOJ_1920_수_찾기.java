package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * Binary Search
 * @author beaverbae
 *
 */

public class BOJ_1920_수_찾기 {
	static int N, M;
	static int[] input, arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		arr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(binary_search(arr[i])).append("\n");
		}
		System.out.println(sb.toString());
	}

	static int binary_search(int target) {
		int start = 0;
		int end = N - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (target == input[mid]) {
				return 1;
			} else if (target < input[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return 0;
	}
}
