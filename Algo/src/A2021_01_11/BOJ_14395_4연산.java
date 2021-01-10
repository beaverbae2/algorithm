package A2021_01_11;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class BOJ_14395_4연산 {
	static long S, T;
	static char[] arr = {'*', '+', '-', '/'};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Long.parseLong(st.nextToken());
		T = Long.parseLong(st.nextToken());
		
		if (S == T) {
			System.out.println(0);
		}else {
			System.out.println(bfs());
		}
		
	}

	private static String bfs() {
		Queue<Node> q = new LinkedList<>();
		HashMap<Long, Boolean> visited = new HashMap<>();
		q.offer(new Node(S, ""));
		visited.put(S, true);
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			long num = node.num;
			String op = node.op;
			
			if (num == T) return op;
			
			long next_num = 0;
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == '*') {//곱하기
					next_num = num * num;
				}else if (arr[i] == '+') {//더하기
					next_num = num + num;
				}else if (arr[i] == '-') {//뺴기
					next_num = num - num;
				}else {//나누기
					next_num = 1;
				}
				
				if (checkValid(next_num) && visited.get(next_num) == null) {
					visited.put(next_num, true);
					q.offer(new Node(next_num, op+arr[i]));
				}
			}
		}
		
		return "-1";
	}
	
	private static boolean checkValid(long next_num) {
		return next_num > 0 && next_num <= 1000000000;
	}

	static class Node {
		long num;
		String op;
		
		public Node(long num, String op) {
			this.num = num;
			this.op = op;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", op=" + op + "]";
		}
	}
}
