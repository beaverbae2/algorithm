package A2021_01_27;

import java.util.*;
import java.io.*;

/**
 * BFS + permutation
 * 
 * @author beaverbae
 *
 */
public class BOJ_4991_로봇_청소기 {
	static char[][] map;
	static int R, C;
	static int[][] pos;// pos[i][j] : (i, j)에 있는 더러운 칸의 번호
	static ArrayList<Pair> posList;
	static int[][] dist;// dist[i][j] : i -> j 의 거리
	static final int INF = 987654321;
	static ArrayList<Integer> selectedList;
	static boolean[] selected;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			if (R == 0 && C == 0) {
				break;
			}

			map = new char[R][C];
			pos = new int[R][C];
			posList = new ArrayList<>();

			int num = 1;
			for (int i = 0; i < map.length; i++) {
				String str = br.readLine();
				for (int j = 0; j < map[i].length; j++) {
					char ch = str.charAt(j);
					map[i][j] = ch;
					if (ch == 'o') {
						pos[i][j] = 0;
						posList.add(0, new Pair(i, j));
					} else if (ch == '*') {
						pos[i][j] = num;
						posList.add(new Pair(i, j));
						num++;
					}
				}
			}

			dist = new int[num][num];
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist[i].length; j++) {
					if (i == j)
						continue;

					dist[i][j] = INF;
				}
			}

			for (int n = 0; n < num; n++) {
				bfs(n);
			}

			answer = INF;
			selectedList = new ArrayList<>();
			selected = new boolean[num];
			selectedList.add(0);
			permuation(1, num);
			
			if (answer == INF)
				sb.append(-1).append("\n");
			else
				sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void permuation(int r, int num) {
		if (r == num) {
			int temp = 0;
			for (int i = 0; i < selectedList.size() - 1; i++) {
				int start = selectedList.get(i);
				int dest = selectedList.get(i + 1);
				if (dist[start][dest] != INF) {
					temp += dist[start][dest];
				} else {
					temp = INF;
					break;
				}
			}
			answer = Math.min(answer, temp);
			return;
		}

		for (int i = 1; i < num; i++) {
			if (selected[i])
				continue;

			selected[i] = true;
			selectedList.add(i);
			permuation(r + 1, num);
			selected[i] = false;
			selectedList.remove(r);
		}
	}

	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];

		Pair p = posList.get(start);
		int start_r = p.r;
		int start_c = p.c;
		q.offer(new Node(start_r, start_c, 0));
		visited[start_r][start_c] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int d = node.d;

			for (int i = 0; i < dirs.length; i++) {
				int nr = r + dirs[i][0];
				int nc = c + dirs[i][1];

				if (!isInMap(nr, nc) || visited[nr][nc] || map[nr][nc] == 'x') {
					continue;// 이동 불가
				}

				if (map[nr][nc] == '*' || map[nr][nc] == 'o') {// 더러운 칸
					int dest = pos[nr][nc];
					dist[start][dest] = d + 1;
				}

				visited[nr][nc] = true;
				q.offer(new Node(nr, nc, d + 1));
			}

		}
	}

	private static boolean isInMap(int nr, int nc) {
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
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}

	static class Node {
		int r, c, d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
	}
}
