package A2020_12_14;

import java.util.*;
import java.io.*;

public class BOJ_19238_스타트택시 {
	static int N, M, fuel;
	static int[][] map;
	static int taxi_r, taxi_c;
	static int[][] dirs = {{-1,0}, {0,-1}, {1,0}, {0,1}};
	static Passenger[][] passenger;
	static boolean flag;
	static List<Pair> list;
	
	static class Pair{
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Elem {
		int r,c,dist;

		public Elem(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", dist=" + dist + "]";
		}
	}
	
	static class Passenger{
		int end_r, end_c;

		public Passenger(int end_r, int end_c) {
			this.end_r = end_r;
			this.end_c = end_c;
		}

		@Override
		public String toString() {
			return "Passenger [end_r=" + end_r + ", end_c=" + end_c + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
	
		map = new int[N][N];
		passenger = new Passenger[N][N];
		flag = true;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi_r = Integer.parseInt(st.nextToken())-1;
		taxi_c = Integer.parseInt(st.nextToken())-1;
	
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken())-1;
			int sc = Integer.parseInt(st.nextToken())-1;
			int er = Integer.parseInt(st.nextToken())-1;
			int ec = Integer.parseInt(st.nextToken())-1;
			passenger[sr][sc] = new Passenger(er, ec);
		}
		
		for (int i = 0; i < M; i++) {
			if(flag) {
				findPassenger();
			}
			else {
				break;
			}
		}
		if(flag) System.out.println(fuel);
		else System.out.println(-1);
	}

	private static void findPassenger() {
		Queue<Elem> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Elem(taxi_r, taxi_c, 0));
		visited[taxi_r][taxi_c] = true;
		
		int min_dist = Integer.MAX_VALUE;
		list = new ArrayList<>();
		
		while(!q.isEmpty()) {
			Elem e = q.poll();
			int r = e.r;
			int c = e.c;
			int dist = e.dist;
			
			if (dist>min_dist) break;
			
			if (passenger[r][c]!=null) {
				if(min_dist>dist){
					min_dist = dist;
					fuel -= dist;
					list.add(new Pair(r, c));
				}else if (min_dist==dist) {
					list.add(new Pair(r,c));
				}
			}
			
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
			
				if (nr<0||nr>=N||nc<0||nc>=N) continue;
				
				if(!visited[nr][nc]&&map[nr][nc]==0) {
					q.offer(new Elem(nr, nc, dist+1));
					visited[nr][nc] = true;
				}
			}
		}

		if (fuel>0 && min_dist!=Integer.MAX_VALUE) {
			getShortestPassenger();
		}else {
			flag = false;
		}
	}

	private static void getShortestPassenger() {
		Collections.sort(list, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if(o1.r != o2.r) {
					return Integer.compare(o1.r, o2.r);
				}else {
					return Integer.compare(o1.c, o2.c);
				}
			}
		});
		
		Pair p = list.get(0);
		taxi_r = p.r;
		taxi_c = p.c;
		int er = passenger[p.r][p.c].end_r;
		int ec = passenger[p.r][p.c].end_c;
		passenger[p.r][p.c] = null;

		movePassenger(er, ec);
	}

	private static void movePassenger(int er, int ec) {
		Queue<Elem> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Elem(taxi_r, taxi_c, 0));
		visited[taxi_r][taxi_c] = true;
		
		int result = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Elem e = q.poll();
			int r = e.r;
			int c = e.c;
			int dist = e.dist;
			
			if (r==er&&c==ec) {
				result = dist;
				fuel -= dist;
				taxi_r = r;
				taxi_c = c;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if (nr<0||nr>=N||nc<0||nc>=N) continue;
				
				if(!visited[nr][nc]&&map[nr][nc]==0) {
					q.offer(new Elem(nr, nc, dist+1));
					visited[nr][nc] = true;
				}
			}
		}
		
		
		if(fuel>=0 && result!=Integer.MAX_VALUE) {
			fuel += (2*result);

		}else {
			flag = false;
		}
	}
}
