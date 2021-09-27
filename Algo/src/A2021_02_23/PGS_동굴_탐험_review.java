package A2021_02_23;

import java.util.*;

/**
 * Topological Sort
 * 
 * @author beaverbae
 *
 */

public class PGS_동굴_탐험_review {
	List<Integer>[] graph;// 양방향 그래프
	List<Integer>[] adjList;// 단방향 그래프
	int[] enter_degree;// 진입 차수
	List<Integer> road;// 위상 정렬한 결과

	public boolean solution(int n, int[][] path, int[][] order) {
		// 초기화
		graph = new List[n];
		adjList = new List[n];
		enter_degree = new int[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
			adjList[i] = new ArrayList<>();
		}

		// 그래프와 진입 차수 구현
		for (int i = 0; i < path.length; i++) {
			int a = path[i][0];
			int b = path[i][1];

			graph[a].add(b);
			graph[b].add(a);
		}

		bfs(0, n); // 단방향 그래프 생성을 위해 사용

		for (int i = 0; i < order.length; i++) {
			int a = order[i][0];
			int b = order[i][1];

			adjList[a].add(b);
			enter_degree[b]++;
		}

		// 위상 정렬
		Queue<Integer> q = new LinkedList<>();
		road = new ArrayList<>();

		// 진입 차수가 0인 노드 추가 (0번쨰 노드로 고정)
		q.offer(0);
		road.add(0);
		
		// 탐색
		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : adjList[v]) {
				enter_degree[next_v]--;
				if (enter_degree[next_v] == 0) {
					q.offer(next_v);
					road.add(next_v);
				}
			}
		}

		return road.size() == n;
	}

	void bfs(int start, int n) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];

		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next_v : graph[v]) {
				if (visited[next_v])
					continue;

				q.offer(next_v);
				visited[next_v] = true;
				enter_degree[next_v]++;
				adjList[v].add(next_v);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new PGS_동굴_탐험_review().solution(9,
				new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
				new int[][] { { 8, 5 }, { 6, 7 }, { 4, 1 } }));
		System.out.println(new PGS_동굴_탐험_review().solution(9,
				new int[][] { { 8, 1 }, { 0, 1 }, { 1, 2 }, { 0, 7 }, { 4, 7 }, { 0, 3 }, { 7, 5 }, { 3, 6 } },
				new int[][] { { 4, 1 }, { 5, 2 } }));
		System.out.println(new PGS_동굴_탐험_review().solution(9,
				new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
				new int[][] { { 4, 1 }, { 8, 7 }, { 6, 5 } }));
	}
}
