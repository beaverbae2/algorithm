package A2020_11_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트Fail_bfs {
	static List<Integer>[] graph;
	static ArrayList<Integer> soloList;
	static boolean[] cyclic;
	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int i = 0; i < TC; i++) {
			N = Integer.parseInt(br.readLine());
			graph = new List[N + 1];
			for (int j = 1; j <= N; j++) {
				graph[j] = new ArrayList<>();
			}
			cyclic = new boolean[N + 1];
			answer = N;
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int a = 1; a <= N; a++) {
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
			}

			for (int j = 1; j <= N; j++) {
				bfs(j);
			}

//			for (int j = 1; j <= N; j++) {
//				if (!visited[j]) answer++;
//			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		//ArrayList<Integer> selected = new ArrayList<>();
		boolean[] temp_visited = new boolean[N + 1];
		boolean iscyclic = false;
		q.offer(start);
		//selected.add(start);

		while (!q.isEmpty()) {
			int vertex = q.poll();

			int next = graph[vertex].get(0);
			if (!temp_visited[next]) {
				q.offer(next);
				//selected.add(next);
				temp_visited[next] = true;
				
				if (next == start) iscyclic = true;
			}

			if (iscyclic) break;
		}

		if (iscyclic) {
			answer--;
		}

	}
}
