package A2021_04_03;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 24MIN
 * @author beaverbae
 *
 */

public class BOJ_14867_물통 {
	static int A, B;
	static int end_A, end_B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		end_A = Integer.parseInt(st.nextToken());
		end_B = Integer.parseInt(st.nextToken());
	
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		
		q.offer(new Node(0, 0, 0));
		visited.add(0+" "+0);
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int a = node.a;
			int b = node.b;
			int w = node.w;
			
			if (a == end_A && b == end_B) {
				return w;
			}
			
			// F
			if (a < A) {
				String next = A+" "+b;
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(A, b, w+1));
				}
			}
			
			if (b < B) {
				String next = a+" "+B;
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(a, B, w+1));
				}
			}
			
			// E
			if (a > 0) {
				String next = 0+" "+b;
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(0, b, w+1));
				}
			}
			
			if (b > 0) {
				String next = a+" "+0;
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(a, 0, w+1));
				}
			}
			
			// M
			if (a > 0) {
				if (a+b <= B) {
					String next = 0+" "+(a+b);
					if (!visited.contains(next)) {
						visited.add(next);
						q.offer(new Node(0, a+b, w+1));
					}
				} else {
					int next_a = (a+b) - B;
					String next = next_a+" "+B;
					if (!visited.contains(next)) {
						visited.add(next);
						q.offer(new Node(next_a, B, w+1));
					}
				}
			}
			
			if (b > 0) {
				if (a+b <= A) {
					String next = (a+b)+" "+0;
					if (!visited.contains(next)) {
						visited.add(next);
						q.offer(new Node(a+b, 0, w+1));
					}
				} else {
					int next_b = (a+b) - A;
					String next = A+" "+next_b;
					if (!visited.contains(next)) {
						visited.add(next);
						q.offer(new Node(A, next_b, w+1));
					}
				}
			}
			
		}
		
		return -1;
	}
	
	static class Node {
		int a, b, w;

		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
	}
}
