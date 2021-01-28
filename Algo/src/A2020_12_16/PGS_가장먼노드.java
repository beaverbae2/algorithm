package A2020_12_16;

import java.util.*;

public class PGS_가장먼노드 {
	private List<Integer>[] graph;
	private boolean[] visited;
	private int max_depth;

	public int solution(int n, int[][] edge) {
		graph = new List[n + 1];
		visited = new boolean[n + 1];
		max_depth = 0;
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < edge.length; i++) {
			int a = edge[i][0];
			int b = edge[i][1];

			graph[a].add(b);
			graph[b].add(a);
		}

		getMaxDepth();
		int answer = getMaxNodes(n);
		return answer;
	}

	private int getMaxNodes(int n) {
		int result = 0;

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 0 });
		visited = new boolean[n + 1];
		visited[1] = true;

		while (!q.isEmpty()) {
			int[] pair = q.poll();
			int v = pair[0];
			int depth = pair[1];

			if (depth == max_depth)
				result++;

			for (int next_v : graph[v]) {
				if (visited[next_v])
					continue;
				q.offer(new int[] { next_v, depth + 1 });
				visited[next_v] = true;
			}
		}

		return result;
	}

	private void getMaxDepth() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 0 });
		visited[1] = true;

		while (!q.isEmpty()) {
			int[] pair = q.poll();
			int v = pair[0];
			int depth = pair[1];

			if (max_depth < depth)
				max_depth = depth;

			for (int next_v : graph[v]) {
				if (visited[next_v])
					continue;
				q.offer(new int[] { next_v, depth + 1 });
				visited[next_v] = true;
			}
		}
	}

	public static void main(String[] args) {
		PGS_가장먼노드 a = new PGS_가장먼노드();
		System.out.println(
				a.solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } }));
	}
}
