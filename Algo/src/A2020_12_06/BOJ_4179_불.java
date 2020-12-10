package A2020_12_06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_4179_불 {
	static int R,C;
	static char[][] map;
	static ArrayList<int[]> initFireList;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		initFireList = new ArrayList<int[]>();
		int start_r = -1;
		int start_c = -1;
		
		
		for (int i = 0; i < R; i++) {
			String src = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = src.charAt(j);
				
				if(map[i][j]=='J') {
					start_r = i;
					start_c = j;
				}else if(map[i][j]=='F') {
					initFireList.add(new int[] {i, j});
				}
			}
		}
		
		int answer = bfs(start_r, start_c);
	
		if(answer == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		}else System.out.println(answer);
	}

	private static int bfs(int start_r, int start_c) {
		Queue<int[]> q1 = new LinkedList<>();
		boolean[][] visited1 = new boolean[R][C];
		q1.offer(new int[] {start_r, start_c, 0});
		visited1[start_r][start_c] = true;
		
		Queue<int[]> q2 = new LinkedList<>();
		boolean[][] visited2 = new boolean[R][C];
		for(int[] elem : initFireList) {
			int r = elem[0];
			int c = elem[1];
			q2.offer(new int[] {r, c, 0});
			visited2[r][c] = true;
		}
		
		int depth_limit = 0;
		while(true) {
			if(q1.isEmpty()&&q2.isEmpty()) {
				break;
			}
			
			//System.out.println("J");
			while(!q1.isEmpty()) {
				int[] elem = q1.peek();
				int r = elem[0];
				int c = elem[1];
				int depth = elem[2];
				
				if(depth>depth_limit) break;;
				q1.poll();
				
				if(map[r][c]=='F') continue;
				
				if(isSide(r, c)) return depth+1;
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r+dirs[d][0];
					int nc = c+dirs[d][1];
					
					if(!isInMap(nr, nc)||visited1[nr][nc]) continue;
					if(map[nr][nc]=='.') {
						//System.out.println("nr: "+nr+", nc : "+nc);
						map[nr][nc] = 'J';
						q1.offer(new int[] {nr, nc, depth+1});
						visited1[nr][nc] = true;
					}
				}
			}
			
			//System.out.println("Fire");
			while(!q2.isEmpty()) {
				int[] elem = q2.peek();
				int r = elem[0];
				int c = elem[1];
				int depth = elem[2];
				
				if(depth>depth_limit) break;
				q2.poll();
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = r+dirs[d][0];
					int nc = c+dirs[d][1];
					
					if(!isInMap(nr, nc)||visited2[nr][nc]) continue;
					if(map[nr][nc]=='.'||map[nr][nc]=='J') {
						//System.out.println("nr: "+nr+", nc : "+nc);
						q2.offer(new int[] {nr, nc, depth+1});
						map[nr][nc] = 'F';
						visited2[nr][nc] = true;
					}
				}
				
			}
			
			
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			depth_limit++;
			
		}
		
		
		return Integer.MAX_VALUE;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0&&nr<R&&nc>=0&&nc<C;
	}
	
	private static boolean isSide(int r, int c) {
		if(r==0||r==R-1||c==0||c==C-1) return true;
		return false;
	}
}
