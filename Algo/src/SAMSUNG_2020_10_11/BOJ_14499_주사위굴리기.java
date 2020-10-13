package SAMSUNG_2020_10_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
	static int[][] map;
	static int[] command;
	static int N,M,K;
	static int[][] dirs = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};//동서북남
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		command = new int[K];
		sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			command[i] = Integer.parseInt(st.nextToken());
		}
		
		int top=0,bot=0,front=0,back=0,right=0,left=0;//주사위의 좌표
		//초기화
		if(map[r][c]==0) {
			map[r][c] = bot;
		}else {
			bot = map[r][c];
			map[r][c] = 0;
		}
		//sb.append(top).append("\n");
		
		//본격 주사위 굴리기
		for (int i = 0; i < K; i++) {
			int d = command[i];
			int nr = r+dirs[d][0];
			int nc = c+dirs[d][1];
			
			//System.out.println(nr+", "+nc);
			if(!isInMap(nr, nc)) continue;
			
			int next_top=0,next_bot=0,next_front=0,next_back=0,next_right=0,next_left=0;
			
			if(d==1) {//동
				next_right = top;
				next_left = bot;
				next_front = front;
				next_back = back;
				next_bot = right;
				next_top = left;
			}else if(d==2) {//서
				next_left = top;
				next_right = bot;
				next_front = front;
				next_back = back;
				next_top = right;
				next_bot = left;
			}else if(d==3) {//북
				next_front = top;
				next_back = bot;
				next_bot = front;
				next_top = back;
				next_right = right;
				next_left = left;
			}else if(d==4) {//남
				next_back = top;
				next_front = bot;
				next_top = front;
				next_bot = back;
				next_right = right;
				next_left = left;
			}
			
			top = next_top;
			bot = next_bot;
			front = next_front;
			back = next_back;
			left = next_left;
			right = next_right;
			
			if(map[nr][nc]==0) {
				map[nr][nc] = bot;
			}else {
				bot = map[nr][nc];
				map[nr][nc] = 0;
			}
			
			r = nr;
			c = nc;
		
			sb.append(top).append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}
}	
