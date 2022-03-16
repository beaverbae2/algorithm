package A2022_03_16;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 37MIN
 * @author beaverbae
 * - DFS해서 시간 터짐 -> BFS 통과 (메모리 여유 확인)
 * - 무방향 그래프, root 항상 생각
 */

public class BOJ_17073_나무위의빗물 {
	static int N, W, n, root;
	static double water;
	
	static List<Integer>[] tree;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		tree = new List[N+1];
		visited = new boolean[N+1];
		for (int v = 1; v <= N; v++) {
			tree[v] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			tree[a].add(b);
			tree[b].add(a);
		}
		
		root = 1;
		bfs();
		System.out.println(water / n);
	}
	
	private static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(new Pair(1, W));
		visited[1] = true;
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int v = p.v;
			double w = p.w;
			
			if (v != root && tree[v].size() == 1) {
				n++;
				water += w;
				continue;
			}
			
			int size = tree[v].size();
			if (v != root) size--;
			double nw = w / size;
			
			for (int nv : tree[v]) {
				if (visited[nv]) continue;
				
				q.offer(new Pair(nv, nw));
				visited[nv] = true;
			}
		}
	}
	
	static class Pair {
		int v;
		double w;
		
		public Pair(int v, double w) {
			this.v = v;
			this.w = w;
		}
		
	}
}
