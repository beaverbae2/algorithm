package A2020_11_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458_키순서 {
	static int N, M;
	static List<Integer>[] tallGraph;
	static List<Integer>[] smallGraph;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tallGraph = new List[N+1];
		smallGraph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			tallGraph[i] = new ArrayList<>();
			smallGraph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tallGraph[a].add(b);
			smallGraph[b].add(a);
		}
		for (int v = 1; v <= N; v++) {
			int sum = bfs(v);
			if(sum==N) { 
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int result = 1;
		
		q.offer(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int vertex = q.poll();
			
			for(int next_vertex : tallGraph[vertex]) {
				if(visited[next_vertex]) continue;
				
				q.offer(next_vertex);
				visited[next_vertex] = true;
				result++;
			}
		}
		
		q = new LinkedList<>();
		visited = new boolean[N+1];
		q.offer(v);
		visited[v] = true;
		
		
		while(!q.isEmpty()) {
			int vertex = q.poll();
			
			for(int next_vertex : smallGraph[vertex]) {
				if(visited[next_vertex]) continue;
				
				q.offer(next_vertex);
				visited[next_vertex] = true;
				result++;
			}
		}
		
		return result;
	}
}


