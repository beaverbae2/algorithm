package A2021_01_28;

import java.util.*;
import java.io.*;

/**
 * BFS
 * good case
 	4 2
	0 0
	0 0
	1 0
	0 0
	1 1 3
	4 1 3
	answer : 7
 * 
 * @author beaverbae
 *
 */

public class BOJ_1726_로봇ver2 {
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };// 동 서 남 북
	static int[][] map;
	static int R, C;
	static int start_r, start_c, start_dir;
	static int end_r, end_c, end_dir;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		start_r = Integer.parseInt(st.nextToken()) - 1;
		start_c = Integer.parseInt(st.nextToken()) - 1;
		start_dir = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		end_r = Integer.parseInt(st.nextToken()) - 1;
		end_c = Integer.parseInt(st.nextToken()) - 1;
		end_dir = Integer.parseInt(st.nextToken()) - 1;

		int answer = bfs();
		System.out.println(answer);
	}

	private static int bfs() {
		int result = 0;
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][4];

		q.offer(new Node(start_r, start_c, 0, start_dir));
		visited[start_r][start_c][start_dir] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int r = node.r;
			int c = node.c;
			int depth = node.depth;
			int dir = node.dir;

			if (r == end_r && c == end_c && dir == end_dir) {
				result = depth;
				break;
			}

			// k칸 (1~3 만큼 이동)
			for (int k = 1; k <= 3; k++) {
				int nr = r + k * dirs[dir][0];
				int nc = c + k * dirs[dir][1];

				if (isInMap(nr, nc) && !visited[nr][nc][dir]) {
					if (map[nr][nc] == 0) {// 궤도 인 경우 -> 탐색 가능
						visited[nr][nc][dir] = true;
						q.offer(new Node(nr, nc, depth + 1, dir));
					} else {// 궤도X -> 탐색 불가 및 다음 k로 이동 불가
						break;
					}
				}
			}

//			기존 코드 (틀림)
//			for (int k = 1; k <= 3; k++) {
//				int nr = r + k * dirs[dir][0];
//				int nc = c + k * dirs[dir][1];
//				
//				if (isInMap(nr, nc) && !visited[nr][nc][dir] && (map[nr][nc] == 0)) {
//					visited[nr][nc][dir] = true;
//					q.offer(new Node(nr, nc, depth + 1, dir));
//				}else {
//					break;
//				}
//			}

			// 좌우 방향 전환
			// 좌
			int next_dir = turnLeft(dir);
			if (isInMap(r, c) && !visited[r][c][next_dir]) {
				visited[r][c][next_dir] = true;
				q.offer(new Node(r, c, depth + 1, next_dir));
			}

			// 우
			next_dir = turnRight(dir);
			if (isInMap(r, c) && !visited[r][c][next_dir]) {
				visited[r][c][next_dir] = true;
				q.offer(new Node(r, c, depth + 1, next_dir));
			}
		}

		return result;
	}

	private static boolean isInMap(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	private static int turnLeft(int dir) {
		switch (dir) {
		case 0:
			dir = 3;
			break;
		case 1:
			dir = 2;
			break;
		case 2:
			dir = 0;
			break;
		case 3:
			dir = 1;
			break;

		default:
			dir = 0;
			break;
		}

		return dir;
	}

	private static int turnRight(int dir) {
		switch (dir) {
		case 0:
			dir = 2;
			break;
		case 1:
			dir = 3;
			break;
		case 2:
			dir = 1;
			break;
		case 3:
			dir = 0;
			break;

		default:
			dir = 0;
			break;
		}

		return dir;
	}

	static class Node {
		int r, c, depth, dir;

		public Node(int r, int c, int depth, int dir) {
			this.r = r;
			this.c = c;
			this.depth = depth;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", depth=" + depth + ", dir=" + dir + "]";
		}
	}
}