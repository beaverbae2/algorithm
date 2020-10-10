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

public class BOJ_19238_스타트택시 {
	static int N, M, fuel, answer;
	static int[][] map;
	static int[] taxi;
	static int[][] customer_start;
	static int[][] customer_end;
	static boolean[][] visited;
	static int[][] d2;// 승객 출발지 -> 목적지 거리
	static int min_cutcumer_num;
	static int min_d1, min_r, min_c;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		taxi = new int[2];
		customer_start = new int[M][2];
		customer_end = new int[M][2];
		visited = new boolean[N][N];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], true);
		}
		d2 = new int[N][N];

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		taxi[0] = Integer.parseInt(st.nextToken()) - 1;
		taxi[1] = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			customer_start[i][0] = Integer.parseInt(st.nextToken()) - 1;
			customer_start[i][1] = Integer.parseInt(st.nextToken()) - 1;
			customer_end[i][0] = Integer.parseInt(st.nextToken()) - 1;
			customer_end[i][1] = Integer.parseInt(st.nextToken()) - 1;
			// 손님의 출발지와 목적지 사이의 거리 구함
			visited[customer_start[i][0]][customer_start[i][1]] = false;
			bfs2(customer_start[i][0], customer_start[i][1], customer_end[i][0], customer_end[i][1], i);
		}

		while (true) {
			min_cutcumer_num = -1;
			min_d1 = Integer.MAX_VALUE;

			if (M == 0) {
				answer = fuel;
				break;
			}

			int[] pair = bfs1();// 손님들의 출발지와 택시 사이의 거리 구함
			int r = pair[0];
			int c = pair[1];
			
			if(r==-1 && c==-1) {
				answer = -1;
				break;
			}
			
			int need_fuel = min_d1 + d2[r][c];
			if (need_fuel > fuel) {
				answer = -1;
				break;
			} else {
				visited[r][c] = true;
				fuel -= need_fuel;// 소모
				fuel += 2 * d2[r][c];// 충전
				M--;
				taxi[0] = customer_end[min_cutcumer_num][0];
				taxi[1] = customer_end[min_cutcumer_num][1];
			}

		}

		System.out.println(answer);
	}

	private static int[] bfs1() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		q.offer(new int[] {taxi[0],taxi[1],0});
		ArrayList<int[]> pairs = new ArrayList<>();
		
		int dd = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int d = elem[2];
			
			if(d>dd) break;
			
			if(!visited[r][c] && dd==d) {
				pairs.add(new int[] {r,c});
			}
			
			if(!visited[r][c] && dd == Integer.MAX_VALUE) {
				pairs.add(new int[] {r,c});
				dd = d;
				min_d1 = d;
			}
			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];

				if (isInMap(nr, nc) && !visit[nr][nc] && map[nr][nc] == 0) {
					q.offer(new int[] { nr, nc, d + 1 });
					visit[nr][nc] = true; 
				}
			}
		}
		if(pairs.size()==0) return new int[] {-1,-1};
		else if(pairs.size()==1) return pairs.get(0);
		else {
			Collections.sort(pairs, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					int r1 = o1[0];
					int r2 = o2[0];
					if(r1!=r2) return Integer.compare(r1, r2);
					else {
						int c1 = o1[1];
						int c2 = o2[2];
						return Integer.compare(c1, c2);
					}
				}
			});
			return pairs.get(0);
		}
	}

	private static void bfs2(int sr, int sc, int er, int ec, int index) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		q.offer(new int[] { sr, sc, 0 });
		visit[sr][sc] = true;

		while (!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int d = elem[2];

			if (r == er && c == ec) {
				d2[sr][sc] = d;
				break;
			}

			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];

				if (isInMap(nr, nc) && !visit[nr][nc] && map[nr][nc] == 0) {
					q.offer(new int[] { nr, nc, d + 1 });
					visit[nr][nc] = true;
				}
			}
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
