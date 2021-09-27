package A2021_09_06;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 27MIN
 * @author beaverbae
 * 문제 스타일이 생소하여 생각해야되는 부분이 많다
 */

public class BOJ_2412_암벽_등반 {
	static int N, T;
	static HashMap<Integer, HashSet<Integer>> map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
		
			if (!map.containsKey(x)) {
				map.put(x, new HashSet<>());
			}
			
			HashSet<Integer> set = map.get(x);
			set.add(y);
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, 0));
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int w = cur.w;
			
			if (y == T) return w;
			
			for (int nx = x - 2; nx <= x + 2; nx++) {
				if (!map.containsKey(nx)) continue;
				
				HashSet<Integer> set = map.get(nx);
				for (int ny = y - 2; ny <= y + 2; ny++) {
					if (!set.contains(ny)) continue;
					
					q.offer(new Node(nx, ny, w+1));
					set.remove(ny);
				}
				
				if (set.isEmpty()) map.remove(nx);
			}
		}
		
		return -1;
	}
	
	static class Node {
		int x, y, w;

		public Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", w=" + w + "]";
		}
	}
}
