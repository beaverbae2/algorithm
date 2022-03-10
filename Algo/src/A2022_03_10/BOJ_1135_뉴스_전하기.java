package A2022_03_10;

import java.util.*;
import java.io.*;

/**
 * BFS, Greedy
 * 61MIN
 * @author beaverbae
 * - Tree DP로도 풀어보자
 */

public class BOJ_1135_뉴스_전하기 {
	static int N, L;
	static int[] parent, dist;
	static List<Integer>[] graph;
	static Map<Integer, ArrayList<Integer>> level;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		dist = new int[N];
		graph = new List[N];
		level = new HashMap<>();
		for (int v = 0; v < N; v++) {
			graph[v] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int v = 0; v < N; v++) {
			int p = Integer.parseInt(st.nextToken());
			parent[v] = p;
			if (p == -1) continue;
		
			graph[p].add(v);
		}
		
		bfs(0);
		calc();
		System.out.println(dist[0]);
	}

	private static void calc() {
		boolean[] visited = new boolean[N];
		for (int l = L; l > 0; l--) {
			List<Integer> list = level.get(l);
			
			for (int v : list) {
				if (visited[v]) continue;
				int p = parent[v];
				
				List<Integer> list1 = new ArrayList<>();
				for (int i = 1; i <= graph[p].size(); i++) {
					list1.add(i);
				}
				
				List<Integer> list2 = new ArrayList<>();
				for (int c : graph[p]) {
					list2.add(dist[c]);
					visited[c] = true;
				}
				Collections.sort(list2, Collections.reverseOrder());
				
				int d = 0;
				for (int i = 0; i < graph[p].size(); i++) {
					int a = list1.get(i);
					int b = list2.get(i);
					d = Math.max(d, a+b);
				}
				dist[p] = d;
			}
		}
	}

	private static void bfs(int s) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		q.offer(new int[] {s, 0});
		visited[s] = true;
		level.put(0, new ArrayList<>(s));
		int d = 0;
		
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int v = arr[0];
			d = arr[1];
			L = d;
			
			int nd = d + 1;
			for (int nv : graph[v]) {
				if (visited[nv]) continue;
				
				if (!level.containsKey(nd)) {
					level.put(nd, new ArrayList<>());
				} 
				
				List<Integer> list = level.get(nd);
				q.offer(new int[] {nv, nd});
				visited[nv] = true;
				list.add(nv);
			}
		}
	}	
}
