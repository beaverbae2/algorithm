package A2021_03_02;

import java.util.*;
import java.io.*;

/**
 * Two Pointers
 * 24MIN
 * @author beaverbae
 *
 */

public class BOJ_3273_두_수의_합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		if (N == 1)
			System.out.println(0);
		else {
			int[] input = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < input.length; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(input);

			int X = Integer.parseInt(br.readLine());

			int start = 0;
			int end = N - 1;
			int answer = 0;

			while (start < end) {
				int a = input[start];
				int b = input[end];
				int sum = a + b;

				if (sum < X) {
					start++;
				} else {
					if (sum == X) {
						answer++;
					}

					end--;
				}
			}

			System.out.println(answer);
		}
	}
}
