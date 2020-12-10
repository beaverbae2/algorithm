package A2020_12_03;

import java.util.*;
import java.io.*;

public class BOJ_14500_테트로미노 {
	static int map[][];
	static int N,M,answer;
	static int[][][][] tetro = {
								{
								 {{0,0},{0,1},{0,2},{0,3}},
								 {{0,0},{1,0},{2,0},{3,0}}
								},
							  	{
								 {{0,0},{0,1},{1,0},{1,1}}
								},
							   {
							    {{0,-1},{0,0},{1,0},{2,0}},
							    {{0,1},{0,0},{1,0},{2,0}},
							    {{2,-1},{0,0},{1,0},{2,0}},
							    {{2,1},{0,0},{1,0},{2,0}},
								
							    {{1,0},{0,0},{0,1},{0,2}},
							    {{-1,0},{0,0},{0,1},{0,2}},
							    {{1,2},{0,0},{0,1},{0,2}},
							    {{-1,2},{0,0},{0,1},{0,2}},
							   },
							   {
								{{0,0},{-1,0},{0,1},{1,1}},
							    {{0,0},{1,0},{0,1},{-1,1}},
								{{0,0},{0,1},{1,0},{1,-1}},
								{{0,0},{0,-1},{1,0},{1,1}}
								},
							   {
								{{0,0},{-1,0},{0,-1},{0,1}},
								{{0,0},{-1,0},{1,0},{0,1}},
								{{0,0},{0,-1},{0,1},{1,0}},
								{{0,0},{-1,0},{1,0},{0,-1}}
							   }
							  };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				answer = Math.max(answer, getMaxSum(r,c));
			}
		}
		System.out.println(answer);
		
		
	}

	private static int getMaxSum(int a, int b) {
		int max = 0;
		for (int r = 0; r < tetro.length; r++) {
			for (int c = 0; c < tetro[r].length; c++) {
				int temp_max = 0;
				int cnt = 0;
				for (int i = 0; i < tetro[r][c].length; i++) {
					int nr = a + tetro[r][c][i][0];
					int nc = b + tetro[r][c][i][1];	
					
					if(IsInMap(nr, nc)==1) {
						cnt++;
						temp_max += map[nr][nc];
					}
				}
				
				if(cnt==4) {
					max = Math.max(max, temp_max);
				}
			}
		}
		
		return max;
	}
	
	private static int IsInMap(int nr, int nc) {
		if(nr<0||nr>=N||nc<0||nc>=M) return 0;
		return 1;
	}
}
