package A2020_12_28;

import java.util.*;
import java.io.*;

public class BOJ_16947_서울_지하철_2호선 {
	static List<Integer>[] graph;
	static int V;
	static boolean[] cycle;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		for (int i = 1; i <= V; i++) {
			cycle = new boolean[V+1];
			if(isCycle(i,i,i)) {
				break;
			}
			System.out.println();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < cycle.length; i++) {
			int dist = 0;
			if(!cycle[i]) {
				dist = bfs(i);
			}
			sb.append(dist).append(" ");
		}
		
		System.out.println(sb);
		
	}
	
	static int bfs(int start) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[V+1];
		q.offer(new int[] {start, 0});
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int[] pair = q.poll();
			int v = pair[0];
			int dist  = pair[1];
		
			for (int next : graph[v]) {
				if (visited[next]) continue;
				if (cycle[next]) return dist+1;
				
				q.offer(new int[] {next, dist+1});
				visited[next] = true;
			}
		}
		
		return -1;//실제로 리턴X
	}

	//사이클 생성
	static boolean isCycle(int prev, int v, int start) {
//		System.out.println("before : "+v);
		cycle[v] = true;//방문체크
		
		for (int next : graph[v]) {
			if (!cycle[next]) {//방문x
				if (isCycle(v, next, start)) {//사이클인지 확인
					return true;
				}
//				isCycle(v, next, start);
			}
			//방문했던 노드 재방문한 경우
			else if(next != prev && next == start) {
//				System.out.println("here");
				return true;
			}
		}
		cycle[v] = false;//다시 돌아오게 된 경우 false임
//		System.out.println("after : "+v);
		
		return false;
	}

	

}
