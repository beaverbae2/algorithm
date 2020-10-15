package SAMSUMG_2020_10_14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	static ArrayList<Shark1>[][] shark_map;
	static Shark2[] sharks;
	static int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
	static int R,C,M;
	
	
	static class Shark1{
		int id, scale;

		public Shark1(int id, int scale) {
			this.id = id;
			this.scale = scale;
		}

		@Override
		public String toString() {
			return "Shark1 [id=" + id + ", scale=" + scale + "]";
		}
		
	}
	
	static class Shark2{
		int r,c,v,dir,scale;

		public Shark2(int r, int c, int v, int dir, int scale) {
			this.r = r;
			this.c = c;
			this.v = v;
			this.dir = dir;
			this.scale = scale;
		}

		@Override
		public String toString() {
			return "Shark2 [r=" + r + ", c=" + c + ", v=" + v + ", dir=" + dir + ", scale=" + scale + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		shark_map = new ArrayList[R+1][C+1];
		sharks = new Shark2[M+1];
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				shark_map[i][j] = new ArrayList<>();
			}
		}
		
		
		for (int id = 1; id <= M; id++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int scale = Integer.parseInt(st.nextToken());
		
			sharks[id] = new Shark2(r, c, v, dir, scale);
			shark_map[r][c].add(new Shark1(id, scale));
		}
		
//		System.out.println(Arrays.toString(sharks));
//		System.out.println();
//		for (int i = 1; i <= R; i++) {
//			for (int j = 1; j <= C; j++) {
//				System.out.print(shark_map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		int answer = 0;
		for (int fisher = 1; fisher <= C; fisher++) {
			
			//낚시왕의 포획
			for (int r = 1; r <= R; r++) {
				if(shark_map[r][fisher].size()==0) continue;
				
				int id = shark_map[r][fisher].get(0).id;
				shark_map[r][fisher] = new ArrayList<>();
				sharks[id] = null;
				break;
			}
			
			//상어의 이동
			for (int id = 1; id < sharks.length; id++) {
				if(sharks[id] == null) continue;
				
				int r = sharks[id].r;
				int c = sharks[id].c;
				int v = sharks[id].v;
				int dir = sharks[id].dir;
				int scale = sharks[id].scale;
				
				moveShark(id,r,c,v,dir);
			}
			checkDup();
			
		}
		System.out.println(answer);
	}

	private static void moveShark(int id,int r, int c, int v, int dir) {
		if(dir==0) {//위
			int nr = 0;
			int nc = 0;
			if(v<=r-1) {
				nr = r+v*dirs[dir][0];
				nc = c;
				
				if(nr == 1) dir = changeDir(dir);
			}else {
				int sub_v = v-r+1;
				int q = R-1;
				int p = sub_v/q;
				int move = sub_v%q;
				
				if(p%2==1) {//방향 그대로
					nr = R-move;
					nc = c;
				}else {
					nr = move;
					nc = c;
				}
				
			}
			for (int i = 0; i < shark_map[r][c].size(); i++) {
				if(shark_map[r][c].get(i).id==id) {
					shark_map[nr][nc].add(new Shark1(id, shark_map[r][c].get(i).scale));
					shark_map[r][c].remove(i);
					break;
				}
			}
			
		}
	}

	private static int changeDir(int dir) {
		if(dir==0) {
			dir = 1;
		}else if(dir==1) {
			dir = 0;
		}else if(dir==2) {
			dir = 3;
		}else if(dir==3) {
			dir = 2;
		}
		
		return dir;
	}

	private static void checkDup() {
		for (int r = 1; r <= R; r++) {
			for(int c = 1; c<=C; c++) {
				if(shark_map[r][c].size()>1) {
					//크기 순으로 정렬
					Collections.sort(shark_map[r][c], new Comparator<Shark1>() {

						@Override
						public int compare(Shark1 s1, Shark1 s2) {
							int scale1 =s1.scale;
							int scale2 =s2.scale;
							return Integer.compare(scale1, scale2);
						}
					});
					
					//크기가 작은 상어 제거
					for (int k = 1; k < shark_map[r][c].size(); k++) {
						int id = shark_map[r][c].get(k).id;
						sharks[id] = null;
					}
					
					//남은 한마리의 상어
					Shark1 remain_shark = new Shark1(shark_map[r][c].get(0).id, shark_map[r][c].get(0).scale);
					shark_map[r][c].clear();
					shark_map[r][c].add(remain_shark);
					
				}
			}
		}
	}
}
