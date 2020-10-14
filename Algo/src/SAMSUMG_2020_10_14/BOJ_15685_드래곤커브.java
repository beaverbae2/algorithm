package SAMSUMG_2020_10_14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {
	static boolean[][] visited;//방문체크
	static int N, answer;
	static int[][] curves;
	static int[][] dirs = {{0,1},{-1,0},{0,-1},{1,0}};
	static int[][] s_dirs = {{1,0},{1,1},{0,1}};
	static int[] pow = {1,2,4,8,16,32,64,128,256,512,1024};
	static ArrayList<Integer>[] dragon_move = new ArrayList[4];
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		curves = new int[N][4];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				curves[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < curves.length; i++) {
//			System.out.println(Arrays.toString(curves[i]));
//		}
		calc_dragonMove();
		draw_dragonMove();
		getSqureCount();
//		for (int i = 0; i < visited.length; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		System.out.println(answer);
	}

	private static void getSqureCount() {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				if(visited[i][j]&&isSquare(i,j)) answer++;
			}
		}
	}

	private static boolean isSquare(int r, int c) {
		int cnt=0;
		for (int d = 0; d < s_dirs.length; d++) {
			int nr = r+s_dirs[d][0];
			int nc = c+s_dirs[d][1];
			
			if(isInMap(nr,nc)&&visited[nr][nc]) cnt++;
		}
		if(cnt==3) return true;
		return false;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<visited.length && nc>=0 && nc<visited[0].length;
	}

	private static void draw_dragonMove() {
		int max_r=0;
		int max_c=0;
		LinkedList<int[]> pairs = new LinkedList<>();
		
		for (int i = 0; i < curves.length; i++) {
			int c = curves[i][0];
			int r = curves[i][1];
			int d = curves[i][2];
			int gen = curves[i][3];
			max_r = Math.max(r, max_r);
			max_c = Math.max(c, max_c);
			pairs.add(new int[] {r,c});
			//System.out.println("i :"+i);
			for (int j = 0; j < pow[gen]; j++) {
				int dir = dragon_move[d].get(j);
				int nr = r+dirs[dir][0];
				int nc = c+dirs[dir][1];
				r = nr;
				c = nc;
				//System.out.println("r : "+r+", c: "+c);
				max_r = Math.max(r, max_r);
				max_c = Math.max(c, max_c);
				pairs.add(new int[] {r,c});
			}
			//System.out.println();
		}
		visited = new boolean[max_r+1][max_c+1];
		for (int[] pair : pairs) {
			int r = pair[0];
			int c = pair[1];
			//System.out.println("r : "+r+", c :"+c);
			visited[r][c] = true;
		}
	}


	private static void calc_dragonMove() {
		for (int i = 0; i < dragon_move.length; i++) {
			dragon_move[i] = new ArrayList<>();
			dragon_move[i].add(i);
		}
		
		while(true) {
			if(dragon_move[0].size()==pow[10]) break;
			
			//어레이리스트 사이즈 변경에 주의
			for (int i = 0; i < dragon_move.length; i++) {
				int size = dragon_move[i].size()-1;
				for (int j = size; j >= 0; j--) {
					int dir = dragon_move[i].get(j);
					int next_dir = reverse90(dir);
					dragon_move[i].add(next_dir);
				}
				
			}
		}
//		for (int i = 0; i < dragon_move.length; i++) {
//			for (int j = 0; j < pow[2]; j++) {
//				System.out.print(dragon_move[i].get(j)+" ");
//			}
//			System.out.println();
//		}
//		
	}
	
	private static int reverse90(int dir) {
		if(dir==0) {
			dir=1;
		}else if(dir==1) {
			dir=2;
		}else if(dir==2) {
			dir=3;
		}else if(dir==3) {
			dir=0;
		}
		return dir;
	}
	
}
