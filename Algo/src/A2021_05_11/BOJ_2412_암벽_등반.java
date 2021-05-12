package A2021_05_11;

import java.util.*;
import java.io.*;

/**
 * BFS, Binary Search
 * 어려웠다. 다시 풀어봐야지
 * @author beaverbae
 *`
 */

public class BOJ_2412_암벽_등반 {
	static List<Pair> list;
	static boolean[] visited;
	static int answer;
	static int N, T;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
		
			list.add(new Pair(r, c));
		}
		
		Collections.sort(list);
		
		System.out.println(bfs(0,0));
	}
	
	private static int bfs(int sr, int sc) {
		Queue<Node> q = new LinkedList<>();
		visited = new boolean[list.size()];
		
		q.offer(new Node(sr, sc, 0));
		
		while (!q.isEmpty()) {
//			System.out.println(q);
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			for (int nr = r-2; nr <= r+2; nr++) {
				for (int nc = c-2; nc <= c+2; nc++) {
					if (!isIn(nr, nc)) continue;
					
					int idx = binary_search(nr, nc);
					
					if (idx== -1 || visited[idx]) continue;
					
					if (list.get(idx).c == T) return cur.d+1;
					visited[idx] = true;
					q.offer(new Node(nr, nc, cur.d+1));
				
				}
			}
			
			
		}
		
		return -1;
	}
	
	private static int binary_search(int nr, int nc) {
		int result = -1;
		int left = 0;
		int right = N-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			Pair p = list.get(mid);
			
			if (p.r < nr) {
				left = mid + 1;
			} else if (p.r > nr) {
				right = mid - 1;
			} else {
				if (p.c < nc) {
					left = mid + 1;
				} else if (p.c > nc) {
					right = mid - 1;
				} else {
					result = mid;
					break;
				}
			}
		}
		
		return result;
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0;
	}
	
	static class Node {
		int r, c, d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
	}
	
	static class Pair implements Comparable<Pair> {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}

		@Override
		public int compareTo(Pair o) {
			if (this.r != o.r) return this.r-o.r;
			
			return this.c-o.c;
		}
	}
}
