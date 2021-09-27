package A2021_03_01;

import java.util.*;
import java.io.*;

/**
 * BFS, MST 
 * 52MIN
 * @author beaverbae
 *
 */

public class BOJ_17472_다리_만들기 {
	static int[][] map;
	static boolean[][] visited;
	static int R, C;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static HashMap<Integer, ArrayList<Node>> bridge_start;
	static List<Elem> graph;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[R][C];
		bridge_start = new HashMap<>();

		int island_id = 1;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					findIslandId(i, j, island_id);
					island_id++;
				}
			}
		}

		graph = new ArrayList<>();
		for (int i = 1; i < island_id; i++) {
			lay_bridge(i);
		}

		parent = new int[island_id];
		for (int i = 1; i < island_id; i++) {
			parent[i] = i;
		}

		Collections.sort(graph, new Comparator<Elem>() {
			@Override
			public int compare(Elem o1, Elem o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.w, o2.w);
			}
		});

		int cnt = 0;
		int answer = 0;
		for (int i = 0; i < graph.size(); i++) {
			Elem e = graph.get(i);
			int a = e.v1;
			int b = e.v2;
			int w = e.w;

			if (findParent(a, b))
				continue;
			unionParent(a, b);

			answer += w;
			cnt++;
			if (cnt == island_id - 2)
				break;
		}

		if (cnt != island_id - 2)
			answer = -1;

		System.out.println(answer);
	}

	private static void lay_bridge(int v) {
		Queue<Node> q = new LinkedList<>();
		ArrayList<Node> list = bridge_start.get(v);
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			q.offer(new Node(node.r, node.c, node.w, node.dir));
		}

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int w = node.w;
			int dir = node.dir;

			int nr = r + dirs[dir][0];
			int nc = c + dirs[dir][1];

			if (isInMap(nr, nc)) {
				if (map[nr][nc] == 0) {
					q.offer(new Node(nr, nc, w + 1, dir));
				} else {
					int next_v = map[nr][nc];

					if (w > 1 && v != next_v) {
						graph.add(new Elem(v, next_v, w));
					}
				}
			}
		}
	}

	private static void findIslandId(int sr, int sc, int island_id) {
		Queue<Pair> q = new LinkedList<>();

		q.offer(new Pair(sr, sc));
		visited[sr][sc] = true;
		map[sr][sc] = island_id;
		bridge_start.put(island_id, new ArrayList<>());
		ArrayList<Node> list = bridge_start.get(island_id);

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int r = p.r;
			int c = p.c;

			for (int d = 0; d < dirs.length; d++) {
				int nr = r + dirs[d][0];
				int nc = c + dirs[d][1];

				if (isInMap(nr, nc)) {
					if (map[nr][nc] == 1) {
						if (!visited[nr][nc]) {
							q.offer(new Pair(nr, nc));
							visited[nr][nc] = true;
							map[nr][nc] = island_id;
						}
					} else if (map[nr][nc] == 0) {
						list.add(new Node(nr, nc, 1, d));
					}
				}
			}
		}
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static int getParent(int v) {
		if (parent[v] == v)
			return parent[v];
		return parent[v] = getParent(parent[v]);
	}

	private static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static boolean findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);

		if (a == b)
			return true;
		return false;
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
		int r, c, w, dir;

		public Node(int r, int c, int w, int dir) {
			this.r = r;
			this.c = c;
			this.w = w;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", w=" + w + ", dir=" + dir + "]";
		}
	}

	static class Elem {
		int v1, v2, w;

		public Elem(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Elem [v1=" + v1 + ", v2=" + v2 + ", w=" + w + "]";
		}
	}
}
