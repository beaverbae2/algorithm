package SAMSUNG_2020_10_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작ver2 {
	static int C,M,R;//세로선(열), 가로선, 가로선을 놓을 수 있는 위치(행)
	static int[][] map;// H*N;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R+1][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
		
			map[r][c] = 1;//오
			map[r][c+1] = -1;//왼
		}
//		for (int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		dfs(0,0);//사다리 놓기
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}

	private static void dfs(int cnt, int start) {
		if(cnt==4) return;
	
		//i -> i인지 계산
		if(calc()) answer = Math.min(answer, cnt); 
		
		
		for (int i = start; i < R*C; i++) {
			int r = i/C;
			int c = i%C;
			if(c == C-1) continue;//맨끝은 사다리 못 놓음
			
			int v1 = map[r][c];
			int v2 = map[r][c+1];
			
			//사다리 놓기 : 연속성 판단
			if(c==0) {//
				if(map[r][c+1]!=0) continue;
			}else {
				//놓으려는 위치에 가로선이 있거나 다음위치에 가로선이 있는 경우 못 놓음
				if(map[r][c]!=0||map[r][c+1]!=0) continue;
			}
			map[r][c] = 1;
			map[r][c+1] = -1;
			dfs(cnt+1, i+1);
			
			map[r][c] = v1;
			map[r][c+1] = v2;
		}
	}

	private static boolean calc() {
		int count = 0;
		for (int k = 0; k < C; k++) {//세로선에 대해 적용
			int r = 0;
			int c = k;
			int nr = 0;
			int nc = 0;
			
			while(true) {
				if(map[r][c]==0) {//내려감
					nr = r+1;
					nc = c;
				}else {
					nr = r+1;
					nc = c+map[r][c];
				}
				r = nr;
				c = nc;
				if(r==R) {
					if(c==k) count++;
					break;
				}
			}
		}
		if(count==C) return true;
		return false;
	}
}
