package A2022_03_17;

import java.util.*;
import java.io.*;

/**
 * Tree
 * 40MIN
 * @author beaverbae
 * - 자식의 개수 구할때 root는 항상 유념해야함 
 * - 가지 최대 길이 구할때 기둥 루트는 배제 -> 방문배열 그대로
 */


public class BOJ_20924_트리의기둥과가지 {
	static int N, root;
	static List<Pair>[] tree;
	static boolean[] visited;
	static int len1, len2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		root = Integer.parseInt(st.nextToken());
		tree = new List[N+1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			tree[a].add(new Pair(b, w));
			tree[b].add(new Pair(a, w));
		}
	
		visited = new boolean[N+1];
		int giga = findGiga(root);
		bfs(giga);
		System.out.println(len1+" "+len2);
	}

	private static int findGiga(int v) {
		visited[v] = true;
		if (v == root && tree[v].size() > 1) return v;
		if (tree[v].size() > 2) return v;
		
		int result = root;
		for (Pair n : tree[v]) {
			if (visited[n.v]) continue;
			result = findGiga(n.v);
			len1 += n.w;
		}
		return result;
	}
	
	private static void bfs(int s) {
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(new Pair(s, 0));
		visited[s] = true;
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			len2 = Math.max(len2, p.w);
			for (Pair n : tree[p.v]) {
				if (visited[n.v]) continue;
				
				q.offer(new Pair(n.v, p.w + n.w));
				visited[n.v] = true;
			}
		}
	}
	
	static class Pair {
		int v, w;

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
