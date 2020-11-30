package A2020_11_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트Fail_dfs {
	static int N,answer;
	static int[] graph;
	static boolean[] cyclic,visited;
	static int revisitedVertex;
	static boolean meetRevisitedVertex; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < TC; t++) {
			answer = 0;
			N = Integer.parseInt(br.readLine());
			graph = new int[N+1];
			cyclic = new boolean[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N ; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N ; i++) {
				if(!cyclic[i]) {
					revisitedVertex = -1;
					meetRevisitedVertex = false;
					visited = new boolean[N+1];
					
					dfs(i);
					if(!cyclic[i]) { 
						answer++;
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int v) {
		if(cyclic[graph[v]]) return;
		
		if(visited[graph[v]]) {
			revisitedVertex = graph[v];
			cyclic[v] = true;
			return;
		}
		visited[v] = true;
		
		dfs(graph[v]);
		if(revisitedVertex!=-1) {
			if(revisitedVertex==v) {
				meetRevisitedVertex = true;
				cyclic[v] = true;
			}
			else if(!meetRevisitedVertex) {
				cyclic[v] = true;
			}
		}
	}
}
