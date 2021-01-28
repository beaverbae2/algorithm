package A2020_12_10;

import java.util.*;
import java.io.*;

public class BOJ_20058_마법사_상어와_파이어스톰 {
	static int N, Q, max;
	static int[][] map;
	static int[] storm;
	static int[] pow = {1, 2<<0, 2<<1, 2<<2, 2<<3, 2<<4, 2<<5};
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[pow[N]][pow[N]];
		storm = new int[Q];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < storm.length; i++) {
			storm[i] = Integer.parseInt(st.nextToken());
		}
	
		fireStorm();
		visited = new boolean[pow[N]][pow[N]];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited.length; j++) {
				if(map[i][j]==0) visited[i][j] = true;
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(!visited[i][j]) {
					getMaxIce(i,j);
				}
			}
		}
		
		System.out.println(getRemainIce());
		System.out.println(max);
	}

	private static void getMaxIce(int sr, int sc) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sr,sc});
		visited[sr][sc] = true;
		
		int temp_max = 1;
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
		
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if(isInMap(nr, nc)&&!visited[nr][nc]) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					temp_max++;
				}
			}
		}
		
		max = Math.max(max, temp_max);
		
	}

	private static int getRemainIce() {
		int remain = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				remain += map[i][j];
			}
		}
		
		return remain;
	}

	private static void fireStorm() {
		for(int L : storm) {
			int[][] next_map = new int[pow[N]][pow[N]];
			
			///이동
			moveIce(next_map,L);
			meltIce(next_map);
			map = next_map;
		}
	}

	private static void moveIce(int[][] next_map, int L) {
		
		for (int i = 0; i < pow[N]; i+=pow[L]) {
			for (int j = 0; j < pow[N]; j+=pow[L]) {
				int r1 = i;
				int r2 = i+pow[L];
				int c1 = j;
				int c2 = j+pow[L];
	
				int sub_c = 0;
				for (int r = r1; r < r2; r++) {
					int sub_r = 1;
					for (int c = c1; c < c2; c++) {
						next_map[r][c] = map[r2-sub_r][c1+sub_c];
						sub_r++;
					}
					sub_c++;
				}
			}
		}
	}
	
	private static void meltIce(int[][] next_map) {
		boolean[][] melt_map = new boolean[pow[N]][pow[N]];
		
		for (int r = 0; r < next_map.length; r++) {
			for (int c = 0; c < next_map.length; c++) {
				if(next_map[r][c] == 0) continue;
				
				int cnt = 0;
				for (int d = 0; d < dirs.length; d++) {
					int nr = r+dirs[d][0];
					int nc = c+dirs[d][1];
					
					if(isInMap(nr, nc)&&next_map[nr][nc]!=0) cnt++;
				
				}
				if(cnt<3) melt_map[r][c] = true; 
				
			}
		}
		
		for (int i = 0; i < melt_map.length; i++) {
			for (int j = 0; j < melt_map.length; j++) {
				if(melt_map[i][j]) {
					next_map[i][j] -= 1;
				}
			}
		}
		
	}
	
	private static boolean isInMap(int nr, int nc) {
		if(nr>=0 && nr<pow[N]&&nc>=0&&nc<pow[N]) return true;
		return false;
	}
	
}
