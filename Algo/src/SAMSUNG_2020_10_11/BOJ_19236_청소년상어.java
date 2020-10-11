package SAMSUNG_2020_10_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어 {
	static int[][] dirs = {{0,0},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static boolean[] isDeadFishes;
	static Pair[][] map;
	static Fish[] fishes;
	static int answer;
	
	static class Pair{
		int id, dir;

		public Pair(int id, int dir) {
			this.id = id;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Pair [id=" + id + ", dir=" + dir + "]";
		}
	}
	
	static class Fish{
		int r, c;

		public Fish(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new Pair[4][4];
		fishes = new Fish[17];
		isDeadFishes = new boolean[17];
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = new Pair(id, dir);
				fishes[id] = new Fish(i, j);
			}
		}
//		System.out.println(Arrays.toString(fishes));
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		//상어가 0,0으로 이동
		fishes[0] = new Fish(0, 0);
		int id = map[0][0].id;
		int dir = map[0][0].dir;
		map[0][0] = new Pair(0, dir);
		isDeadFishes[id] = true;
		
//		System.out.println(Arrays.toString(fishes));
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		dfs(map,fishes,id,0);
		System.out.println(answer);
	}
	
	private static void dfs(Pair[][] map, Fish[] fishes, int total, int cnt) {
		answer = Math.max(total, answer);
		
		moveFish(map,fishes);
		int r = fishes[0].r;
		int c = fishes[0].c;
		int dir = map[r][c].dir;
		int id = map[r][c].id;
//		System.out.println(cnt+", present");
//		for (int k = 0; k < 4; k++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(map[k][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		for (int i = 1; i < 4; i++) {
			int nr = r+dirs[dir][0]*i;
			int nc = c+dirs[dir][1]*i;
			int fish_id = 0;
			int fish_dir = 0;
			
			if(isInMap(nr, nc)&&map[nr][nc] != null) {
				//물고기 잡아 먹음
				fish_id = map[nr][nc].id;
				fish_dir = map[nr][nc].dir;
				int next_total = total+map[nr][nc].id;
				
				Pair[][] next_map = new Pair[4][4];
				for (int j = 0; j < next_map.length; j++) {
					for (int k = 0; k < next_map.length; k++) {
						if(map[j][k] != null) {
							next_map[j][k] = new Pair(map[j][k].id, map[j][k].dir);
						}
					}
				}
				Fish[] next_fishes = new Fish[17];
				for (int j = 0; j < next_fishes.length; j++) {
					next_fishes[j] = new Fish(fishes[j].r, fishes[j].c);
				}
				
				next_fishes[0] = new Fish(nr, nc);
				next_map[r][c] = null;
				next_map[nr][nc] = new Pair(id, fish_dir);
				isDeadFishes[fish_id] = true;
//				System.out.println(cnt+", next");
//				for (int k = 0; k < 4; k++) {
//					for (int j = 0; j < 4; j++) {
//						System.out.print(next_map[k][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//				System.out.println("dead: "+fish_id);
				dfs(next_map,next_fishes,next_total,cnt+1);
				isDeadFishes[fish_id] = false;
//				map[nr][nc] = new Pair(fish_id,fish_dir);
//				map[r][c] = new Pair(id, dir);
//				fishes[0] = new Fish(r, c);				
			}
			
		}
	}

	private static void moveFish(Pair[][] map, Fish[] fishes) {
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println(Arrays.toString(isDeadFishes));
//		System.out.println();
		
		for (int id = 1; id < fishes.length; id++) {
			if(isDeadFishes[id]) continue;
			int r = fishes[id].r;
			int c = fishes[id].c;
			int dir = map[r][c].dir;
			int init_dir = dir;
			
			int count = 0;
			while(true) {
				//조건 파악(이동 불가, 상어 있는지)
				int nr = r+dirs[dir][0];
				int nc = c+dirs[dir][1];
				count++;
				if (count==8) break;  
				
				if(isInMap(nr, nc)) {
					if(map[nr][nc]==null) {
						map[r][c].dir = dir;
						Pair temp = map[r][c];
						map[r][c] = null;
						map[nr][nc] = temp;
						fishes[id] = new Fish(nr, nc);
						break;
					}else if(map[nr][nc].id!=0) {
						map[r][c].dir = dir;
						int new_id = map[nr][nc].id;
						Pair temp = map[r][c];
						map[r][c] = map[nr][nc];
						map[nr][nc] = temp;
						fishes[id] = new Fish(nr, nc);
						fishes[new_id] = new Fish(r, c);
						break;
					}
				}
				
				dir++;
				if(dir==dirs.length) {
					dir = 1;
				}
			}
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<4 && nc>=0 && nc<4;
	}
}
