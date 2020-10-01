package A2020_09_29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기Fail2 {
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
			dfs(0);
			if(flag) sb.append("happy").append('\n');
			else sb.append("sad").append('\n');
		}
		System.out.println(sb);
	}

	private static void dfs(int point) {
		visited[point] = true;
		if(point==N+1) {
			flag = true;
			return;
		}
		
		for (int i = 0; i < points.length; i++) {
			if(visited[i]) continue;
			
			int x1 = points[point].x;
			int y1 = points[point].y;
			int x2 = points[i].x;
			int y2 = points[i].y;
			
			int distance = Math.abs(x1-x2)+Math.abs(y1-y2);
			
			if(distance>1000) continue;
			dfs(i);
		}
		visited[point] = false;
	}
	
}
