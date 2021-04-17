package A2021_04_17;

import java.util.*;
import java.io.*;

/**
 * 1H40MIN
 * DFS
 * 오래걸린 이유: set오류(ConcurrentModificationException) , answer처리
 * @author beaverbae
 *
 */

public class BOJ_11967_불켜기 {
	static boolean[][] isLight;
	static boolean[][] visited;
	static List<Pair>[][] list;
	static int N, M;
	static HashSet<Integer> set;
	static int answer;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isLight = new boolean[N][N];
		visited = new boolean[N][N];

		list = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		set = new HashSet<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			list[x][y].add(new Pair(a, b));
		}

		isLight[0][0] = true;
		dfs(0);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isLight[i][j]) answer++;
			}
		}
		
		System.out.println(answer);
	}

	private static void dfs(int idx) {
		int r = idx / N;
		int c = idx % N;

		visited[r][c] = true;
		set.remove(idx);
		
		// 탐색 확인해야하는 좌표 추가
		for (int d = 0; d < dirs.length; d++) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];

			if (isIn(nr, nc) && !visited[nr][nc]) {
				int nidx = nr * N + nc;
				set.add(nidx);
			}
		}

		// 현재 위치에서 불을 밝힐 수 있는 좌표 추가
		for (Pair p : list[r][c]) {
			isLight[p.r][p.c] = true;
		}
		
		// 이동할 수 있는 좌표로 이동
		Iterator<Integer> iter = set.iterator();
		while (iter.hasNext()) {
			int next_idx = iter.next();
			int nr = next_idx/N;
			int nc = next_idx%N;
			if (isLight[nr][nc] && !visited[nr][nc]) {
				// answer++ // 틀렸던 부분
				dfs(next_idx);
				iter = set.iterator();// 오래걸린부분1
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
}
