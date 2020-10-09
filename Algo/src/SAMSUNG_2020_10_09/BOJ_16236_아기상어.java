package SAMSUNG_2020_10_09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 푸는데 오래 걸린 이유
 * 1. 
 * !visited[i][j] 조건인 경우만  q에 값을 추가해야되는데 이걸 뺴버렸다....  
 * 
 * 2.
 * 상어가 물고기를 잡아 먹고 나서의 처리가 미흡했다(특히 잡아 먹은 후 원래자리 0, 현재자리 9로 표기하는거....)
 * -> 해당 부분은 코드 부분에 주석 달아놨다
 * -> 문제를 똑바로 읽고 설계를 더 명확히 한 다음 코딩하자
 * 
 * 최적화 좀 해야겠다....
 */
public class BOJ_16236_아기상어 {
	static int[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int N, answer;
	static Shark shark;
	static ArrayList<Elem> canEatFishes; 
	
	static class Elem{
		int r,c,d;

		public Elem(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static class Shark{
		int r,c,scale, eat;

		public Shark(int r, int c, int scale, int eat) {
			this.r = r;
			this.c = c;
			this.scale = scale;
			this.eat = eat;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark = new Shark(i, j, 2, 0);
				}
			}
		}
		while(true) {
			canEatFishes = new ArrayList<>();
			
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if(map[i][j]==0||map[i][j]==9) continue;
				
					if(map[i][j] < shark.scale) {
						bfs(i,j);
					}
				}
			}
			Collections.sort(canEatFishes, new Comparator<Elem>() {

				@Override
				public int compare(Elem e1, Elem e2) {
					int d1 = e1.d;
					int d2 = e2.d;
					
					if(d1 != d2) return Integer.compare(d1, d2);
					else {
						int r1 = e1.r;
						int r2 = e2.r;
						if(r1 != r2) return Integer.compare(r1, r2);
						else {
							int c1 = e1.c;
							int c2 = e2.c;
							return Integer.compare(c1, c2);
						}
					}
				}

				
			});
			if(canEatFishes.isEmpty()) break;
			else {//오래걸린 부분(상어가 물고기를 잡아 먹은 후)
				Elem e = canEatFishes.get(0);
				map[shark.r][shark.c] = 0; //이전의 상어 좌표를 0으로
				map[e.r][e.c] = 9; //현재 상어 좌표를 9로
				shark.r = e.r;//상어 행 좌표 변경
				shark.c = e.c;//상어 열 좌표 변경
				answer += e.d;//이동 거리 변경
				shark.eat += 1;//상어가 물고기를 하나 먹었으므로
				if(shark.scale==shark.eat) {//상어의 크기와 물고기를 먹은 갯수가 같다면
					shark.scale += 1;//상어 크기 1증가
					shark.eat = 0;//먹은거 초기화
				}
				
			}
		}
		System.out.println(answer);
	}
	
	private static void bfs(int dr, int dc) {
		Queue<Elem> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Elem(shark.r, shark.c,0));
		visited[shark.r][shark.c] = true;
		
		while(!q.isEmpty()) {
			Elem e = q.poll();
			int r = e.r;
			int c = e.c;
			int d = e.d;
			
			if(r==dr && c==dc) {
				canEatFishes.add(new Elem(r, c, d));
				break;
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int nr = r+dirs[i][0];
				int nc = c+dirs[i][1];
				
				if (isInMap(nr, nc) && !visited[nr][nc] && map[nr][nc]<=shark.scale) {
					q.offer(new Elem(nr, nc, d+1));
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
