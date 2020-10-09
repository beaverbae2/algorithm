package SAMSUNG_2020_10_09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 오래 걸린 이유 
 * 1.
 * map[i][j] == 0(빈칸) 인 경우만 blank(남아있는 빈칸의 개수)를 빼야되는데
 * map[i][j] == 2(비활성화 바이러스)일떄도 뺴버림... -> 디버깅해서 발견
 * 
 * 2.
 * 예제 7번(빈칸 개수 0 인 경우)에 카운터 맞아버림 
 * -> 기존에 q에 값을 추가 할때 blank-- 하던 방식에서 q에서 poll할 때 blank-- 하는 방식으로 변경
 */

public class BOJ_17142_연구소3 {
	static int[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int N, M;
	static ArrayList<Pair> viruses;
	static Pair[] seletedViruses;
	static int initBlank, answer;
	
	static class Pair{
		int r,c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		seletedViruses = new Pair[M];
		viruses = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					viruses.add(new Pair(i,j));
				}else if(map[i][j]==0) initBlank++;
			}
		}
		makeCombination(0,0);
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	private static void makeCombination(int start, int r) {
		if(r==M) {
			answer = Math.min(answer, bfs());;
			return;
		}
		
		
		for (int i = start; i < viruses.size(); i++) {
			seletedViruses[r] = viruses.get(i);
			makeCombination(i+1, r+1);
		}
	}
	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for (Pair p : seletedViruses) {
			q.offer(new int[] {p.r, p.c, 0});
			visited[p.r][p.c] = true;
		}

		
		int blank = initBlank;
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int d = elem[2];
			
			if(map[r][c]==0) {
				blank--;
			}
			if(blank==0) return d;
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				
				if(nr<0||nr>=N||nc<0||nc>=N) continue;
				if(!visited[nr][nc]&&map[nr][nc]!=1) {
					q.offer(new int[] {nr,nc,d+1});
					visited[nr][nc] = true;
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
}
