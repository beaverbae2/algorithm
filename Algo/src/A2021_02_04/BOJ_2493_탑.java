package A2021_02_04;

import java.util.*;
import java.io.*;

public class BOJ_2493_탑 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Node> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty()) {
				Node node = stack.peek();
				if (node.value < v) {
					stack.pop();
				} else {
					stack.add(new Node(i, v));
					sb.append((node.idx + 1)).append(" ");
					break;
				}
			}

			if (stack.isEmpty()) {
				stack.add(new Node(i, v));
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	static class Node {
		int idx, value;

		public Node(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", value=" + value + "]";
		}
	}
}
