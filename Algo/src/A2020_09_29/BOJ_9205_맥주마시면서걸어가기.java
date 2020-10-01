package A2020_09_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {
	static Pair[] points;
	static boolean[] visited;
	static boolean flag;
	static int N;
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			points = new Pair[N+2];
			visited = new boolean[N+2];
			flag = false;
			
			for (int i = 0; i < points.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				points[i] = new Pair(x, y);
			}
			bfs();
			if(flag) sb.append("happy").append('\n');
			else sb.append("sad").append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		visited[0] = true;
		
		
		while(!q.isEmpty()) {
			int index = q.poll();
			
			if(index == N+1) {
				flag = true;
				break;
			}
			
			for (int i = 0; i < points.length; i++) {
				if(visited[i]) continue;
				int dist = Math.abs(points[i].x-points[index].x)+Math.abs(points[i].y-points[index].y);
				if(dist>1000) continue;
				q.offer(i);
				visited[i] = true;
			}
		}
	}
	
}
