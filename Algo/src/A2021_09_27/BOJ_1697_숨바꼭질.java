package A2021_09_27;
import java.util.*;
import java.io.*;

/**
 * BFS
 * 9MIN
 * @author beaverbae
 * 메모리 초과 주의!
 */

public class BOJ_1697_숨바꼭질 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, K;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		System.out.println(bfs(N, K));
	}
	
	private static int bfs(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		q.offer(new Node(start, 0));
		visited.add(start);
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.v < 0 || cur.v > 100000) continue;
			if (cur.v == end) return cur.w;
			
			// 이동
			if (!visited.contains(cur.v - 1)) {
				q.offer(new Node(cur.v - 1, cur.w + 1));
				visited.add(cur.v - 1);
			}
			if (!visited.contains(cur.v + 1)) {
				q.offer(new Node(cur.v + 1, cur.w + 1));
				visited.add(cur.v + 1);
			}
			if (!visited.contains(cur.v * 2)) {
				q.offer(new Node(cur.v * 2, cur.w + 1));
				visited.add(cur.v  * 2);
			}
		}
		
		return -1;
	}
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
