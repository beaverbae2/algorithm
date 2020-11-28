package A2020_11_28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {
	static int R,C;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int answer = 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,1});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
			int depth = elem[2];
			
			if(r==R-1&&c==C-1) {
				answer = depth;
				break;
			}
			
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
			
				if(nr<0||nr>R-1||nc<0||nc>C-1||visited[nr][nc]) continue;
				
				if(map[nr][nc] == 1) {
					q.offer(new int[] {nr, nc, depth+1});
					visited[nr][nc] = true;
				}
			}
			
		}
				
		return answer;
	}
}