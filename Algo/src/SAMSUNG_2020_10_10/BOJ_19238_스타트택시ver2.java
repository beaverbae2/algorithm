package SAMSUNG_2020_10_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//승객이 출발지에서 목적지로 갈수 없는 경우가 존재한다...

public class BOJ_19238_스타트택시ver2 {
	static int[][] map;
	static int N,M,fuel,taxi_r, taxi_c;
	static int[][] customers;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int[] d2;
	static int[][] visited;
	static int min_r, min_c, min_d, answer;
	static ArrayList<int[]> list;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
	
		map = new int[N][N];
		customers= new int[M][4];
		d2 = new int[M];
		Arrays.fill(d2, Integer.MAX_VALUE);
		
		visited = new int[N][N];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], -1);
		}
		
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
			for (int j = 0; j < 4; j++) {
				customers[i][j] = Integer.parseInt(st.nextToken())-1;
			}
			visited[customers[i][0]][customers[i][1]] = i;
			//손님 출발지-> 목적지 최단 거리
			bfs1(customers[i][0],customers[i][1],customers[i][2],customers[i][3],i);
		}
		//System.out.println(Arrays.toString(d2));
		
		//본격 탐색
		while(true) {
			if(M==0) {//정답
				answer = fuel;
				break;
			}
			
			min_r = min_c = -1;
			min_d = Integer.MAX_VALUE;
			bfs1();//택시 현재 위치 부터 가장 가까운 손님의 출발지 찾기
			
			if(min_d==Integer.MAX_VALUE) {//탐색 불가
				answer = -1;
				break;
			}else {
				int min_customer_num = visited[min_r][min_c];
				int need_fuel = min_d+d2[min_customer_num];
				
				if(need_fuel>fuel) {
					answer = -1;
					break;
				}
				if(d2[min_customer_num]==Integer.MAX_VALUE) {
					answer = -1;
					break;
				}
				visited[min_r][min_c] = -1;
				taxi_r = customers[min_customer_num][2];
				taxi_c = customers[min_customer_num][3];
				fuel -= need_fuel;
				fuel += 2*d2[min_customer_num];
				M--;
			}
		}
		System.out.println(answer);
	}

	private static void bfs1() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		q.offer(new int[] {taxi_r,taxi_c,0});
		visit[taxi_r][taxi_c] = true;
		list = new ArrayList<>();
		
		int threshold_d = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] e = q.poll();
			int r = e[0];
			int c = e[1];
			int d = e[2];
			
			if(d>threshold_d) break;
			
			if(visited[r][c]>=0){
				if(threshold_d>d) {
					threshold_d = d;
					list.add(new int[] {r,c});
				}else if(threshold_d == d){
					list.add(new int[] {r,c});
				}
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				
				if(isInMap(nr, nc)&&!visit[nr][nc]&&map[nr][nc]==0) {
					q.offer(new int[] {nr,nc,d+1});
					visit[nr][nc] = true;
				}
			}
		}
		
		if(list.size()==1) {
			min_r = list.get(0)[0];
			min_c = list.get(0)[1];
			min_d = threshold_d;
			
		}else if (list.size()>1){
			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					int r1 = o1[0];
					int r2 = o2[0];
					if(r1!=r2) return Integer.compare(r1, r2);
					else {
						int c1 = o1[1];
						int c2 = o2[1];
						return Integer.compare(c1, c2);
					}
				}
			});
			min_r = list.get(0)[0];
			min_c = list.get(0)[1];
			min_d = threshold_d;
		
		}
		
	}

	private static void bfs1(int sr, int sc, int er, int ec, int index) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		q.offer(new int[] {sr,sc,0});
		visit[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] e = q.poll();
			int r = e[0];
			int c = e[1];
			int d = e[2];
			
			if(r==er && c==ec) {
				d2[index] = d;
				break;
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				
				if(isInMap(nr, nc)&&!visit[nr][nc]&&map[nr][nc]==0) {
					q.offer(new int[] {nr,nc,d+1});
					visit[nr][nc] = true;
				}
			}
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0&& nr<N&& nc>=0 && nc<N;
	}
}
