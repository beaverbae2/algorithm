package A2021_04_24;

import java.util.*;
import java.io.*;
/**
 * 오래걸린 이유
 * - 문제 똑바로 해석 안함(꽃이 피는 조건)
 * - Queue에 다음 값을 집어넣어야 되는데 현재값을 집어넣음
 * - state(현재 위치에 있는 배양액) == 3 일떄 거르는 거 처리 안해줬음
 * - state와 time을 동시 확인해야되는데, Pair 객체로 했는데 가독성 떨어지고 null 처리 문제 있음
 * 
 * @author beaverbae
 *
 */
public class BOJ_18809_Gaaaaaaaaaarden_badversion {
	static int R, C, red, green;
	static int[][] map;
	static List<Pair> list;
	static int[] visited;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		green = Integer.parseInt(st.nextToken());
		red = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2) {
					list.add(new Pair(i, j));
				}
			}
		}

		visited = new int[list.size()];

		getGreen(0, 0);
		System.out.println(answer);
	}

	private static void getGreen(int start, int cnt) {
		if (cnt == green) {
			getRed(0, 0);
			return;
		}

		for (int i = start; i < list.size(); i++) {
			if (visited[i] != 0)
				continue;
			visited[i] = 1;
			getGreen(i + 1, cnt + 1);
			visited[i] -= 1;
		}
	}

	private static void getRed(int start, int cnt) {
		if (cnt == red) {
			answer = Math.max(answer, move());
			return;
		}

		for (int i = start; i < list.size(); i++) {
			if (visited[i] != 0)
				continue;
			visited[i] = 2;
			getRed(i + 1, cnt + 1);
			visited[i] -= 2;
		}
	}

	private static int move() {
		int result = 0;
		Queue<Node> q = new LinkedList<>();
		Pair[][] map_state = new Pair[R][C];
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == 0)
				continue;

			Pair p = list.get(i);
			q.offer(new Node(p.r, p.c, visited[i], 0));
			map_state[p.r][p.c] = new Pair(visited[i], 0);
		}

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int state = map_state[r][c].r;
			int time = map_state[r][c].c;

			if (state == 3) continue;
				
			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				if (isIn(nr, nc) && map[nr][nc] != 0) {
					int s = 0;
					int t = time + 1;

					if (map_state[nr][nc] != null) {
						s = map_state[nr][nc].r;
						t = map_state[nr][nc].c;
					} else {
						map_state[nr][nc] = new Pair(s, t);
					}

					if (state == 1) {// green
						if (s == 0) {
							map_state[nr][nc].r = 1;
							q.offer(new Node(nr, nc, map_state[nr][nc].r, time + 1));
						} else if (s == 2 && t == time + 1) {
							map_state[nr][nc].r = 3;
						}
					} else if (state == 2) {// red
						if (s == 0) {
							map_state[nr][nc].r = 2;
							q.offer(new Node(nr, nc, map_state[nr][nc].r, time + 1));
						} else if (s == 1 && t == time + 1) {
							map_state[nr][nc].r = 3;
						}
					}
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map_state[i][j] == null)
					continue;

				if (map_state[i][j].r == 3) {
					result++;
				}
			}
		}

		return result;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}

	static class Node {
		int r, c, state, time;

		public Node(int r, int c, int state, int time) {
			this.r = r;
			this.c = c;
			this.state = state;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", state=" + state + ", time=" + time + "]";
		}
	}
}
