package A2021_04_02;

import java.util.*;
import java.io.*;

public class BOJ_1613_역사_fail {
	static List<Integer>[] graph;
	static List<Integer>[] parent;
	static Map<Integer, Integer>[] path;
	static int N, M, K;
	static int[] enter_degree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new List[N+1];
		parent = new List[N+1];
		path = new Map[N+1];
		enter_degree = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			parent[i] = new ArrayList<>();
			path[i] = new HashMap<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			// 단방향
			graph[a].add(b);
			enter_degree[b]++;
		}
		for (int i = 1; i <= N; i++) {
			if (enter_degree[i] == 0 && graph[i].size() > 0) {
				bfs(i);
			}
		}
		
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			boolean flag = false;
			int parent_a = -1;
			int parent_b = -1;
			
			for (int j = 0; j < parent[a].size(); j++) {
				parent_a = parent[a].get(j);
				for (int k = 0; k < parent[b].size(); k++) {
					parent_b = parent[b].get(k);
				
					if (parent_a == parent_b) { 
						flag = true;
						break;
					}
				}
				if (flag) break;
			}
			
			if (!flag) {
				sb.append(0).append("\n");
			} else {
				int p = parent_a;
				
				int v1 = path[p].get(a);
				int v2 = path[p].get(b);
				
				if (v1 == v2) {
					sb.append(0).append("\n");
				} else if (v1 < v2) {
					sb.append(-1).append("\n");
				} else if (v2 < v1) {
					sb.append(1).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}

	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(new Node(start, 0));
		visited[start] = true;
		parent[start].add(start);
		path[start].put(start, 0);
		
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int v = node.v;
			int depth = node.depth;
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				q.offer(new Node(next_v, depth+1));
				visited[next_v] = true;
				parent[next_v].add(start);
				path[start].put(next_v, depth+1);
			}
		}
	}
	
	static class Node {
		int v, depth;

		public Node(int v, int depth) {
			this.v = v;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", depth=" + depth + "]";
		}
	}
}
