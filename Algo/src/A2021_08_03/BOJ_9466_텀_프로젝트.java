package A2021_08_03;

import java.util.*;
import java.io.*;

/**
 * Graph
 * 90MIN
 * 어려웠던 부분
 * - 주어진 문제의 그래프 특성 파악 : 하나의 노드에서 다른 노드로 가는 경로가 오직 하나
 * - 사이클 확인 : 최적화가 까다로웠음, 팀에 속하냐 속하지 않느냐를 어떤 자료구조로 표현 할지 어려웠음
 * @author beaverbae
 *
 */

public class BOJ_9466_텀_프로젝트 {
	static int N, ans;
	static int[] graph, team;
	static boolean[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			ans = 0;
			graph = new int[N+1];
			team = new int[N+1];
			visited = new boolean[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int v1 = 1; v1 <= N; v1++) {
				int v2 = Integer.parseInt(st.nextToken());
				graph[v1] = v2;
			}
			
			for (int v = 1; v <= N; v++) {
				if (visited[v]) continue;
				
				visited[v] = true;
				findPath(v);
			}
			
			for (int i = 1; i <= N; i++) {
				if (team[i] == -1) ans++;
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void findPath(int start) {
		Stack<Integer> stack = new Stack<>();
		int v = start;
		int next_v = graph[v];
		stack.push(v);
		
		while (!visited[next_v]) {
			visited[next_v] = true; 
			v = next_v;
			stack.push(v);
			next_v = graph[v];
		}
		
		if (team[next_v] == 0) {
			while (!stack.isEmpty()) {
				int top = stack.pop();
				team[top] = 1;
				if (top == next_v) break;
			}
		}
		
		while (!stack.isEmpty()) {
			team[stack.pop()] = -1;
		}
	}
}
