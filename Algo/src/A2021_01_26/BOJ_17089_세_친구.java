package A2021_01_26;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 
 * @author beaverbae
 *
 */

public class BOJ_17089_세_친구 {
	static List<Integer>[] graph;// 인접리스트
	static int N, M;
	static boolean[] visited;// 방문 확인
	static LinkedList<Integer> friends;// 세 친구
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			graph[start].add(end);
			graph[end].add(start);
		}

		visited = new boolean[N + 1];
		friends = new LinkedList<>();
		answer = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			friends.addLast(i);
			dfs(i, i, 0);
			visited[i] = false;
			friends.removeLast();
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static void dfs(int start, int v, int cnt) {
		if (cnt == 2) {// 사이클 확인
			for (int next_v : graph[v]) {
				if (visited[next_v] && next_v == start) {// 방문한 정점이 시작 정점인 경우 사이클 형성
					answer = Math.min(answer, getFriends());
				}
			}
			return;
		}
		
		
		// 나머지 경우 
		for (int next_v : graph[v]) {
			if (visited[next_v]) 
				continue;

			visited[next_v] = true;
			friends.addLast(next_v);
			dfs(start, next_v, cnt + 1);
			visited[next_v] = false;
			friends.removeLast();
		}
	}

	private static int getFriends() {
		int result = 0;
		for (int v : friends) {
			for (int next_v : graph[v]) {// 친구 확인
				result++;// 친구 수만큼 추가
			}
		}

		result -= 6; // A 는 B,C 제와, B는 A,C 체외, C는 A,B 제외
		return result;
	}
}
