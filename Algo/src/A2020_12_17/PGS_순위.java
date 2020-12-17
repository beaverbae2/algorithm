package A2020_12_17;

import java.util.*;

public class PGS_순위 {
	private List<Integer>[] graph1;
	private List<Integer>[] graph2;

	public int solution(int n, int[][] results) {
		graph1 = new List[n + 1];
		graph2 = new List[n + 1];

		for (int i = 1; i <= n; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}

		// 그래프 만들기
		for (int i = 0; i < results.length; i++) {
			int a = results[i][0];
			int b = results[i][1];

			graph1[a].add(b);
			graph2[b].add(a);
		}

		int answer = 0;

		for (int i = 1; i <= n; i++) {
			if (bfs(i, n))
				answer++;
		}

		return answer;
	}

	public boolean bfs(int start_v, int n) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		q.offer(start_v);
		visited[start_v] = true;

		int cnt = 1;
		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : graph1[v]) {
				if (visited[next_v])
					continue;
				q.offer(next_v);
				visited[next_v] = true;
				cnt++;
			}
		}

		q = new LinkedList<>();
		visited = new boolean[n + 1];
		q.offer(start_v);
		visited[start_v] = true;

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : graph2[v]) {
				if (visited[next_v])
					continue;
				q.offer(next_v);
				visited[next_v] = true;
				cnt++;
			}
		}

		if (cnt == n)
			return true;
		return false;
	}

	public static void main(String[] args) {
		PGS_순위 a = new PGS_순위();
		System.out.println(a.solution(5, new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } }));
	}
}
