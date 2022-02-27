package A2022_02_27;

import java.util.*;
import java.io.*;

/**
 * BFS
 * 44MIN
 * @author beaverbae
 * - 방문조건 : 같은 시각(초)에는 재방문 가능
 */

public class BOJ_12851_숨바꼭질2 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
	
		bfs(s, e);
		System.out.println(sb);
	}
	
	private static void bfs(int s, int e) {
		Queue<Node> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		int cnt = 0;
		int w = 0;
		int dist = Integer.MAX_VALUE;
		
		q.offer(new Node(s, 0));
		visited.add(s);
		map.put(s, 0);
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int v = node.v;
			w = node.w;
			
			if (w > dist) {
				w--;
				break;
			}
			
			if (v == e) {
				dist = w;
				cnt++;
			}
			
			if (v-1 >= 0) {
				if (!map.containsKey(v-1)) {
					q.offer(new Node(v-1, w+1));
					map.put(v-1, w+1);
				} else {
					int nw = map.get(v-1);
					if (nw == w+1) {
						q.offer(new Node(v-1, w+1));
						map.put(v-1, w+1);
					}
				}
			}
			
			if (v+1 <= 200000) {
				if (!map.containsKey(v+1)) {
					q.offer(new Node(v+1, w+1));
					map.put(v+1, w+1);
				} else {
					int nw = map.get(v+1);
					if (nw == w+1) {
						q.offer(new Node(v+1, w+1));
						map.put(v+1, w+1);
					}
				}
			}
			
			if (v*2 <= 200000) {
				if (!map.containsKey(v*2)) {
					q.offer(new Node(v*2, w+1));
					map.put(v*2, w+1);
				} else {
					int nw = map.get(v*2);
					if (nw == w+1) {
						q.offer(new Node(v*2, w+1));
						map.put(v*2, w+1);
					}
				}
			}
		}
		
		sb.append(w).append("\n").append(cnt).append("\n");
	}
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", w=" + w + "]";
		}
	}
}
