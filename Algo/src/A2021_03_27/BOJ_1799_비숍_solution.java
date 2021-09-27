package A2021_03_27;

import java.util.*;
import java.io.*;

/**
 * DFS
 * 체스판 : 흑->백->흑->백 순, 흑과 백은 서로 영향X
 * @author beaverbae
 * @see https://pangsblog.tistory.com/84
 * 
 */

public class BOJ_1799_비숍_solution {
	static int N;
	static int[][] map;
	static int[][] dirs = {{1,1},{-1,-1},{1,-1},{-1,1}};
	static boolean[][] visited;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		List<Integer> black_list = new ArrayList<>();
		List<Integer> white_list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			
				if (map[i][j] == 1) {
					int idx = i*N + j;
					if (N % 2 == 0) {// 짝수 개
						if (i % 2 == 0) {// 짝수 행
							if (idx % 2 == 0) {
								black_list.add(idx);
							} else {
								white_list.add(idx);
							}
						} else {// 홀수 행
							if (idx % 2 == 0) {
								white_list.add(idx);
							} else {
								black_list.add(idx);
							}
						}
					} else {// 홀수 개
						if (idx % 2 == 0) {
							black_list.add(idx);
						} else {
							white_list.add(idx);
						}
						
					}
				}
			}
		}
		
		int ans = 0;
		answer = 0;
		visited = new boolean[N][N];
		dfs(0, 0, black_list);
		ans += answer;
		
		answer = 0;
		visited = new boolean[N][N];
		dfs(0, 0, white_list);
		ans += answer;
		System.out.println(ans);
	}
	
	private static void dfs(int idx, int cnt, List<Integer> list) {
		if (idx == list.size()) {
			answer = Math.max(answer, cnt);
			return;
		}
		
		int i = list.get(idx);
		int r = i/N;
		int c = i%N;
		
		if (check(r,c)) {// 둠
			visited[r][c] = true;
			dfs(idx+1, cnt+1, list);
			visited[r][c] = false;
		}
		
		// 두지 않음
		dfs(idx+1, cnt, list);
	}
	
	private static boolean check(int sr, int sc) {
		for (int d = 0; d < dirs.length; d++) {
			int r = sr;
			int c = sc;
			
			while (true) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];
				
				if (!isIn(nr, nc)) break;
				if (visited[nr][nc]) return false;
				
				r = nr;
				c = nc;
			}
		}
		
		return true;
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
