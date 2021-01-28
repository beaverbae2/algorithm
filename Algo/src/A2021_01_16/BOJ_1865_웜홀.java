package A2021_01_16;

import java.util.*;
import java.io.*;

/**
 * BellmanFord
 * @author beaverbae
 * @see https://m.blog.naver.com/PostView.nhn?blogId=miyamae&logNo=220918830855&proxyReferer=https:%2F%2Fwww.google.com%2F
 * @see https://ratsgo.github.io/data%20structure&algorithm/2017/11/27/bellmanford/
 *
 */

public class BOJ_1865_웜홀 {
	static List<Node> graph;
	static int N, M, W;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph.add(new Node(start, end, cost));
				graph.add(new Node(end, start, cost));
			}
			
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph.add(new Node(start, end, (-1) * cost));
			}
			
			if (bellmanford()) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean bellmanford() {
		boolean update;
		int[] dist = new int[N+1];
		final int INF = 987654321;
		
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		// 시간 복잡도 O(VE)
		// V : 지점의 수
		// E : 간선의 수
		// N번 체크(지점의 수)
		for (int cnt = 0; cnt < N; cnt++) {//모든 지점에 대해 relaxation 적용
			update = false;
			for (Node node : graph) {//다익스트라와 비슷(간선)
				if (dist[node.end] > dist[node.start] + node.cost) { 
					dist[node.end] = dist[node.start] + node.cost;
					update = true;
				}
			}
			
			if (!update) return false;
		}
		
		return true;//N번째에도 갱신 된다면 음수 사이클이 존재
	}

	static class Node {
		int start, end, cost;

		public Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", cost=" + cost + "]";
		}
	}
}
