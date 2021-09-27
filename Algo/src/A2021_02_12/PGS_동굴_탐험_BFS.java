package A2021_02_12;

import java.util.*;

/**
 * 
 * @author beaverbae
 * @see https://tosuccess.tistory.com/149
 * 
 */

public class PGS_동굴_탐험_BFS {
	private List<Integer>[] graph;
	private int[] savePoint;
	private int[] before;
	private boolean[] visited;
	
	
	public boolean solution(int n, int[][] path, int[][] order) {
		graph = new List[n];
		savePoint = new int[n];
		before = new int[n];
		visited = new boolean[n];
		
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 그래프 생성
		for (int i = 0; i < path.length; i++) {
			int a = path[i][0];
			int b = path[i][1];
		
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 0; i < order.length; i++) {
			int a = order[i][0];
			int b = order[i][1];
		
			before[b] = a;
		}
		
		if (before[0] != 0) return false;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		visited[0] = true;
		
		for (int next_v : graph[0]) {
			q.offer(next_v);
		}
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			// 이미 방문한 노드인 경우
			if (visited[v]) continue;
			
			// 선행 방문해야되는 노드를 방문 안한 경우
			if (!visited[before[v]]) {
				savePoint[before[v]] = v;
				continue;
			}
			
			visited[v] = true;
			
			// 연결된 모든 노드 추가
			for (int next_v : graph[v]) {
				q.offer(next_v);
			}
			
			// 선행 방문 해야되는 노드도 추가
			q.add(savePoint[v]);
		}
		
		for (int v = 0; v < n; v++) {
			if (!visited[v]) return false;
		}
		
		return true;
	}

	public static void main(String[] args) {
		new PGS_동굴_탐험_BFS().solution(9 , new int[][] {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}}, new int[][] {{8,5},{6,7},{4,1}});
		new PGS_동굴_탐험_BFS().solution(9 , new int[][] {{8,1},{0,1},{1,2},{0,7},{4,7},{0,3},{7,5},{3,6}}, new int[][] {{4,1},{5,2}});
		new PGS_동굴_탐험_BFS().solution(9 , new int[][] {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}}, new int[][] {{4,1},{8,7},{6,5}});
	}
}
