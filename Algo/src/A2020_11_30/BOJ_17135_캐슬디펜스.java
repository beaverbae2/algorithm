package A2020_11_30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {
	static int N,M,D,answer;
	static int[][] map, copy_map;
	static int[] bower;
	static boolean[][] isKilled;
	static ArrayList<int[]> killedList;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
//		System.out.println(D);
		map = new int[N+1][M];
		bower = new int[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combi(0,0);
		//game();
		System.out.println(answer);
	}

	private static void combi(int start, int r) {
		if(r==3) {//궁수 3명
			//게임 시작
			answer = Math.max(answer, game());
			return;
		}
		for (int i = start; i < M; i++) {
			bower[r] = i;
			combi(i+1, r+1);
		}
	}

	private static int game() {
//		bower[0] = 0;
//		bower[1] = 1;
//		bower[2] = 2;
		
		int killed = 0;
		copy_map = new int[N+1][M];
		
		deepcopy();
		
		for (int i = 0; i < N; i++) {
			killedList = new ArrayList<>();
			isKilled = new boolean[N][M];
			
			//궁수의 공격
			for (int j = 0; j < bower.length; j++) {
				bfs(N,bower[j]);
			}
//			System.out.println(i+1);
//			for (int j = 0; j < N; j++) {
//				System.out.println(Arrays.toString(copy_map[j]));
//			}
			for (int[] elem : killedList) {
				int r = elem[0];
				int c = elem[1];
				if(!isKilled[r][c]) {
					isKilled[r][c] = true;
					copy_map[r][c] = 0;
					killed++;
				}
			}
			//적군 1칸식 이동
			moveEnemies();
//			System.out.println(i+1);
//			for (int j = 0; j < N; j++) {
//				System.out.println(Arrays.toString(copy_map[j]));
//			}
//			System.out.println();
		}
//		System.out.println("killed: "+killed);
		return killed;
	}

	private static void deepcopy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
	}

	private static void moveEnemies() {
		for (int i = N-2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copy_map[i+1][j] = copy_map[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			copy_map[0][i] = 0;
		}
	}

	private static void bfs(int bower_r, int bower_c) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N+1][M];
		q.offer(new int[] {bower_r, bower_c, 0});
		visited[bower_r][bower_c] = true;
		
		int enemy_dist = Integer.MAX_VALUE-1;
		ArrayList<int[]> enemies = new ArrayList<>();
//		System.out.println("bower_r : "+bower_r+" bower_c : "+bower_c);
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int dist = elem[2];
		
			if(dist>D) break;
			if(dist==enemy_dist+1) break;
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
			
				if(nr<0||nr>=N+1||nc<0||nc>=M||visited[nr][nc]) continue;
				
				if(copy_map[nr][nc]==1) {
					if(dist+1<=D&&enemy_dist==Integer.MAX_VALUE-1||dist+1 == enemy_dist) {
						enemy_dist = dist+1;
						enemies.add(new int[] {nr,nc});
					}
				}
				q.offer(new int[] {nr,nc,dist+1});
				visited[nr][nc] = true;
			}
		}
		if(!enemies.isEmpty()) {
			Collections.sort(enemies, new Comparator<int[]>() {

				@Override
				public int compare(int[] e1, int[] e2) {
					int c1 = e1[1];
					int c2 = e2[1];
					
					return Integer.compare(c1, c2);
				}
			});
			int[] enemy = enemies.get(0);
			int r = enemy[0];
			int c = enemy[1];
//			System.out.println("r : "+r+" c : "+c);
			killedList.add(new int[] {r,c});
		}
		
	}
}
