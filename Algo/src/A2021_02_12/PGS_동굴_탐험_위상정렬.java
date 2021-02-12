package A2021_02_12;

import java.util.*;

/**
 * 
 * @author beaverbae
 * @see https://dong-wook94.github.io/algorithm/2020/09/02/algorithm-programmers-67260/
 * 
 */

public class PGS_동굴_탐험_위상정렬 {
	private List<Integer>[] graph;// 양방향 그래프
	private List<Integer>[] adjList;// 위상정렬을 위한 방향 그래프
	private int[] cntInDegree;// 진입차수

	public boolean solution(int n, int[][] path, int[][] order) {
		// 초기화
		graph = new List[n];
		adjList = new List[n];
		cntInDegree = new int[n];

		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
			adjList[i] = new ArrayList<>();
		}

		// 양방향 그래프 생성
		for (int i = 0; i < path.length; i++) {
			int a = path[i][0];
			int b = path[i][1];

			graph[a].add(b);
			graph[b].add(a);
		}

		// order배열에 나타난 선행관계 표현
		for (int i = 0; i < order.length; i++) {
			int from = order[i][0];
			int to = order[i][1];

			adjList[from].add(to);
			cntInDegree[to]++;// to정점의 진입차수 추가
		}

		bfs(n);

		// 위상 정렬 진행
		Queue<Integer> q = new LinkedList<>();
		List<Integer> result = new ArrayList<>();// 위상 정렬한 결과

		for (int i = 0; i < cntInDegree.length; i++) {
			if (cntInDegree[i] == 0) {// 진입 차수가 0인 (선행 노드가 없는) 노드 모두 추가
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int v = q.poll();
			result.add(v);

			for (int next_v : adjList[v]) {
				cntInDegree[next_v]--;// 선행된 노드를 하나 지났으므로 진입차수 1빼기
				if (cntInDegree[next_v] == 0) {
					q.offer(next_v);
				}
			}
		}

		if (result.size() < n)
			return false;
		return true;
	}

	// 0번부터 bfs 탐색
	private void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];

		q.offer(0);
		visited[0] = true;

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : graph[v]) {
				if (visited[next_v])
					continue;

				q.offer(next_v);
				visited[next_v] = true;

				// 연결된 두 정점을 방향 그래프로 나타내고, 진입차수 추가
				adjList[v].add(next_v);
				cntInDegree[next_v]++;
			}
		}
	}

	public static void main(String[] args) {
		new PGS_동굴_탐험_위상정렬().solution(9,
				new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
				new int[][] { { 8, 5 }, { 6, 7 }, { 4, 1 } });
		new PGS_동굴_탐험_위상정렬().solution(9,
				new int[][] { { 8, 1 }, { 0, 1 }, { 1, 2 }, { 0, 7 }, { 4, 7 }, { 0, 3 }, { 7, 5 }, { 3, 6 } },
				new int[][] { { 4, 1 }, { 5, 2 } });
		new PGS_동굴_탐험_위상정렬().solution(9,
				new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
				new int[][] { { 4, 1 }, { 8, 7 }, { 6, 5 } });
	}
}
