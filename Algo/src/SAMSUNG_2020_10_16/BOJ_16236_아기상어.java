package SAMSUNG_2020_10_16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 실수 한 부분
 * 1. bfs의 시작을 상어의 좌표로 지정하고,
 * 이동 중 상어를 만나면 해당 상어까지의 거리를 구하고,
 * 같은 거리에 있는 상어들을 추가,
 * 해당 거리 넘으면 탐색 중단
 * 
 * 2. 이동 후 처리
 * map의 현재 상어 위치를 0으로 설정 안함
 *
 */


public class BOJ_16236_아기상어 {
	static int N;
	static int[][] map;
	static int answer, shark_r, shark_c,shark_scale, shark_eat;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static String src = "3\r\n" + 
			"0 0 0\r\n" + 
			"0 0 0\r\n" + 
			"0 9 0";
	static ArrayList<int[]> isEatFishes;//먹을수 있는지 확인해야함
	static ArrayList<int[]> canEatFishes;//확실히 먹을 수 있음
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {//상어
					shark_r = i;
					shark_c = j;
					shark_scale = 2;
					shark_eat = 0;
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		while(true) {
			canEatFishes = new ArrayList<>();
			
			//후보군 추가
			bfs(shark_r,shark_c);
			
			if(canEatFishes.size()==0) {
				break;
			}else if(canEatFishes.size()>1) {
				Collections.sort(canEatFishes, new Comparator<int[]>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						int r1 = o1[0];
						int r2 = o2[0];
						if(r1!=r2) {
							return Integer.compare(r1, r2);
						}else {
							int c1 = o1[1];
							int c2 = o2[1];
							return Integer.compare(c1, c2);
						}
					}
				});
			}
			
			//해당 물고기를 먹음
			int elem[] = canEatFishes.get(0);
			map[shark_r][shark_c] = 0;
			shark_r = elem[0];
			shark_c = elem[1];
			int d = elem[2];
			map[shark_r][shark_c] = 9;
			
			
			shark_eat++;
			if(shark_eat==shark_scale) {
				shark_eat = 0;
				shark_scale++;
			}
			answer += d;
			
		}
		System.out.println(answer);
		
	}


	private static void bfs(int shark_r, int shark_c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {shark_r, shark_c, 0});
		boolean[][] visited = new boolean[N][N];
		visited[shark_r][shark_c] = true;
		
		int thres_d = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int d = elem[2];
			
			if(thres_d<d) break;
			
			if(map[r][c]>=1&&map[r][c]<=6&&map[r][c]<shark_scale) {
				canEatFishes.add(new int[] {r,c,d});
				thres_d = d;
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				
				if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]) continue;
				
				if(map[nr][nc]<=shark_scale) {
					q.offer(new int[] {nr,nc,d+1});
					visited[nr][nc] = true;
				}
			}
			
		}
	}
}
