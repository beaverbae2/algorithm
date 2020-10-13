package SAMSUNG_2020_10_12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17837_새로운게임2 {
	static int N,K,answer;
	static int[][] dirs = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
	static int[][] map;
	static Elem[] elems;
	static ArrayList<Integer>[][] elem_map;
	
	static class Elem{
		int r,c,dir;

		public Elem(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Elem [r=" + r + ", c=" + c + ", dir=" + dir + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		elem_map = new ArrayList[N][N];
		elems = new Elem[K+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				elem_map[i][j] = new ArrayList<>();
			}
		}
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			elems[i] = new Elem(r, c, dir);
			elem_map[r][c].add(i);
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(elem_map[i][j].toString()+" ");
//			}
//			System.out.println();
//		}
		
		boolean flag = false;
		while(true) {
			//if(answer==2) break;
			if(answer>1000) {
				answer = -1;
				break;
			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(elem_map[i][j].toString() + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			//모든 순번에 대해 반복
			for (int i = 1; i < elems.length; i++) {
//				System.out.println("elems : "+elems[i]);
//				System.out.println(i);
				Elem e = elems[i];
				int r = e.r;
				int c = e.c;
				int dir = e.dir;
				int nr = r+dirs[dir][0];
				int nc = c+dirs[dir][1];
				
				if(isBlue(nr, nc)) {
					//방향 전환
					dir = reverseDirection(dir);
					elems[i].dir = dir;
					nr = r+dirs[dir][0];
					nc = c+dirs[dir][1];
					
					if(isBlue(nr, nc)) {
						continue;
					}else if(map[nr][nc]==0) {//흰색
						moveElem(r,c,nr,nc,i);
					}else if(map[nr][nc]==1) {//빨간색
						moveElem(r,c,nr,nc,i);
						reverseElem(nr, nc, i,dir);
					}
				}else if(map[nr][nc]==0) {//흰색
					moveElem(r,c,nr,nc,i);
				}else if(map[nr][nc]==1) {//빨간색
					moveElem(r,c,nr,nc,i);
					reverseElem(nr,nc,i,dir);
				}
				
				if(isFourElemInSameSpace()) {
					flag = true;
					break;
				}
				
//				for (int j = 0; j < N; j++) {
//					for (int k = 0; k < N; k++) {
//						System.out.print(elem_map[j][k].toString() + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
			answer++;
			if(flag) break;
//			System.out.println(answer);
		}
		System.out.println(answer);
	}

	private static int reverseDirection(int dir) {
		if(dir==1) {
			return 2;
		}else if(dir==2) {
			return 1;
		}else if(dir==3) {
			return 4;
		}else if(dir==4) {
			return 3;
		}
		return 0;
	}

	//해당 위치의 말 거꾸로 뒤집기
	private static void reverseElem(int r, int c, int id, int dir) {
		ArrayList<Integer> reversed_elem = new ArrayList<>();
		ArrayList<Integer> staying_elem = new ArrayList<>();
		
		for (int i = elem_map[r][c].size()-1; i >= 0; i--) {
			int elem_id = elem_map[r][c].get(i);
			
			if(elem_id==id) {
				for (int j = elem_map[r][c].size()-1; j >=i; j--) {
					reversed_elem.add(elem_map[r][c].get(j));
				}
				for (int j = 0; j < i; j++) {
					staying_elem.add(elem_map[r][c].get(j));
				}
				elem_map[r][c] = new ArrayList<>();;
				for (int j = 0; j < staying_elem.size(); j++) {
					elem_map[r][c].add(staying_elem.get(j));
				}
				for (int j = 0; j < reversed_elem.size(); j++) {
					elem_map[r][c].add(reversed_elem.get(j));
				}
				break;
			}
		}
	}

	//말의 이동
	private static void moveElem(int r, int c, int nr, int nc, int id) {
		for (int i = 0; i < elem_map[r][c].size(); i++) {
			int elem_id = elem_map[r][c].get(i);
			
			if(elem_id==id) {//움직이려하는 말의 번호 찾음
				ArrayList<Integer> new_elem = new ArrayList<>();
				ArrayList<Integer> old_elem = new ArrayList<>();
				
				for (int j = 0; j < i; j++) {
					old_elem.add(elem_map[r][c].get(j));
				}
				
				for (int j = i; j < elem_map[r][c].size(); j++) {
					new_elem.add(elem_map[r][c].get(j));
					int dir = elems[elem_map[r][c].get(j)].dir; //이부분
					elems[elem_map[r][c].get(j)] = new Elem(nr, nc, dir);//이부분
				}
				//System.out.println("new: "+new_elem);
				if(elem_map[nr][nc].size()==0) {
					elem_map[nr][nc] = new_elem;
				}else {
					elem_map[nr][nc].addAll(new_elem);
				}
				elem_map[r][c] = old_elem;
				break;
			}
		}
	}


	//이동하려는 칸이 파란색 or 범위밖
	private static boolean isBlue(int nr, int nc) {
		if(nr<0||nr>=N||nc<0||nc>=N) return true;
		else if(map[nr][nc]==2) return true;
		return false;
	}

	private static boolean isFourElemInSameSpace() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(elem_map[i][j].size()>=4) return true;
			}
		}
		
		return false;
	}
}
