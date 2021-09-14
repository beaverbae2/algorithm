package A2021_09_14;

import java.util.*;
import java.io.*;

/**
 * Backtracking
 * 120MIN
 * @author beaverbae
 * 문제를 완전히 잘못읽었다. 
 * 
 */

public class BOJ_2026_소풍 {
	static int K, N, F;
	static List<Integer>[] graph; 
	static boolean[] visited;
	static LinkedHashSet<Integer> path;
	static LinkedList<Integer> answer_path;
	static boolean findAnswer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		graph = new List[N+1];
		visited = new boolean[N+1];
		path = new LinkedHashSet<>();
		answer_path = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while (F-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int v = 1; v <= N; v++) {
			Collections.sort(graph[v]);
		}
		
		for (int v = 1; v <= N; v++) {
			if (graph[v].size() < K-1) continue;
			
			path.add(v);
			combination(v, 1);
			path.remove(v);
			
			if (findAnswer) break;
		}
		
		if (answer_path.isEmpty()) System.out.println(-1);
		else {
			StringBuilder sb = new StringBuilder();
			for (int v : answer_path) {
				sb.append(v).append("\n");
			}
			System.out.println(sb);
		}
		
	}

	private static void combination(int v, int cnt) {
		if (findAnswer) return;
		if (cnt == K) {
			findAnswer = calcAnswer();
			if (findAnswer) copyAnswer();
			return;
		}
		
		for (int i = 0; i < graph[v].size(); i++) {
			int next_v = graph[v].get(i);
			if (path.contains(next_v)) continue;
			
			path.add(next_v);
			combination(next_v, cnt+1);
			path.remove(next_v);
		}
		
	}

	private static boolean calcAnswer() {
		for (int v1 : path) {
			int cnt = 1;
			for (int v2 : graph[v1]) {
				if (path.contains(v2)) cnt++;
			}
			if (cnt < K) return false;
		}
		
		return true;
	}

	private static void copyAnswer() {
		for (int v : path) {
			answer_path.add(v);
		}
	}

	
}
