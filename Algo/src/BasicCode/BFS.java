package BasicCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제 풀이 요령
 * 1. 2차원 배열에서의 조합
 * 2. BFS 탐색 : Queue의 요소,초기화, 이동 조건, visited 
 * 3, map과 next_map : 원본 map은 보존 되어야 함
 */


//BOJ 14502 연구소
public class BFS {
	static int N,M;
	static int[][] map;
	static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
	static int answer;
	static String src = "7 7\r\n" + 
			"2 0 0 0 1 1 0\r\n" + 
			"0 0 1 0 1 2 0\r\n" + 
			"0 1 1 0 1 0 0\r\n" + 
			"0 1 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 1 1\r\n" + 
			"0 1 0 0 0 0 0\r\n" + 
			"0 1 0 0 0 0 0";
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		//벽 선택
		combi(0,0);
		System.out.println(answer);
	}


	private static void combi(int cnt, int start) {
		if(cnt == 3) {//벽 3개
			answer = Math.max(answer, bfs());//탐색
			return;
		}
		for (int i = start; i < N*M; i++) {
			int r = i/M;
			int c = i%M;
			
			if(map[r][c]==0) {
				map[r][c] = 1;
				combi(cnt+1, i+1);
				map[r][c] = 0;
			}
		}
	}


	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==2) {
					q.offer(new int[] {i,j});
				}
			}
		}
		
		int[][] next_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				next_map[i][j] = map[i][j];
			}
		}
		
		while(!q.isEmpty()) {
			int[] elem = q.poll();
			int r = elem[0];
			int c = elem[1];
		
			for (int d = 0; d < dirs.length; d++) {
				int nr = r+dirs[d][0];
				int nc = c+dirs[d][1];
			
				if (!isInMap(nr, nc)) continue;
				
				if(next_map[nr][nc]==0) {
					q.offer(new int[] {nr, nc});
					next_map[nr][nc] = 2;
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(next_map[i][j]==0) result++;
			}
		}
		
		return result;
	}
	
	private static boolean isInMap(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}
	
}
