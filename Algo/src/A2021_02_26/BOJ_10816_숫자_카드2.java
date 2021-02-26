package A2021_02_26;

import java.util.*;
import java.io.*;

/**
 * Binary Search
 * @author beaverbae
 *
 */

public class BOJ_10816_숫자_카드2 {
	static int[] input, arr;
	static int N, M;
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		map = new HashMap<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());

			if (!map.containsKey(input[i])) {
				map.put(input[i], 1);
			} else {
				map.put(input[i], map.get(input[i]) + 1);
			}
		}

		M = Integer.parseInt(br.readLine());
		arr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			sb.append(binary_search(arr[i])).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static int binary_search(int target) {
		int start = 0;
		int end = N - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (target == input[mid]) {
				return map.get(target);
			} else if (target < input[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return 0;
	}
}
