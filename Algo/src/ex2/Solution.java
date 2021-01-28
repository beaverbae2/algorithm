package ex2;

import java.util.*;
//2021 THE FIRST HARF SINSEGAE INTERNATIONAL CODING TEST EX2
public class Solution {
	private int[][] answer;
	private int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	private final int INF = 987654321;

	static class Node implements Comparable<Node> {
		int r, c, depth;

		public Node(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.depth, o.depth);
		}
	}

	public int[][] solution(int N, int[][] bus_stop) {
		answer = new int[N][N];
		for (int i = 0; i < answer.length; i++) {
			Arrays.fill(answer[i], INF);
		}

		for (int i = 0; i < bus_stop.length; i++) {
			int r = bus_stop[i][0] - 1;
			int c = bus_stop[i][1] - 1;

			answer[r][c] = 0;
		}

		bfs(N);

//		for (int i = 0; i < answer.length; i++) {
//			System.out.println(Arrays.toString(answer[i]));
//		}
//		System.out.println();

		return answer;
	}

	public void bfs(int N) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];

		for (int r = 0; r < answer.length; r++) {
			for (int c = 0; c < answer[r].length; c++) {
				if (answer[r][c] == 0) {
					pq.offer(new Node(r, c, 0));
				}
			}
		}

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int r = node.r;
			int c = node.c;
			int depth = node.depth;

			if (visited[r][c])
				continue;
			visited[r][c] = true;

			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
					continue;

				if (answer[nr][nc] > answer[r][c] + 1) {
					answer[nr][nc] = answer[r][c] + 1;
					pq.offer(new Node(nr, nc, answer[r][c] + 1));
				}
			}
		}
	}

	public static void main(String[] args) {
		new Solution().solution(3, new int[][] { { 1, 2 } });
		new Solution().solution(3, new int[][] { { 1, 2 }, { 3, 3 } });
	}
}
