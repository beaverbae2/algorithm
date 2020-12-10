package A2020_12_10;

import java.util.*;

public class PGS_네트워크 {
	public static int solution(int n, int[][] computers) {
		int answer = 0;
		boolean[][] graph = new boolean[n][n];
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(computers[i][j] == 1) {
					graph[i][j] = true;
					graph[j][i] = true;//
				}
			}
		}
		
		for (int i = 0; i < graph.length; i++) {
			if(!visited[i]) {
				if(bfs(graph, visited, i)) answer++;//
			}
		}
		
		return answer;
	}

	private static boolean bfs(boolean[][] graph, boolean[] visited, int start) {
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);//
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for (int i = 0; i < graph[v].length; i++) {
				if(graph[v][i]&&!visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
		System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
	}
}
