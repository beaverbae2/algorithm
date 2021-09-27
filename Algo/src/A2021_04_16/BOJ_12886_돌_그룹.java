package A2021_04_16;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 17MIN
 * @author beaverbae
 *
 */

public class BOJ_12886_돌_그룹 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
	
		System.out.println(bfs(a, b, c));
	}
	
	private static int bfs(int sa, int sb, int sc) {
		Queue<Node> q = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		
		q.offer(new Node(sa, sb, sc));
		visited.add(getString(sa, sb, sc));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int a = node.a;
			int b = node.b;
			int c = node.c;
		
			if (a == b && b == c) return 1;
			
			// a, b
			if (a < b) {
				int na = a+a;
				int nb = b-a;
				int nc = c;
				
				String next = getString(na, nb, nc);
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(na, nb, nc));
				}
			} else if (a > b) {
				int na = a-b;
				int nb = b+b;
				int nc = c;
				
				String next = getString(na, nb, nc);
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(na, nb, nc));
				}
			}
			
			// b, c
			if (b < c) {
				int na = a;
				int nb = b+b;
				int nc = c-b;
				
				String next = getString(na, nb, nc);
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(na, nb, nc));
				}
			} else if (b > c) {
				int na = a;
				int nb = b-c;
				int nc = c+c;
				
				String next = getString(na, nb, nc);
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(na, nb, nc));
				}
			}
			// c, a
			if (c < a) {
				int na = a-c;
				int nb = b;
				int nc = c+c;
				
				String next = getString(na, nb, nc);
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(na, nb, nc));
				}
			} else if (c > a) {
				int na = a+a;
				int nb = b;
				int nc = c-a;
				
				String next = getString(na, nb, nc);
				if (!visited.contains(next)) {
					visited.add(next);
					q.offer(new Node(na, nb, nc));
				}
			}
		}
		
		return 0;
	}
	
	private static String getString(int a, int b, int c) {
		StringBuilder sb = new StringBuilder();
		sb.append(a).append(" ").append(b).append(" ").append(c);
		return sb.toString();
	}
	
	static class Node {
		int a, b, c;

		public Node(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", c=" + c + "]";
		}
	}
}
