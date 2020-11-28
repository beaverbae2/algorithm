package A2020_11_28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	static int answer1, answer2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		//색약x
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				bfs(i,j,map[i][j],false);
				answer1++;
			}
		}
		
		//색약o
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				bfs(i,j,map[i][j],true);
				answer2++;
			}
		}
		System.out.println(answer1+" "+answer2);
	}
	
	private static void bfs(int i, int j, char color, boolean isColorBlind) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
				
				if(!isInMap(nr, nc)||visited[nr][nc]) continue;
			
				if(isColorBlind) {
					if(map[r][c]=='R') {
						if(map[nr][nc]=='B') continue;
					}else if(map[r][c]=='G') {
						if(map[nr][nc]=='B') continue;
					}else if(map[r][c]=='B') {
						if(map[nr][nc]!='B') continue;
					}
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}else {
					if(map[r][c]==map[nr][nc]) {
						q.offer(new int[] {nr,nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<N&&nc>=0&&nc<N;
	}
}
