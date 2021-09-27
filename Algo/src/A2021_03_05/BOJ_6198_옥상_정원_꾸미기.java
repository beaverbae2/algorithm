package A2021_03_05;

import java.util.*;

import java.io.*;

/**
 * Stack
 * 30MIN
 * @author beaverbae
 *
 */

public class BOJ_6198_옥상_정원_꾸미기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Pair> stack = new Stack<>();
		long answer = 0;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());

			while (!stack.isEmpty()) {
				Pair top = stack.peek();

				if (top.height <= h) {
					answer += i - top.idx - 1;
					stack.pop();
				} else
					break;
			}

			stack.push(new Pair(i, h));
		}

		if (!stack.isEmpty()) {
			int last_idx = stack.peek().idx;

			while (!stack.isEmpty()) {
				answer += last_idx - stack.pop().idx;
			}
		}

		System.out.println(answer);
	}

	static class Pair {
		int idx, height;

		public Pair(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}

		@Override
		public String toString() {
			return "Pair [idx=" + idx + ", height=" + height + "]";
		}
	}
}
