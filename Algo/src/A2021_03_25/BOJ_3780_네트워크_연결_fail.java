package A2021_03_25;

import java.util.*;

import java.io.*;

public class BOJ_3780_네트워크_연결_fail {
	static int N;
	static int[] len;
	static final int MOD = 1000;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new List[N+1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}
			
			len = new int[N+1];
			
			while(true) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				if (command == 'O') {
					break;
				} else if (command == 'E') {
					int v = Integer.parseInt(st.nextToken());
					sb.append(len[v]).append("\n");
				} else if (command == 'I') {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
				
					graph[b].add(a);
					int l = (len[b] + Math.abs(a-b)) % MOD;
					bfs(a, l);
				}
			}
			
		}
		System.out.println(sb.toString());
	}
	
	public static void bfs(int start, int l) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int v = q.poll();
			
			len[v] = (len[v] + l) % MOD;
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				visited[next_v] = true;
				q.offer(next_v);
			}
		}
	}
}
