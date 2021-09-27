package A2021_04_19;

import java.util.*;
import java.io.*;

public class BOJ_11967_불켜기_ver2 {
	
	static int N, M;
	static List<Integer>[] list;
	static boolean[] isLight;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N*N];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		isLight = new boolean[N*N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			int idx1 = x * N + y;
			int idx2 = a * N + b;
			
			list[idx1].add(idx2);
		}
		
		System.out.println(bfs(0, 0));
	}
	
	private static int bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N*N];
		HashSet<Integer> set = new HashSet<>();
		
		q.offer(new Node(sr, sc));
		visited[sr * N + sc] = true;
		set.add(sr * N + sc);
		isLight[sr * N + sc] = true;
		int cnt = 1;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int idx = r * N + c;
			
			set.remove(idx);
			
			// 불 켜기
			for (int n_idx : list[idx]) {
				if (!isLight[n_idx]) {
					isLight[n_idx] = true;
					cnt++;
				}
			}
			
			// 이동할 수 있는 위치 추가
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				int n_idx = nr * N + nc;
				
				if (isIn(nr, nc) && !visited[n_idx]) {
					set.add(n_idx);
				}
			}
			
			// 다음에 이동할 수 있는 위치 확인
			for (int n_idx : set) {
				int nr = n_idx / N;
				int nc = n_idx % N;
				
				if (!visited[n_idx] && isLight[n_idx]) {
					visited[n_idx] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;   
	}
	
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
	}
}
