package A2021_04_30;

import java.util.*;
import java.io.*;

public class BOJ_1219_오민식의_고민_fail {
//	static long[][] graph;
	static List<Node>[] graph;
	static LinkedList<Integer> list;
	static int N, start, end, M;
	static boolean[] isValid, visited;
	static long[] money;
	static boolean isInfinity;
	static long answer = Long.MIN_VALUE;
	static long[] cost;
	static final long INF = Long.MAX_VALUE;
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N];
		cost = new long[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		list = new LinkedList<>();
		isValid = new boolean[N];
		visited = new boolean[N];
		money = new long[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			graph[a].add(new Node(b, w));
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			money[i] = Long.parseLong(st.nextToken()) ;
		}
		
		visited[start] = true;
		travel(start);

//		System.out.println(Arrays.toString(isValid));
		
		if (!isValid[end]) {
			System.out.println("gg");
			return;
		}
		
		
		cost[start] = money[start];
		dfs(start, money[start]);
		
//		System.out.println(Arrays.toString(cost));
		if (isInfinity) {
			System.out.println("Gee");
		} else {
			System.out.println(answer);
		}
	}
	
	
	private static void travel(int v) {
		if (v == end) {
			if (!isValid[start]) isValid[start] = true;
			for (int vertex : list) {
				isValid[vertex] = true;
			}
			return;
		}
		
		for (Node next : graph[v]) {
			int next_v = next.v;
			if (visited[next_v]) continue;
			
			visited[next_v] = true;
			list.add(next_v);
			travel(next_v);
			visited[next_v] = false;
			list.removeLast();
		}
		
	}
	
	
	private static void dfs(int v, long sum) {
		if (isInfinity) return;
		
		if (v == end) {
			for (Node next : graph[v]) {
				int next_v = next.v;
				long next_w = next.w;
				
				long next_sum = sum;
				if (visited[next_v]) {
					if (isValid[next_v]) {
						next_sum += money[next_v] - next_w;
						if (next_sum - cost[next_v] > 0) isInfinity = true;
					}
					continue;
				}
			}
			
			answer = Math.max(answer, sum);
			return;
		}
		for (Node next : graph[v]) {
			int next_v = next.v;
			long next_w = next.w;
			
			long next_sum = sum;
			if (visited[next_v]) {
				if (isValid[next_v]) {
					next_sum += money[next_v] - next_w;
					if (next_sum - cost[next_v] > 0) isInfinity = true;
				}
				continue;
			}
			
			visited[next_v] = true;
			cost[next_v] = sum + money[next_v] - next_w;
			dfs(next_v, sum + money[next_v] - next_w);
			visited[next_v] = false;
		}
	}

	static class Node {
		int v;
		long w;
		
		public Node(int v, long w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
