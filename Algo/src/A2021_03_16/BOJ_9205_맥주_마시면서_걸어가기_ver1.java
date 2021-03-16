package A2021_03_16;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class BOJ_9205_맥주_마시면서_걸어가기_ver1 {
	static List<Pair> list;
	static List<Integer>[] graph;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine())+2;
			list = new ArrayList<>();
			graph = new List[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			// list 구성
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
			
				list.add(new Pair(x, y));
			}
			
			// list -> graph
			// 거리 1000 이하만 연결
			for (int i = 0; i < list.size(); i++) {
				Pair p1 = list.get(i);
				
				for (int j = i+1; j < list.size(); j++) {
					Pair p2 = list.get(j);
					
					int d = Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
				
					if (d > 1000) continue;
					
					graph[i].add(j);
					graph[j].add(i);
				}
			}
			
			// bfs
			sb.append(bfs()).append("\n");
		
		}
		
		System.out.println(sb.toString());
	}
	
	private static String bfs() {
		// 초기화
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		q.offer(0);
		visited[0] = true;
		
		// 탐색
		while(!q.isEmpty()) {
			int v = q.poll();
			
			if (v == N-1) return "happy";
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				q.offer(next_v);
				visited[next_v] = true;
			}
		}
		
		
		return "sad";
	}
	
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
	}
}
