package A2021_02_06;

import java.util.*;
import java.io.*;

public class BOJ_12851_숨바꼭질2 {
	static int min_time;
	static int min_time_cnt;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
	
		bfs(start, end);
		System.out.println(min_time);
		System.out.println(min_time_cnt);
	}
	
	private static void bfs(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		int[] visited = new int[100001];
		min_time = INF;
		
		q.offer(new Node(start, 0));
		visited[start] = 1;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int time = node.time;
			
			if (end == x) {
				min_time = time;
				min_time_cnt=visited[end];
				break;
			}
			
			// -1
			int nx = x - 1;
			if (isIn(nx)) {
				if (visited[nx] == 0) {
					q.offer(new Node(nx, time+1));
				}
				visited[nx]++;
			}
			
			// +1
			nx = x + 1;
			if (isIn(nx)) {
				if (visited[nx] == 0) {
					q.offer(new Node(nx, time+1));
				}
				visited[nx]++;
			}
			
			// x2
			nx = x + x;
			if (isIn(nx)) {
				if (visited[nx] == 0) {
					q.offer(new Node(nx, time+1));
				}
				visited[nx]++;
			}
			visited[x] = 0;
		}
	}
	
	private static boolean isIn(int x) {
		return x>=0 && x <=100000;
	}
	
	static class Node {
		int x, time;

		public Node(int x, int time) {
			this.x = x;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", time=" + time + "]";
		}
	}
}
