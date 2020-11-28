package SAMSUNG_2020_10_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2 {
	static int N,M;
	static char[][] map;
	static int answer;
	static int hole_r,  hole_c;
	static int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
	
	static class Ball{
		int r,c;
		char color;
		
		public Ball(int r, int c, char color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Ball [r=" + r + ", c=" + c + ", color=" + color + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		Ball red = new Ball(0,0,'R');
		Ball blue = new Ball(0,0,'B');
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='R') {
					red = new Ball(i, j, 'R');
					map[i][j] = '.';
				}else if(map[i][j]=='B') {
					blue = new Ball(i,j,'B');
					map[i][j] = '.';
				}else if(map[i][j]=='O') {
					hole_r = i;
					hole_c = j;
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		bfs(red,blue);
	}

	private static void bfs(Ball b1, Ball b2) {
		Queue<Ball[]> q = new LinkedList<>();
		q.offer(new Ball[] {b1,b2});
		
		while(true) {
			Ball[] balls = q.poll();
			int r1 = balls[0].r;
			int c1 = balls[0].c;
			int r2 = balls[1].r;
			int c2 = balls[1].c;
			
			for (int d = 0; d < dirs.length; d++) {
				if(d==0||d==1) {
					if(c1==c2) {
						
					}else {
						//이동
						for (int i = 0; i < balls.length; i++) {
							int r = balls[i].r;
							int c = balls[i].c;
							for (int j = 0; j < N; j++) {
								int nr = r+dirs[d][0];
								int nc = c+dirs[d][1];
								
								if(map[nr][nc]=='#') {
									
								}
							}
						}
						
					}
				}else if(d==2||d==3) {
					if(r1==r2) {
						
					}else {
						
					}
				}
			}
		}
	}

	
}
