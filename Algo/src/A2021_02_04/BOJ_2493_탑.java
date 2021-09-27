package A2021_02_04;

import java.util.*;
import java.io.*;

public class BOJ_2493_탑 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// N 받아오기
		
		StringTokenizer st = new StringTokenizer(br.readLine());// 한 줄 읽기 -> 탑 요소들 잃어오기
		Stack<Node> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(st.nextToken());// 탑 하나씩 꺼냅
			
			while (!stack.isEmpty()) {
				Node node = stack.peek();
				if (node.value < v) {
					stack.pop();
				} else {
					stack.push(new Node(i, v));
					sb.append((node.idx + 1)).append(" ");
					break;
				}
			}

			if (stack.isEmpty()) {
				stack.push(new Node(i, v));
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
