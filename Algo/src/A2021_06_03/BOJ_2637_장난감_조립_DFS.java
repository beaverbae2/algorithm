package A2021_06_03;

import java.util.*;
import java.io.*;

/**
 * DFS + DP
 * 95MIN
 * 
 * @author beaverbae
 * 
 * 어려웠던 이유
 * - 첨에 DFS로 했는데 시간초과 났다
 * - 그래서 메모이제이션이 필요하다고 판단
 *     - 메모이제이션 할때 메모리크기를 줄여야 했다 -> Map으로 기본 부품만을 나타내어 메모리를 최적화 했다
 *     - Map의 값이 Map을 나타내서 머리 구현하기 복잡했다
 *     - dfs의 리턴값이 Map이라 익숙하지 않았다
 * - 뭐야 이거 위상 정렬이었음??? -> 다시 풀어야겠네...
 *
 */

public class BOJ_2637_장난감_조립_DFS {
	static int N, M;
	static List<Node>[] graph1, graph2;// 방향이 서로 반대인 두 그래프
	static boolean[] isBasic;
	static int[] basicCnt;
	static HashMap<Integer, HashMap<Integer, Integer>> map;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int root = -1;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph1 = new List[N+1];
		graph2 = new List[N+1];
		isBasic = new boolean[N+1];
		basicCnt = new int[N+1];
		sb = new StringBuilder();
		map = new HashMap<>();
		
		for (int i = 1; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int end = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			graph1[start].add(new Node(end, cost));
			graph2[end].add(new Node(start, cost));
		}
		
		// 기본 부품 찾기
		for (int v = 1; v <= N; v++) {
			if (graph2[v].size() != 0) {
				
				if (graph1[v].size() == 0) {
					root = v;
				}
				continue;
			}
			
			isBasic[v] = true;
		}
		
		
		
		Map<Integer, Integer> result_map = dfs(root);
		
		for (int v = 1; v <= N; v++) {
			if (!result_map.containsKey(v)) continue;
		
			sb.append(v).append(" ").append(result_map.get(v)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static Map<Integer, Integer> dfs(int v) {
		if (map.containsKey(v)) {
			return map.get(v);
		}
		
		map.put(v, new HashMap<>());
		
		Map<Integer, Integer> temp_map = map.get(v);
		
		for (Node next : graph2[v]) {
			if (isBasic[next.v]) {
				if (!temp_map.containsKey(next.v)) {
					temp_map.put(next.v, next.w);
				} else {
					temp_map.put(next.v, temp_map.get(next.v) + next.w);
				}
			}
			
			Map<Integer, Integer> result_map = dfs(next.v);
			
			Iterator<Integer> iter = result_map.keySet().iterator();
			while (iter.hasNext()) {
				int key = iter.next();
				
				if (!temp_map.containsKey(key)) {
					temp_map.put(key, result_map.get(key) * next.w);
				} else {
					temp_map.put(key, temp_map.get(key) + result_map.get(key) * next.w);
				}
			}
			
		}
		
		return map.get(v);
	}

	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
