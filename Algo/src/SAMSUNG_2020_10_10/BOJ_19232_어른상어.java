package SAMSUNG_2020_10_10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_19232_어른상어 {
	static int[][] map;
	static Pair[][] smell_map;
	static ArrayList<Integer>[][] next_map;
	static Shark[] shark_arr;
	static boolean[] dead_shark;
	static int N,M,K,answer,total,remain_shark_num;
	static int[][] dirs = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	static int[][][] dir_priority;
	
	
	static class Pair{
		int id, time;

		public Pair(int id, int time) {
			this.id = id;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Pair [id=" + id + ", time=" + time + "]";
		}
	}
	
	static class Shark{
		int r,c,dir;

		public Shark(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		smell_map = new Pair[N][N];
		next_map = new ArrayList[N][N];
		shark_arr = new Shark[M+1];
		shark_arr[0] = new Shark(-1, -1, -1);
		dead_shark = new boolean[M+1];
		dead_shark[0] = true;
		dir_priority = new int[M+1][4][4];
		remain_shark_num = M;
		
		for (int i = 0; i < next_map.length; i++) {
			for (int j = 0; j < next_map.length; j++) {
				next_map[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0) {
					shark_arr[map[i][j]] = new Shark(i, j, -1);
				}
			}
		}
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			shark_arr[i].dir = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(shark_arr));
		
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					dir_priority[i][j][k] = Integer.parseInt(st.nextToken()); 
				}
			}
		}
//		for (int i = 1; i < dir_priority.length; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.println(Arrays.toString(dir_priority[i][j]));
//			}
//		}
//		System.out.println();
		int count = 0;
		while(true) {
			//종료조건
			//if(count>5) break;
			if(answer>1000) break;
			if(remain_shark_num==1) break;
			
			shot_smell();//현재위치에 냄새 뿌림
			move_sharks();//이동
			arrange_sharks();//이동후 상어 정리
			arrange_smell();//냄새 정리
			
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			answer++;
			//break;
		}
		
		
		if(answer>1000) answer=-1;
		System.out.println(answer);
	}
	
	//냄새를 1씩 뺴줌, 0인경우 null로
	private static void arrange_smell() {
		for (int i = 0; i < smell_map.length; i++) {
			for (int j = 0; j < smell_map.length; j++) {
				if(smell_map[i][j]==null) continue;
				
				smell_map[i][j].time -=1;
				if(smell_map[i][j].time==0) smell_map[i][j] = null; 
			}
		}
		
//		for (int i = 0; i < smell_map.length; i++) {
//			for (int j = 0; j < smell_map.length; j++) {
//				System.out.print(smell_map[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
	
	//이동한 상어들 정리
	private static void arrange_sharks() {
		//한 위치에 여러 상어가 있는 경우
		for (int i = 0; i < next_map.length; i++) {
			for (int j = 0; j < next_map.length; j++) {
				if(next_map[i][j].size()>=2) {
					Collections.sort(next_map[i][j]);
					for (int k = 1; k < next_map[i][j].size(); k++) {
						dead_shark[next_map[i][j].get(k)] = true;
						remain_shark_num--;
					}
				}
			}
		}
		
		//map정리
		map = new int[N][N];//0으로 초기화
		for (int i = 0; i < next_map.length; i++) {
			for (int j = 0; j < next_map.length; j++) {
				if(next_map[i][j].size()>=1) {
					map[i][j] = next_map[i][j].get(0);
				}
			}
		}
		next_map = new ArrayList[N][N];
		
		for (int i = 0; i < next_map.length; i++) {
			for (int j = 0; j < next_map.length; j++) {
				next_map[i][j] = new ArrayList<>();
			}
		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}
	
	//상어의 이동
	private static void move_sharks() {
		for (int i = 1; i < shark_arr.length; i++) {
			if(dead_shark[i]) continue;
			Shark shark = shark_arr[i];
			int r = shark.r;
			int c = shark.c;
			int dir = shark.dir;
			boolean flag = false;
			
			//냄새가 없는 곳이 있는가?
			for (int d : dir_priority[i][dir-1]) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if(isInMap(nr, nc)&&smell_map[nr][nc]==null) {
					shark_arr[i] = new Shark(nr, nc, d);
					next_map[nr][nc].add(i);
					flag = true;
					break;
				}
			}
			
			//없다면 같은 냄새를 가지고 있는 곳이 있는가?
			if(!flag) {
				for (int d : dir_priority[i][dir-1]) {
					int nr = r+dirs[d][0];
					int nc = c+dirs[d][1];
					
					if(isInMap(nr, nc)&&smell_map[nr][nc].id==i) {
						shark_arr[i] = new Shark(nr, nc, d);
						next_map[nr][nc].add(i);
						flag = true;
						break;
					}
				}
			}
		}
//		for (int i = 1; i < shark_arr.length; i++) {
//			System.out.println(shark_arr[i]);
//		}
//		System.out.println();
//	
//		for (int i = 0; i < next_map.length; i++) {
//			for (int j = 0; j < next_map.length; j++) {
//				System.out.print(next_map[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
	
	//현재 위치에 상어들이 냄새를 뿌린다.
	private static void shot_smell() {
		for (int i = 1; i < shark_arr.length; i++) {
			if(dead_shark[i]) continue;
			Shark shark = shark_arr[i];
			int r = shark.r;
			int c = shark.c;
			smell_map[r][c] = new Pair(i, K);
		}
		
//		for (int i = 0; i < smell_map.length; i++) {
//			System.out.println(Arrays.toString(smell_map[i]));
//		}
	}
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
