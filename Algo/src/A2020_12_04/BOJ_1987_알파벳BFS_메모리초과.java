package A2020_12_04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳BFS_메모리초과 {
	static int R,C;
	static int answer;
	static char[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
	
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		bfs();
		System.out.println(answer);
	}
	

	

	private static void bfs() {
		//초기화
		Queue<Game> q = new LinkedList<>();
		boolean[] visited = new boolean[26];
		visited[map[0][0]-'A'] = true;
		q.offer(new Game(0, 0, 1, visited));
	
		//진행
		while(!q.isEmpty()) {
			Game temp = q.poll();
			
			answer = Math.max(answer, temp.d);
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = temp.r+dirs[d][0];
				int nc = temp.c+dirs[d][1];
				
				if(!isInMap(nr, nc)) continue;
				if(temp.visited[map[nr][nc]-'A']) continue;
				
				boolean[] next_visited = new boolean[26];
				for (int i = 0; i < next_visited.length; i++) {
					next_visited[i] = temp.visited[i];
				}
				next_visited[map[nr][nc]-'A'] = true;
				q.offer(new Game(nr, nc, temp.d+1, next_visited));
			}
		}
	
	}

	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<R&&nc>=0&&nc<C;
	}
	
	static class Game {
		int r;
		int c;
		int d;
		boolean[] visited;
		
		public Game(int r, int c, int d, boolean[] visited) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.visited = visited;
		}

		
	}
}
