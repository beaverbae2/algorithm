package SAMSUNG_2020_10_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어 {
	//static boolean[] isDead;//1~16, 죽은 물고기 표시
	static int answer, shark_r, shark_c, shark_dir;
	static int[][] dirs = {{0,0},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static String src = "7 6 2 3 15 6 9 8\r\n" + 
			"3 1 1 8 14 7 10 1\r\n" + 
			"6 1 13 6 4 3 11 4\r\n" + 
			"16 1 8 7 5 2 12 2";
	
	static class Pair{
		int r,c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Fish{
		int id, dir;

		public Fish(int id, int dir) {
			this.id = id;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Fish [id=" + id + ", dir=" + dir + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br= new BufferedReader(new StringReader(src));
		Fish[][] map = new Fish[4][4];
		Pair[] fishes = new Pair[17];
		//isDead = new boolean[17];
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = new Fish(id, dir);
				fishes[id] = new Pair(i, j);
			}
		}
//		System.out.println(Arrays.toString(fishes));
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		//초기화
		answer += map[0][0].id;
		shark_r = 0;
		shark_c = 0;
		shark_dir = map[0][0].dir;
		//isDead[map[0][0].id] = true;			
		fishes[map[0][0].id] = null;
		map[0][0] = null;
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
		dfs(map,fishes,answer);
		
		System.out.println(answer);
	}

	private static void dfs(Fish[][] map, Pair[] fishes,int point) {
		//물고기의 이동
		moveFishes(map,fishes);
		
		int init_shark_r = shark_r;
		int init_shark_c = shark_c;
		int init_shark_dir = shark_dir;
		
		//상어의 이동
		for (int i = 1; i <= 3; i++) {
			int shark_nr = shark_r+i*dirs[shark_dir][0];
			int shark_nc = shark_c+i*dirs[shark_dir][1];
			
			if(!isInMap(shark_nr, shark_nc)||map[shark_nr][shark_nc]==null) continue;
		
			Fish[][] next_map = new Fish[4][4];
			Pair[] next_fishes = new Pair[17];
			for (int j = 0; j < next_map.length; j++) {
				for (int k = 0; k < next_map.length; k++) {
					if(map[j][k] != null) next_map[j][k] = new Fish(map[j][k].id, map[j][k].dir);
				}
			}
			for (int j = 1; j < next_fishes.length; j++) {
				if(fishes[j]!=null) next_fishes[j] = new Pair(fishes[j].r, fishes[j].c);
			}
			
			int id = next_map[shark_nr][shark_nc].id;
			int dir = next_map[shark_nr][shark_nc].dir;
			int next_point = point+id;
			next_fishes[id] = null;
			next_map[shark_nr][shark_nc] = null;
			shark_dir = dir;
			shark_r = shark_nr;
			shark_c = shark_nc;
			answer = Math.max(answer, next_point);
			dfs(next_map, next_fishes, next_point);
			shark_dir = init_shark_dir;
			shark_r = init_shark_r;
			shark_c = init_shark_c;
		}
	}

	private static void moveFishes(Fish[][] map, Pair[] fishes) {
		//번호 순으로이동
		for (int i = 1; i < fishes.length; i++) {
			if(fishes[i]==null) continue;//죽은 물고기는 패스
			
			int r = fishes[i].r;
			int c = fishes[i].c;
			int dir = map[r][c].dir;
			//System.out.println("id : "+i+" r: "+r+" c: "+c);
			int turn_count = 0;
			//1,2,3,4,5,6,7,8;
			while(true) {
				if(turn_count==7) break;
				
				int nr = r+dirs[dir][0];
				int nc = c+dirs[dir][1];
				
				if(!isInMap(nr, nc)||(shark_r==nr&&shark_c==nc)) {
					dir++;
					turn_count++;
					if(dir==9) dir=1;
				}else {
					map[r][c].dir = dir;
					if(map[nr][nc]==null) {
						fishes[i] = new Pair(nr, nc);
						Fish temp  = map[r][c];
						map[r][c] = null;
						map[nr][nc] = temp;
					}else {
						Fish temp  = map[r][c];
						int i2 = map[nr][nc].id;
						map[r][c] = map[nr][nc];
						map[nr][nc] = temp;
						fishes[i] = new Pair(nr, nc);
						fishes[i2] = new Pair(r, c);
						
					}
					break;
				}
			}
//			for (int k = 0; k < map.length; k++) {
//				System.out.println(Arrays.toString(map[k]));
//			}
//			System.out.println();
		}
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<4 && nc>=0 && nc<4;
	}
}
