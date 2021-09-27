package A2021_04_02;

import java.util.*;
import java.io.*;

/**
 * BFS
 * @author beaverbae
 *
 */

public class BOJ_21278_호석이_두마리_치킨 {
	static List<Integer>[] graph;
	static int N, M;
	static int[] selected;// 치킨집 으로 뽑인 정점(조합)
	static int[] dist;// 거리
	static final int INF = 987654321;
	static List<Pair> list;// 정답 출력위한 리스트(정렬 필요)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new List[N+1];
		selected = new int[2];
		dist = new int[N+1];
		list = new ArrayList<>();
		
		// 그래프 구성
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방향
			graph[a].add(b);
			graph[b].add(a);
		}
		
		comb(1, 0);// 2개 뽑기(조합)
		
		// 정렬
		Collections.sort(list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.w != o2.w) {
					return o1.w - o2.w;
				} else if (o1.v1 != o2.v1) {
					return o1.v1 - o2.v1;
				}
				return o1.v2 - o2.v2;
			}
		});
		Pair first = list.get(0);
		System.out.println(first.v1+" "+first.v2+" "+first.w);
	}
	
	private static void comb(int start, int idx) {
		if (idx == 2) {
			// dist 초기화
			Arrays.fill(dist, INF);
			for (int i = 0; i < selected.length; i++) {
				bfs(selected[i]);// 현재 정점과 다른 노드의 최단거리 구함
			}
			
			int total_w = 0;
			for (int i = 1; i < dist.length; i++) {
				total_w += (2*dist[i]);// 왕복 처리
			}
			list.add(new Pair(selected[0], selected[1], total_w));
			return;
		}
		
		for (int i = start; i <= N; i++) {
			selected[idx] = i;
			comb(i+1, idx+1);
		}
	}

	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(new Node(start, 0));
		visited[start] = true;
		dist[start] = 0;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			int v = node.v;
			int w = node.w;
			
			for (int next_v : graph[v]) {
				if (visited[next_v]) continue;
				
				dist[next_v] = Math.min(dist[next_v], w+1);
				q.offer(new Node(next_v, w+1));
				visited[next_v] = true;
			}
		}
	}

	static class Pair {
		int v1, v2, w;

		public Pair(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Pair [v1=" + v1 + ", v2=" + v2 + ", w=" + w + "]";
		}
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
