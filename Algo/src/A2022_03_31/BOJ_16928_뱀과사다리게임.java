package A2022_03_31;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 18MIN
 * @author beaverbae
 *
 */

public class BOJ_16928_뱀과사다리게임 {
	static int N, M, K = 100;
	static int[] ladder, snake;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ladder = new int[K+1];
		snake = new int[K+1];
		
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			ladder[s] = e;
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			snake[s] = e;
		}
		
		System.out.println(bfs(1));
	}
	
	private static int bfs(int s) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[K+1];
		
		q.offer(new Node(s, 0));
		visited[s] = true;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int v = n.v;
			int w = n.w;
			
			if (v == K) return w;
			
			for (int d = 1; d <= 6; d++) {
				int nv = v + d;
				
				if (nv > 100 || visited[nv]) continue;
				
				if (ladder[nv] != 0) nv = ladder[nv];
				else if (snake[nv] != 0) nv = snake[nv];
				
				q.offer(new Node(nv, w + 1));
				visited[nv] = true;
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
