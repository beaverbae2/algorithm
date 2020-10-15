package SAMSUNG_2020_10_15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_15684_사다리조작 {
	static int[][] map;
	static int[][] adj;//인접 정보
	static int N,M,H;
	static int answer;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//열
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());//행
		map = new int[H+2][N+1];
		adj = new int[H+2][N+1];
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[r][c] = 1;//오
			adj[r][c+1] = -1;//왼
		}
		calc();
		dfs(1,0);
		if(answer==Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}
	
	//조합
	private static void dfs(int start, int cnt) {
		if(cnt==4) return;
		
		//계산
		if(calc()) answer = Math.min(answer, cnt);
		
		
		for (int i = start; i <= N*H ; i++) {
			int r = i/N+1;
			int c = i%N;
			if(c==0) {//맨끝
				r = r-1;
				c = N;
			}
			//사다리가 연속 되어 있는지 확인
			if(r>0&&r<H+1&&c>0&&c<N) {
				if(adj[r][c]!=0||adj[r][c+1]!=0) {//연속하는 경우
					continue;
				}
				
				int v1 = adj[r][c];
				int v2 = adj[r][c+1];
				
				adj[r][c] = 1;
				adj[r][c+1] = -1;
				
				dfs(i+1, cnt+1);
				adj[r][c] = v1;
				adj[r][c+1] = v2;
			}
		}
	}

	private static boolean calc() {
		int count = 0;
		
		for (int i = 1; i <= N; i++) {
			int r = 0;
			int c = i;
			int nr = 0;
			int nc = 0;
			
			boolean isTurn = false;
			while(true) {
				if(adj[r][c]!=0) {
					if (!isTurn) {
						nr = r;
						nc = c+adj[r][c];
						isTurn = true;
					}else {
						nr = r+1;
						nc = c;
						isTurn = false;
					}
				}else {
					nr = r+1;
					nc = c;
					isTurn = false;
				}
				r = nr;
				c = nc;
				if(r==H+1) {
//					System.out.println("사다리 번호 : "+i+", 도착 번호: "+c);
					if(c==i) count++;
					break;
				}
			}
		}
		if(count==N) return true;
		return false;
	}
}
