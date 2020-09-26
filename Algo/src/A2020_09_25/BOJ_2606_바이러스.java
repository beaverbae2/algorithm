package A2020_09_25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs
public class BOJ_2606_바이러스 {
	static List<Integer>[] graph;
	static int N,M,answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		visited = new boolean[N+1];
		q.offer(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next_v : graph[v]) {
				if(visited[next_v]) continue;
				q.offer(next_v);
				visited[next_v] = true;
				answer++;
			}
		}
	}
}
