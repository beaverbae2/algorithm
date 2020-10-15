package SAMSUMG_2020_10_14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

//어휴...

public class BOJ_17143_낚시왕ver2 {
	static int[][] dirs = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};//상하우좌
	static ArrayList<Shark>[][] map;
	static ArrayList<Shark>[][] next_map;
	static int R,C,M;
	
	static class Shark{
		int v,dir,scale;//속력, 방향, 크기

		public Shark(int v, int dir, int scale) {
			this.v = v;
			this.dir = dir;
			this.scale = scale;
		}

		@Override
		public String toString() {
			return "[v=" + v + ", dir=" + dir + ", scale=" + scale + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new ArrayList[R+1][C+1];//행, 열 1부터 시작
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				map[r][c] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int scale = Integer.parseInt(st.nextToken());
			
			map[r][c].add(new Shark(v, dir, scale));
		}
		
		//입력 확인
//		for (int r = 1; r <= R; r++) {
//			for (int c = 1; c <= C; c++) {
//				System.out.print(map[r][c]+" ");
//			}
//			System.out.println();
//		}
		
		
		int answer = 0;//잡은 상어 크기의 합
		//닊시왕이 이동
		for (int fisher = 1; fisher <= C; fisher++) {
			
			//1. 낚시왕이 상어를 잡는다
			//땅에서 가장 가까운 상어를 잡는다
			for (int r = 1; r <= R; r++) {
				if(map[r][fisher].size()==0) continue;
				
				answer += map[r][fisher].get(0).scale; //잡은 상어의 크기만큼 추가
				map[r][fisher] = new ArrayList<>();//상어 소멸
				break;//한 마리 잡으면 끝
			}
//			System.out.println(answer);
//			for (int r = 1; r <= R; r++) {
//				for (int c = 1; c <= C; c++) {
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			//2. 상어의 이동
			//이동 후 위치를 담는 next_map 선언 및 정의
			next_map = new ArrayList[R+1][C+1];
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					next_map[r][c] = new ArrayList<>();
				}
			}
			
			
			//상어가 존재하는 곳 찾기
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					if(map[r][c].size()==0) continue; //상어가 없는 경우
					
					Shark shark = map[r][c].get(0);
					int v = shark.v;
					int dir = shark.dir;
					int scale = shark.scale;
					
					//각각의 상어가 이동
					moveShark(r,c);
				}
			}
			
			//이동을 마친 후 한칸에 여러 상어가 있는 경우, 큰 상어만 남긴다
			arrageShark();
			map = next_map;
		}
		System.out.println(answer);
	}


	private static void moveShark(int r, int c) {
		int dir = map[r][c].get(0).dir;
		int v = map[r][c].get(0).v;
		int init_v = v;
		int scale = map[r][c].get(0).scale;
		int end = 0;
		int byuk_v = 0;
		int q = 0;
		int div = 0;
		int mod = 0;
		int nr = 0;
		int nc = 0;
		
		if(v == 0) {
			next_map[r][c].add(new Shark(init_v, dir, scale));
			return;
		}
		
		if(dir==1) {//위
			end = 1;
			byuk_v = r-1;
			if(v<byuk_v) {
				nr = r-v;
				nc = c;
			}else if(v==byuk_v) {//방향 변경
				dir = changeDir(dir);
				nr = 1;
				nc = c;
			}else {//계산
				v -= byuk_v;
				q = R-1;
				div = v/q;
				mod = v%q;
				
				if(div%2==0) {//방향 변경
					dir = changeDir(dir);
					nr = mod+1;
					nc = c;
				}else {//방향 그대로
					nr = (q-mod)+1;
					nc = c;
				}
			}
		}else if(dir==2) {//아래
			end = 1;
			byuk_v = R-r;
			if(v<byuk_v) {
				nr = r+v;
				nc = c;
			}else if(v==byuk_v) {//방향 변경
				dir = changeDir(dir);
				nr = R;
				nc = c;
			}else {//계산
				v -= byuk_v;
				q = R-1;
				div = v/q;
				mod = v%q;
				
				if(div%2==0) {//방향 그래로
					dir = changeDir(dir);
					nr = (q-mod)+1;
					nc = c;
				}else {//방향 변경
					nr = mod+1;
					nc = c;
				}
			}
		}else if(dir==3) {//오른쪽
			end = 1;
			byuk_v = C-c;
			
			if(v<byuk_v) {
				nr = r;
				nc = c+v;
			}else if(v==byuk_v) {//방향 변경
				dir = changeDir(dir);
				nr = r;
				nc = C;
			}else {//계산
				v -= byuk_v;
				q = C-1;
				div = v/q;
				mod = v%q;
				
				if(div%2==0) {//방향 그래로
					dir = changeDir(dir);
					nr = r;
					nc = (q-mod)+1;
				}else {//방향 변경
					nr = r;
					nc = mod+1;
				}
			}
		}else if(dir==4) {//왼쪽
			end = 1;
			byuk_v = c-1;
			if(v<byuk_v) {
				nr = r;
				nc = c-v;
			}else if(v==byuk_v) {//방향 변경
				dir = changeDir(dir);
				nr = r;
				nc = 1;
			}else {//계산
				v -= byuk_v;
				q = C-1;
				div = v/q;
				mod = v%q;
				
				if(div%2==0) {//방향 변경
					dir = changeDir(dir);
					nr = r;
					nc = mod+1;
				}else {//방향 그대로
					nr = r;
					nc = (q-mod)+1;
				}
			}
		}
		
		next_map[nr][nc].add(new Shark(init_v, dir, scale));
	}


	private static int changeDir(int dir) {
		if(dir==1) {
			dir = 2;
		}else if(dir==2) {
			dir = 1;
		}else if(dir==3) {
			dir = 4;
		}else if(dir==4) {
			dir = 3;
		}
		return dir;
	}


	private static void arrageShark() {
		//next_map에 대해 적용
		//한 칸에 여러마리의 상어가 있는 경우 가장 큰 한마리만 남김
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if(next_map[r][c].size()<=1) continue;
			
				//크기순의 내림차순으로
				Collections.sort(next_map[r][c], new Comparator<Shark>() {
					@Override
					public int compare(Shark o1, Shark o2) {
						int s1 = o1.scale;
						int s2 = o2.scale;
						
						return Integer.compare(s2, s1);
					}
				});
				int v = next_map[r][c].get(0).v;
				int dir = next_map[r][c].get(0).dir;
				int scale = next_map[r][c].get(0).scale;
				
				Shark remain_shark = new Shark(v, dir, scale);//크가가 가장 큰 상
				next_map[r][c] = new ArrayList<>();
				next_map[r][c].add(remain_shark);
			}
		}
	}
}
