package A2020_12_06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20057_마법사_상어와_토네이도 {
	static int[][] map;
	static int N;
	static int answer = 0;
	static int[][] dirs = {{0,-1},{1,0},{0,1},{-1,0}};
	static int[][] spread_dirs = {{-1,0},{1,0},{0,1},{0,-1},
								  {1,1},{1,-1},{-1,1},{-1,-1},
								  {2,0},{-2,0},{0,2},{0,-2}};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		tonato(N/2,N/2);
		System.out.println(answer);
	}


	private static void tonato(int sr, int sc) {
		//서남동북
		int r = sr;
		int c = sc;
		int moveCnt = 1;
		int nr = r;
		int nc = c;
		while(true) {
			for (int d = 0; d < dirs.length; d++) {
				for (int i = 1; i <= moveCnt; i++) {
					nr += dirs[d][0];
					nc += dirs[d][1];
//					System.out.println("sand : "+map[nr][nc]);
//					System.out.println("nr : "+nr+", nc : "+nc+", d : "+d);
					if (isInMap(nr, nc)) spreadSand(nr,nc,d);
//					for (int j = 0; j < map.length; j++) {
//						System.out.println(Arrays.toString(map[j]));
//					}
//					System.out.println(answer);
//					System.out.println();
				}
				
				if((d+1)%2==0) moveCnt++;
				if(nr==0&&nc==-1) break;
			}
			if(nr==0&&nc==-1) break;
		}
	}


	private static void spreadSand(int r, int c, int d) {
		int remain_sand = map[r][c];
		
		for (int sd = 0; sd < spread_dirs.length; sd++) {
			int nr = r+spread_dirs[sd][0];
			int nc = c+spread_dirs[sd][1];
			
			int move = moveSand(r,c,nr,nc,d);
			remain_sand -= move;
			if(isInMap(nr, nc)) {
				map[nr][nc] += move;
			}else {
				answer += move;
			}
		}
		
		int ar = r+dirs[d][0];
		int ac = c+dirs[d][1];
		if(isInMap(ar, ac)) map[ar][ac] += remain_sand;
		else answer += remain_sand;
		map[r][c] = 0;
	}
	
	private static int moveSand(int r,int c,int nr, int nc, int d) {
		int sand = map[r][c];
		int sub_r = nr-r;
		int sub_c = nc-c;
		if(d==0) {//서
			if(sub_c==-2) {
				if(sub_r==0) return (int) (0.05*sand);
			}else if(sub_c==-1) {
				if(sub_r==-1) return (int) (0.1*sand);
				else if(sub_r==1) return (int) (0.1*sand);
			}else if(sub_c == 0) {
				if(sub_r==-2) return (int) (0.02*sand);
				else if(sub_r==-1) return (int) (0.07*sand);
				else if(sub_r==1) return (int) (0.07*sand);
				else if(sub_r==2) return (int) (0.02*sand);
			}else if(sub_c==1) {
				if(sub_r==-1) return (int) (0.01*sand);
				else if(sub_r==1) return (int) (0.01*sand);
			}
		}else if(d==1) {//남
			if(sub_r==2) {
				if(sub_c==0) return (int) (0.05*sand);
			}else if(sub_r==1) {
				if(sub_c==-1) return (int) (0.1*sand);
				else if(sub_c==1) return (int) (0.1*sand);
			}else if(sub_r == 0) {
				if(sub_c==-2) return (int) (0.02*sand);
				else if(sub_c==-1) return (int) (0.07*sand);
				else if(sub_c==1) return (int) (0.07*sand);
				else if(sub_c==2) return (int) (0.02*sand);
			}else if(sub_r==-1) {
				if(sub_c==-1) return (int) (0.01*sand);
				else if(sub_c==1) return (int) (0.01*sand);
			}
		}else if(d==2) {//동
			if(sub_c==2) {
				if(sub_r==0) return (int) (0.05*sand);
			}else if(sub_c==1) {
				if(sub_r==-1) return (int) (0.1*sand);
				else if(sub_r==1) return (int) (0.1*sand);
			}else if(sub_c == 0) {
				if(sub_r==-2) return (int) (0.02*sand);
				else if(sub_r==-1) return (int) (0.07*sand);
				else if(sub_r==1) return (int) (0.07*sand);
				else if(sub_r==2) return (int) (0.02*sand);
			}else if(sub_c==-1) {
				if(sub_r==-1) return (int) (0.01*sand);
				else if(sub_r==1) return (int) (0.01*sand);
			}
		}else if(d==3) {//북
			if(sub_r==-2) {
				if(sub_c==0) return (int) (0.05*sand);
			}else if(sub_r==-1) {
				if(sub_c==-1) return (int) (0.1*sand);
				else if(sub_c==1) return (int) (0.1*sand);
			}else if(sub_r == 0) {
				if(sub_c==-2) return (int) (0.02*sand);
				else if(sub_c==-1) return (int) (0.07*sand);
				else if(sub_c==1) return (int) (0.07*sand);
				else if(sub_c==2) return (int) (0.02*sand);
			}else if(sub_r==1) {
				if(sub_c==-1) return (int) (0.01*sand);
				else if(sub_c==1) return (int) (0.01*sand);
			}
		}
		return 0;
	}


	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<N&&nc>=0&&nc<N;
	}
}
