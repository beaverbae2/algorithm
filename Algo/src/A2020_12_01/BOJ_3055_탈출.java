package A2020_12_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055_탈출 {
	static char[][] map;
	static int R,C;
	static int start_r, start_c;
	static ArrayList<int[]> water;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		water = new ArrayList<>();
		
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if(map[r][c]=='*') {//물
					water.add(new int[] {r,c});
				}else if(map[r][c]=='S') {
					start_r = r;
					start_c = c;
					map[r][c] = '.';
				}
			}
		}
		
		int answer = bfs();
		if(answer == Integer.MAX_VALUE) System.out.println("KAKTUS");
		else System.out.println(answer);
	}

	private static int bfs() {
		Queue<int[]> q1 = new LinkedList<>();//고습도치
		boolean[][] visited1 = new boolean[R][C];
		q1.offer(new int[] {start_r, start_c, 0});
		visited1[start_r][start_c] = true;
		
		Queue<int[]> q2 = new LinkedList<>();//홍수
		boolean[][] visited2 = new boolean[R][C];
		for(int[] elem : water) {
			int r = elem[0];
			int c = elem[1];
			q2.offer(new int[] {r,c,0});
			visited2[r][c] = true;
		}
		
		int threshold = 0;
		while(true) {
			while(!q2.isEmpty()) {//홍수 먼저
				int[] elem2 = q2.peek();
				int r2 = elem2[0];
				int c2 = elem2[1];
				int depth = elem2[2];
				if(depth>threshold) { 
					break;
				}
				
				q2.poll();
				
				for (int d = 0; d < dirs.length; d++) {
					int nr2 = r2 + dirs[d][0];
					int nc2 = c2 + dirs[d][1];
				
					if(!isInMap(nr2, nc2)||visited2[nr2][nc2]) continue;
					
					if(map[nr2][nc2]=='.') {
						q2.offer(new int[] {nr2, nc2,depth+1});
						visited2[nr2][nc2] = true;
						map[nr2][nc2] = '*';
					}
				}
			}
			
			
			while(!q1.isEmpty()) {
				
				int[] elem1 = q1.peek();
				int r1 = elem1[0];
				int c1 = elem1[1];
				int time = elem1[2];
				
				if(time>threshold) break;
//				System.out.println("r1 : "+r1+", c1 : "+c1);
				
				q1.poll();
				for (int d = 0; d < dirs.length; d++) {
					int nr1 = r1 + dirs[d][0];
					int nc1 = c1 + dirs[d][1];
					
					if(!isInMap(nr1, nc1)||visited1[nr1][nc1]) continue;
					
					if(map[nr1][nc1]=='.') {
						q1.offer(new int[] {nr1, nc1,time+1});
						visited1[nr1][nc1] = true;
//						System.out.println("nr1 : "+nr1+", nc1 : "+nc1);
					}else if(map[nr1][nc1]=='D') { 
						return time+1;
					}
				}
			}
			threshold++;
			
			if(q1.isEmpty()) break;
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
		}
		
		return Integer.MAX_VALUE;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<R&&nc>=0&&nc<C;
	}
}
