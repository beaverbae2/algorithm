package A2021_02_24;

import java.util.*;
import java.io.*;

/**
 * Topological Sort
 * 35MIN
 * @author beaverbae
 *
 */

public class BOJ_3665_최종_순위 {
	static boolean[][] graph;
	static int[] enter_degree;
	static int[] nodes;
	static int N, M;
	static StringBuilder sb;
	static final int INF = 987654321;
	static List<Integer> path;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new boolean[N + 1][N + 1];
			enter_degree = new int[N + 1];
			nodes = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nodes[i] = Integer.parseInt(st.nextToken());
			}
			
			// 방향 그래프 및 진입 차수 초기 구성 (작년 순위)
			for (int i = 0; i < nodes.length; i++) {
				for (int j = i + 1; j < nodes.length; j++) {
					graph[nodes[i]][nodes[j]] = true;
					enter_degree[nodes[j]]++;
				}
			}
			
			// 올해 순위
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 진입 차수 변경
				if (graph[a][b]) {
					enter_degree[b]--;
					enter_degree[a]++;
				} else {
					enter_degree[a]--;
					enter_degree[b]++;
				}
				
				// swap
				boolean temp = graph[a][b];
				graph[a][b] = graph[b][a];
				graph[b][a] = temp;
			}

			topological_sort();// 위상 정렬
		}

		System.out.println(sb.toString());
	}

	private static void topological_sort() {
		// 초기화
		boolean isSingle = true;
		Queue<Integer> q = new LinkedList<>();
		path = new ArrayList<>();

		int cnt = 0;// 하나의 팀의 다음 순위로 나올 수 있는 팀의 개수
		for (int i = 1; i < enter_degree.length; i++) {
			if (enter_degree[i] != 0)
				continue;

			q.offer(i);
			path.add(i);
			cnt++;
		}

		if (cnt > 1)
			isSingle = false;

		while (!q.isEmpty()) {
			int v = q.poll();

			cnt = 0;
			for (int next_v = 1; next_v < graph.length; next_v++) {
				if (!graph[v][next_v])
					continue;

				enter_degree[next_v]--;
				if (enter_degree[next_v] == 0) {
					q.offer(next_v);
					path.add(next_v);
					cnt++;
				}
			}

			if (cnt > 1)
				isSingle = false;
		}

		if (path.size() != N) {// 순위 구성 자체가 불가능
			sb.append("IMPOSSIBLE").append("\n");
		} else {
			if (isSingle) {// 확실한 순위 구성 가능
				for (int i = 0; i < path.size(); i++) {
					int v = path.get(i);
					sb.append(v).append(" ");
				}
				sb.append("\n");
			} else {// 확실한 순위 구성 불가능
				sb.append("?").append("\n");
			}
		}

	}

}
